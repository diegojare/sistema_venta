package com.cybershoes.sistema_venta.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cybershoes.sistema_venta.model.Cliente;
import com.cybershoes.sistema_venta.repository.ClienteRepository;
import com.cybershoes.sistema_venta.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> obtenerPorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public List<Cliente> listarPorDniONombreOApellido(String filtro) {
        return clienteRepository.findByDniClienteStartingWithOrNomClienteContainingIgnoreCaseOrApeClienteContainingIgnoreCase(filtro, filtro, filtro);
    }

}
