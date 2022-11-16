package ru.softlab.zikova.efr.controllers;

import lombok.AllArgsConstructor;
import org.camunda.bpm.engine.authorization.Permissions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.softlab.efr.services.auth.Right;
import ru.softlab.efr.services.authorization.annotations.HasPermission;
import ru.softlab.efr.services.authorization.annotations.HasRight;
import ru.softlab.zikova.efr.api.ClientsApi;
import ru.softlab.zikova.efr.exchange.model.Client;
import ru.softlab.zikova.efr.model.ClientEntity;
import ru.softlab.zikova.efr.services.ClientService;

import static java.util.Objects.isNull;

@Component
@AllArgsConstructor
public class ClientDelegate implements ClientsApi.Delegate{

    private final ClientService clientService;

    @Override
    public ResponseEntity<Client> createClient(Client client) throws Exception {
        if (isNull(client.getName()) || isNull(client.getSurname())){
            throw  new NullPointerException();
        }
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setName(client.getName());
        clientEntity.setSurname(client.getSurname());
        clientService.save(clientEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @Override
    public ResponseEntity<String> deleteClients(String id) throws Exception {
        long clientId = Long.parseLong(id);
        clientService.remove(clientId);

        return ResponseEntity.ok("Клиент удалён");
    }

    @Override
    //@HasPermission()
    public ResponseEntity<Page<Client>> getAllClient(Pageable pageable) throws Exception {
        Page<ClientEntity> clientEntities = clientService.findAll(pageable);

        return ResponseEntity.ok(clientEntities.map(ClientDelegate::buildClient));
    }

    @Override
    @HasRight(Right.VIEW_CLIENT_ADDITIONAL_INFO)
    public ResponseEntity<Client> getClientById(String id){
        long clientId = Long.parseLong(id);
        ClientEntity clientEntity = clientService.findById(clientId);

        return ResponseEntity.ok(new Client(clientEntity.getName(), clientEntity.getSurname()));
    }

    @Override
    public ResponseEntity<Client> updateClient(Client toUpdateInformation) throws Exception {
        return null;
    }

    private static Client buildClient(ClientEntity clientEntity){
        Client client = new Client();
        client.setName(clientEntity.getName());
        client.setSurname(clientEntity.getSurname());
        return client;
    }

}
