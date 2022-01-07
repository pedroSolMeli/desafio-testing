package com.mercadolibre.desafiotesting.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.desafiotesting.dto.CasaRequestDTO;
import com.mercadolibre.desafiotesting.dto.ComodoRequestDTO;
import com.mercadolibre.desafiotesting.model.Casa;
import com.mercadolibre.desafiotesting.model.Comodo;
import com.mercadolibre.desafiotesting.model.Localidade;
import com.mercadolibre.desafiotesting.repository.CasaRepository;
import com.mercadolibre.desafiotesting.repository.LocalidadeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CasaControllerTest {

    @Autowired
    CasaRepository casaRepository;

    @Autowired
    LocalidadeRepository localidadeRepository;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    Localidade localidade;
    Casa casa;

    @Test
    public void deveRetornarListaCasa() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/casa"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void deveRetornarCasaPorId() throws Exception {
        localidade = new Localidade(1l,"Santos",BigDecimal.valueOf(500.0));
        localidadeRepository.save(localidade);

        Comodo comodoDto = new Comodo(1l,"Quarto",3.0,3.0,3.0);
        List<Comodo> listaComodo = new ArrayList();
        listaComodo.add(comodoDto);

        casa = new Casa(1l,"Casa", localidade,listaComodo, 5.0);
        casaRepository.save(casa);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/casa/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void deveCriarCasaPorIdComLetraDoNomeMinuscula() throws Exception {
        localidade = new Localidade(1l,"Santos",BigDecimal.valueOf(500.0));
        localidadeRepository.save(localidade);

        String payLoad = "{\"nome\": \"testeCasa\",\"localidadeId\": 1, \"comodos\":[{ \"nome\": \"quarto\",\"largura\": \"5\",\"comprimento\": 10}]}";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/casa")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void deveCriarCasaPorIdComLetraDoNomeMinusculaRetornarSemSucesso() throws Exception {
        localidade = new Localidade(1l,"Santos",BigDecimal.valueOf(500.0));
        localidadeRepository.save(localidade);

        String payLoad = "{\"nome\": \"testeCasa\",\"localidadeId\": 1, \"comodos\":[{ \"nome\": \"quarto\",\"largura\": \"5\",\"comprimento\": 10}]}";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/casa")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void deveCriaCasaPorIdComSucesso() throws Exception {
        localidade = new Localidade(1l,"Santos",BigDecimal.valueOf(500.0));
        localidadeRepository.save(localidade);

        String payLoad = "{\"nome\": \"Testecasa\",\"localidadeId\": 1, \"comodos\":[{ \"nome\": \"Quarto\",\"largura\": \"5\",\"comprimento\": 10}]}";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/casa")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveDeletarCasaPorId() throws Exception {
        localidade = new Localidade(1l,"Santos",BigDecimal.valueOf(500.0));
        localidadeRepository.save(localidade);

        Comodo comodoDto = new Comodo(1l,"Quarto",3.0,3.0,3.0);
        List<Comodo> listaComodo = new ArrayList();
        listaComodo.add(comodoDto);

        casa = new Casa(1l,"Casa", localidade,listaComodo, 5.0);
        casaRepository.save(casa);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/casa/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}
