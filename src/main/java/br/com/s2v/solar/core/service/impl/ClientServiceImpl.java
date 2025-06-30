package br.com.s2v.solar.core.service.impl;

import br.com.s2v.solar.core.service.ClientService;
import br.com.s2v.solar.persistence.repository.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    //    @Override
    //    public ResponseEntity<Client> createClient(Client client) {
    //        var clientExists = clientRepository.findById(client.getId());
    //
    //        if (clientExists.isPresent()) {
    //            return ResponseEntity.status(409).build(); // Conflict
    //        }
    //
    //        clientRepository.save(client);
    //        return ResponseEntity.status(201).body(client); // Created
    //    }
    //
    //    @Override
    //    public ResponseEntity<Client> getById(Long clientId) {
    //        var client = clientRepository.findById(clientId);
    //
    //        if (client.isEmpty()) {
    //            return ResponseEntity.notFound().build(); // Not Found
    //        }
    //
    //        return ResponseEntity.ok(client.get()); // OK
    //    }
    //
    //    @Override
    //    public List<Client> getAll() {
    //        return clientRepository.findAll();
    //    }
    //
    //    @Override
    //    public ResponseEntity<Void> delete(Long clientId) {
    //        var client = clientRepository.findById(clientId);
    //
    //        if (client.isEmpty()) {
    //            return ResponseEntity.notFound().build(); // Not Found
    //        }
    //
    //        clientRepository.deleteById(client.get().getId());
    //        return ResponseEntity.noContent().build(); // No Content
    //    }
    //
    //    @Override
    //    public ResponseEntity<Client> update(Long clientId, Client client) {
    //        var clientResult = clientRepository.findById(clientId);
    //
    //        if (clientResult.isEmpty()) {
    //            return ResponseEntity.notFound().build(); // Not Found
    //        }
    //
    //        var existingClient = clientResult.get();
    //        existingClient.setName(client.getName());
    //        existingClient.getLocation().setState(client.getLocation().getState());
    //        existingClient.getLocation().setCity(client.getLocation().getCity());
    //        existingClient.getLocation().setAddress(client.getLocation().getAddress());
    //        existingClient.setUpdatedAt(java.time.LocalDateTime.now());
    //
    //        clientRepository.save(existingClient);
    //        return ResponseEntity.ok(existingClient); // OK
    //    }
}
