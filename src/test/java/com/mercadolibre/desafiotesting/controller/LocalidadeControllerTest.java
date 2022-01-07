package com.mercadolibre.desafiotesting.controller;

import com.mercadolibre.desafiotesting.model.Localidade;
import com.mercadolibre.desafiotesting.repository.LocalidadeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class LocalidadeControllerTest {

    @Autowired
    LocalidadeRepository localidadeRepository;

    @Autowired
    private MockMvc mockMvc;

    Localidade localidade;

    @Test
    public void deveRetornarListaLocalidade() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/localidade"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void deveRetornarLocalidadePorId() throws Exception {
        localidade = new Localidade(1l,"Santos",BigDecimal.valueOf(500.0));
        localidadeRepository.save(localidade);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/localidade/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void deveCriarLocalidadePorId() throws Exception {
        String payLoad = " { \"id\": 1, \"nome\":\"testeLocal\", \"precoM2\": 10.00}";
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/localidade")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }


    @Test
    public void deveDeletarLocalidadePorId() throws Exception {
        localidade = new Localidade(2l,"Santos",BigDecimal.valueOf(500.0));
        localidadeRepository.save(localidade);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/localidade/2")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void deveAtualizarLocalidadePorId() throws Exception {
        localidade = new Localidade(1l,"Santos",BigDecimal.valueOf(500.0));
        localidadeRepository.save(localidade);

        String payLoad = " { \"nome\":\"Bertioga\", \"precoM2\": 10.00}";
        mockMvc.perform(MockMvcRequestBuilders
                .put("/localidade/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
