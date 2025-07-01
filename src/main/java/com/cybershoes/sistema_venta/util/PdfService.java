package com.cybershoes.sistema_venta.util;

import com.cybershoes.sistema_venta.model.Venta;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PdfService {

	public ByteArrayInputStream listaVentasPdf(List<Venta> ventas) {
	    Document document = new Document();
	    ByteArrayOutputStream out = new ByteArrayOutputStream();

	    try {
	        PdfWriter.getInstance(document, out);
	        document.open();

	        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLUE);
	        Paragraph titulo = new Paragraph("Reporte de Ventas", fontTitulo);
	        titulo.setAlignment(Element.ALIGN_CENTER);
	        document.add(titulo);
	        document.add(new Paragraph(" "));

	        PdfPTable table = new PdfPTable(7);
	        table.setWidthPercentage(100);
	        table.setWidths(new float[]{2, 3, 4, 3, 2, 2, 2});

	        // Encabezados
	        Stream.of("Nro Venta", "Fecha", "Cliente", "Vendedor", "Subtotal", "IGV", "Total")
	            .forEach(headerTitle -> {
	                PdfPCell header = new PdfPCell();
	                Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	                header.setHorizontalAlignment(Element.ALIGN_CENTER);
	                header.setPhrase(new Phrase(headerTitle, headFont));
	              

	                table.addCell(header);
	            });
	        double totalVentas = 0.0;
	        for (Venta venta : ventas) {
	            table.addCell(String.valueOf(venta.getNroVenta()));
	            table.addCell(venta.getFechaVenta() != null ? venta.getFechaVenta().toString() : "");
	            table.addCell(venta.getCliente() != null ? venta.getCliente().getNomCliente() : "");
	            table.addCell(venta.getUsuario() != null ? venta.getUsuario().getUsername() : "");
	            table.addCell(venta.getSubtotal() != null ? venta.getSubtotal().toString() : "");
	            table.addCell(venta.getIgv() != null ? venta.getIgv().toString() : "");
	            table.addCell(venta.getTotal() != null ? venta.getTotal().toString() : "");
	            
				if (venta.getTotal() != null) {
					totalVentas += venta.getTotal().doubleValue();
				}
	        }

	        document.add(table);
	        document.add(new Paragraph(" "));

	        
	        Font fontTotal = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.DARK_GRAY);
	        Paragraph totalGeneral = new Paragraph("Monto Total General: S/ " + String.format("%.2f", totalVentas), fontTotal);
	        totalGeneral.setAlignment(Element.ALIGN_RIGHT);
	        document.add(totalGeneral);
	        
	        document.close();
	    } catch (DocumentException ex) {
	        ex.printStackTrace();
	    }
	    return new ByteArrayInputStream(out.toByteArray());
	}

}
