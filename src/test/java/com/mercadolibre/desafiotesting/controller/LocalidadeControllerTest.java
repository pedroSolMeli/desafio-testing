package com.mercadolibre.desafiotesting.controller;

import com.mercadolibre.desafiotesting.dto.LocalidadeDTO;
import com.mercadolibre.desafiotesting.service.LocalidadeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class LocalidadeControllerTest {

    @Mock
    LocalidadeService localidadeService;

    @Autowired
    private MockMvc mockMvc;

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
    public void naoDeveRetornarLocalidadePorId() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/localidade/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void deveRetornarLocalidadePorId() throws Exception {

        LocalidadeDTO localidadeDTO = new LocalidadeDTO(1l, "Santos", BigDecimal.valueOf(5000));
        given(localidadeService.buscarPorId(1l)).willReturn(localidadeDTO);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/localidade/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

}
