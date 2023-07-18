package com.alam.bank.controllerTest;


import com.alam.bank.models.ClienteDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteControllerTest {

    @LocalServerPort
    private int port;

    private TestRestTemplate testRestTemplate;

    private String baseUrl;

    @Before
    public void setUp() {
        this.testRestTemplate = new TestRestTemplate();
        this.baseUrl = "http://localhost:"+port+"/clientes";
    }

    @Sql({"/data.sql"})
    @Test
    public void testGetAllClients(){
        ClienteDto[] clientes = this.testRestTemplate.getForObject(this.baseUrl, ClienteDto[].class);

        assertFalse(clientes.length == 0);
    }

    @Sql({"/data.sql"})
    @Test
    public void testGetClientById(){
        ClienteDto cliente = this.testRestTemplate.getForObject(this.baseUrl+"/1", ClienteDto.class);

        assertEquals("123456", cliente.getIdentificacion());
    }

    @Sql({"/data.sql"})
    @Test
    public void testCreateClient(){
        //Prepare
        ClienteDto cliente = ClienteDto.builder()
                .identificacion("6758391")
                .direccion("13 de junio y Equinoccial")
                .genero("M")
                .fechaNacimiento(new Date("2/3/1993"))
                .nombre("Juan Osorio")
                .estado(true)
                .telefono("098874587")
                .contrasena("1245")
                .build();

        //Execute
        ClienteDto response = this.testRestTemplate.postForObject(this.baseUrl, cliente, ClienteDto.class);

        //Assert
        assertEquals(cliente.getIdentificacion(), response.getIdentificacion());
    }
}
