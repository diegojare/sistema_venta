package com.cybershoes.sistema_venta.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cybershoes.sistema_venta.model.DetalleVenta;
import com.cybershoes.sistema_venta.model.DetalleVentaId;
import com.cybershoes.sistema_venta.model.Producto;
import com.cybershoes.sistema_venta.model.ProductoParaVender;
import com.cybershoes.sistema_venta.model.Venta;
import com.cybershoes.sistema_venta.service.ClienteService;
import com.cybershoes.sistema_venta.service.DetalleVentaService;
import com.cybershoes.sistema_venta.service.ProductoService;
import com.cybershoes.sistema_venta.service.UsuarioService;
import com.cybershoes.sistema_venta.service.VentaService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/recepcionista/venta")
public class VentaController {

	private final VentaService ventaService;
	private final ClienteService clienteService;
	private final ProductoService productoService;
	private final UsuarioService usuarioService;
	private final DetalleVentaService detalleVentaService;

	public VentaController(VentaService ventaService, ClienteService clienteService, ProductoService productoService,
			UsuarioService usuarioService, DetalleVentaService detalleVentaService) {
		this.ventaService = ventaService;
		this.clienteService = clienteService;
		this.productoService = productoService;
		this.usuarioService = usuarioService;
		this.detalleVentaService = detalleVentaService;
	}

	@GetMapping("/listar")
	public String listVentas(Model model) {
		model.addAttribute("ventas", ventaService.listarTodos());
		model.addAttribute("clienteList", clienteService.listarTodos());
		model.addAttribute("usuarioVentaList", usuarioService.listarTodos());
		return "venta/venta_listar";
	}

	private ArrayList<ProductoParaVender> obtenerCarrito(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		ArrayList<ProductoParaVender> carrito = (ArrayList<ProductoParaVender>) request.getSession()
				.getAttribute("carrito");
		if (carrito == null) {
			carrito = new ArrayList<>();
		}
		return carrito;
	}

	private void guardarCarrito(ArrayList<ProductoParaVender> carrito, HttpServletRequest request) {
		request.getSession().setAttribute("carrito", carrito);
	}

	@PostMapping("/quitar/{indice}")
	public String quitarDelCarrito(@PathVariable int indice, HttpServletRequest request) {
		ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
		if (carrito != null && carrito.size() > 0 && carrito.get(indice) != null) {
			carrito.remove(indice);
			this.guardarCarrito(carrito, request);
		}

		return "redirect:/recepcionista/venta/registrar";
	}

	private void limpiarCarrito(HttpServletRequest request) {
		this.guardarCarrito(new ArrayList<>(), request);
	}

