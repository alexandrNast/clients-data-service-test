package ru.softlab.zikova.efr.config.controllers.integration;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.softlab.efr.test.infrastructure.transport.rest.MockRestRule;
import ru.softlab.zikova.efr.config.IntegrationTestApplicationConfig;
import ru.softlab.zikova.efr.exchange.model.Client;
import ru.softlab.zikova.efr.model.ClientEntity;
import ru.softlab.zikova.efr.repositories.ClientRepository;
import ru.softlab.zikova.efr.services.ClientService;


import static ru.softlab.efr.test.infrastructure.transport.rest.matchers.MockRestResultMatchers.status;
import static ru.softlab.zikova.efr.api.ClientsApi.CREATE_CLIENT_PATH;
import static ru.softlab.zikova.efr.api.ClientsApi.GET_CLIENT_BY_ID_PATH;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = IntegrationTestApplicationConfig.class)
public class IntegrationTestClientController {

    @Autowired
    private ClientRepository clientRepository;


    @Autowired
    @Rule
    public MockRestRule mockRest;

    @Test
    public void testGetClientsById() throws Exception{
        ClientEntity client = new ClientEntity("Ivan", "Ivanov", true);

        ClientEntity savedClient = clientRepository.saveAndFlush(client);

        mockRest.init()
                .path(GET_CLIENT_BY_ID_PATH)
                .variable("clientId", savedClient.getId())
                .get(ClientEntity.class)
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateClient() throws Exception{
        Client client = new Client("TestName", "TestSurName");

        mockRest.init()
                .setTimeoutInSeconds(60L)
                .path(CREATE_CLIENT_PATH)
                .post(client, Client.class)
                .andExpect(status().isCreated())
                .andReturnBody();

    }


    @After
    public void tearDown(){
        clientRepository.deleteAll();
    }


}
