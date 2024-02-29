package com.ccsw.tutorial.client;

import java.util.List;

import javax.naming.NameAlreadyBoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;

import jakarta.transaction.Transactional;

/**
 * @author Jaime Poveda Algueró
 *
 */
@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Client get(Long id) {

        return this.clientRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Client> findAll() {

        return (List<Client>) this.clientRepository.findAll();
    }

    /**
     * {@inheritDoc}
     * 
     * @throws {@link DataIntegrityViolationException}
     */
    @Override
    public void save(Long id, ClientDto dto) throws NameAlreadyBoundException {

        Client client = null;
        List<Client> clients = (List<Client>) this.clientRepository.findAll();
        boolean error = false;
        if (id == null) {
            for (int i = 0; i < clients.size(); i++) {
                if (dto.getName().equals(clients.get(i).getName().toString())) {
                    error = true;
                    throw new NameAlreadyBoundException("Name already used");
                }
            }
            client = new Client();
        } else {
            client = this.get(id);
        }

        if (!error) {
            client.setName(dto.getName());
            this.clientRepository.save(client);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if (this.get(id) == null) {
            throw new Exception("Not exists");
        }

        this.clientRepository.deleteById(id);
    }

}