	@GetMapping("/registrar")
	public String interfazVender(Model model, HttpServletRequest request) {
		Venta venta = new Venta();
		model.addAttribute("venta", venta); // enviamos objeto a la pagina create
		model.addAttribute("clientelist", clienteService.listarTodos()); // enviamos datos de clientes
		model.addAttribute("usuarioVentalist", usuarioService.listarTodos()); // enviamos datos de asesor
		model.addAttribute("producto", new Producto()); // enviamos un objeto producto con atributos vacíos
		float total = 0;
		ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request); // creamos un List con el carrito vacío
		for (ProductoParaVender p : carrito)
			total += p.getTotal(); // por cada producto agregado se suma el total
		model.addAttribute("total", total); // enviamos el total a la página create
		return "venta/venta_registrar";
	}

	@PostMapping("/agregar")
	public String agregarAlCarrito(@ModelAttribute Producto producto, HttpServletRequest request,
			RedirectAttributes redirectAttrs) {
		ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);// creo un List y lo empiezo a llenar
		Producto productoBuscadoPorCodigo = productoService.buscarProductoByCodigo(producto.getCodigo());
		if (productoBuscadoPorCodigo == null) {
			redirectAttrs
					.addFlashAttribute("mensaje", "El producto con el código " + producto.getCodigo() + " no existe")
					.addFlashAttribute("clase", "warning");
			return "redirect:/recepcionista/venta/registrar"; // si no existe el producto redirecciono un atributo
																// mensaje a la página
		}

		if (productoBuscadoPorCodigo.sinExistencia()) {
			redirectAttrs.addFlashAttribute("mensaje", "El producto está agotado").addFlashAttribute("clase",
					"warning");
			return "redirect:/recepcionista/venta/registrar";
		}

		boolean encontrado = false;

		for (ProductoParaVender productoParaVenderActual : carrito) {// obtengo cada objeto guardado en carrito
			if (productoParaVenderActual.getCodigo().equals(productoBuscadoPorCodigo.getCodigo())) {
				productoParaVenderActual.aumentarCantidad();
				encontrado = true;
				break;
			} // buscamos si ya habíamos ingresado ese producto a carrito, si es así
				// aumentamos un cantidad más sin ingresarlo

		}

		if (!encontrado) {// si encontrado es falso (no encontró el producto en carrito)
			carrito.add(
					new ProductoParaVender(productoBuscadoPorCodigo.getIdProducto(),
							productoBuscadoPorCodigo.getCodigo(),productoBuscadoPorCodigo.getMarcaProd(),productoBuscadoPorCodigo.getModeloProd(),
							productoBuscadoPorCodigo.getDescripcion(), productoBuscadoPorCodigo.getPrecioCompra(),
							productoBuscadoPorCodigo.getPrecioVenta(), productoBuscadoPorCodigo.getStock(), 1// cantidad
																												// arbritariamente
					));
		} // entonces ingresamos el nuevo producto a carrito
		this.guardarCarrito(carrito, request);// guardo todos los productos agregados en la sesión de venta en carrito
		return "redirect:/recepcionista/venta/registrar";
	}

	@PostMapping("/terminar")
	public String terminarVenta(HttpServletRequest request, RedirectAttributes redirectAttrs,
			@ModelAttribute("venta") Venta miVenta) {
		ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request); // 1lenamos nuevamente un List del carrito
		if (carrito == null || carrito.size() <= 0) {
			return "redirect:/recepcionista/venta/registrar";
		} // Si no hay carrito o está vacío, regresamos inmediatamente
			// De ser todo correcto, asignamos un valor al atributo fecha del objeto venta
			// ingresado como parámetro
		miVenta.setFechaVenta(LocalDate.now());
		Venta v = ventaService.registrarVenta(miVenta); // guardamos ese objeto venta y lo asignamos a otro objeto
														// //variables locales
		BigDecimal total = new BigDecimal(0);
		BigDecimal importeVenta = new BigDecimal(0);
		BigDecimal importeCompra = new BigDecimal(0);
		BigDecimal ganancia = new BigDecimal(0);
		// Recorrer el carrito
		for (ProductoParaVender productoParaVender : carrito) {
			// Obtener el producto reciente desde la base de datos
			Producto p = productoService.buscarProductoById(productoParaVender.getIdProducto());
			if (p == null)
				continue; // Si es nulo o no existe, ignoramos el siguiente código con continue // Le
							// restamos existencia
			p.restarExistencia(productoParaVender.getCantidad());
			// Lo guardamos con la existencia ya restada
			productoService.guardarProducto(p);
			DetalleVentaId objDetalleVentaId = new DetalleVentaId();
			objDetalleVentaId.setVenta(v);
			objDetalleVentaId.setProducto(p);

			importeVenta = new BigDecimal(productoParaVender.getCantidad()).multiply(productoParaVender.getPrecioVenta());

			importeCompra = new BigDecimal(productoParaVender.getCantidad()).multiply(productoParaVender.getPrecioCompra());
			
			// Creamos un nuevo detalle de venta que guardaremos junto con la venta
			DetalleVenta productoVendido = new DetalleVenta(objDetalleVentaId, productoParaVender.getCantidad(),
					productoParaVender.getPrecioVenta(), importeVenta, productoParaVender.getPrecioCompra());

			detalleVentaService.guardarDetalleVenta(productoVendido);
			// subtotal
			// subtotal = subtotal.add(importeVenta);
			total = total.add(importeVenta);
			ganancia = ganancia.add(importeVenta).subtract(importeCompra);
		}

		// actualizar los datos de la venta nueva y guardarla finalmente
		miVenta.setNroVenta(v.getNroVenta());
		// miVenta.setTotal (miVenta.getSubtotal().add(miVenta.getIgv()));
		miVenta.setTotal(total);
		miVenta.setIgv(miVenta.getTotal().multiply(new BigDecimal(0.18)));
		miVenta.setSubtotal(miVenta.getTotal().subtract(miVenta.getIgv()));
		miVenta.setGanancia(ganancia);
		ventaService.registrarVenta(miVenta);
		// Al final limpiamos el carrito
		this.limpiarCarrito(request);
		// e indicamos una venta exitosa
		redirectAttrs
				.addFlashAttribute("mensaje", "Venta realizada correctamente")
				.addFlashAttribute("clase", "success");

		return "redirect:/recepcionista/venta/registrar";
	}

	@GetMapping("/limpiar")
	public String cancelarVenta(HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.limpiarCarrito(request);
		redirectAttrs
				.addFlashAttribute("mensaje", "Venta cancelada")
				.addFlashAttribute("clase", "info");
		return "redirect:/recepcionista/venta/registrar";
	}

	@GetMapping("/listar/verDetalle/{id}")
	public String verDetalleForm(@PathVariable Long id, Model model) {
		List<DetalleVenta> dv = detalleVentaService.buscarDetalleVentaByNroVenta(id);
		model.addAttribute("listDetalleVenta", dv);
		return "venta/venta_detalles";
	}

}