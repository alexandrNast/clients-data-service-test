package ru.softlab.zikova.efr.config.controllers.junit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;
import ru.softlab.zikova.efr.config.ControllerTestConfig;
import ru.softlab.zikova.efr.model.ClientEntity;
import ru.softlab.zikova.efr.services.ClientService;

import javax.persistence.EntityNotFoundException;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.softlab.zikova.efr.api.ClientsApi.CREATE_CLIENT_PATH;
import static ru.softlab.zikova.efr.api.ClientsApi.GET_CLIENT_BY_ID_PATH;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ControllerTestConfig.class)
public class TestClientController {

    protected MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ClientService clientService;

    @Rule
    public final ExpectedException exception = ExpectedException.none();


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @After
    public void tearDown(){
        Mockito.reset(clientService);
    }

    @Test
    public void testGetClientById() throws Exception {
        when(clientService.findById(1L))
                .thenReturn(new ClientEntity(1L, "TestName", "TestSurname", true));

        mockMvc.perform(get(GET_CLIENT_BY_ID_PATH, 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetClientById_404_NotFound() throws Exception {
        when(clientService.findById(anyLong()))
                .thenThrow(new EntityNotFoundException());

        exception.expect(NestedServletException.class);
        mockMvc.perform(get(GET_CLIENT_BY_ID_PATH, 100000L))
                .andExpect(status().isNotFound());


    }

//    @Test
//    public void testDeleteClientById() throws Exception {
//        ClientEntity client = new ClientEntity(1L, "TestName", "TestSurname", true);
//
//
//        mockMvc.perform(delete(DELETE_CLIENTS_PATH, 1L))
//                .andExpect(status().isOk());
//
//    }

    @Test
    public void testCreateNewClient() throws Exception{
        ClientEntity client = new ClientEntity(10L, "Ivan", "Ivanov", true);
        when(clientService.save(client))
                .thenReturn(new ClientEntity(10L, "Ivan", "Ivanov", true));

        mockMvc.perform(post(CREATE_CLIENT_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(client)))
                .andExpect(status().isCreated());
    }

//    @Test
//    public void testGetAllClients() throws Exception{
//
//    }
    private static String asJsonString(final Object obj) {
        try {
            return getMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

}
