//package com.example.api.infra.web;
//
//import com.example.api.domain.jogador.Jogador; // Importe da entidade Jogador
//import com.example.api.domain.jogador.JogadorRepository; // Importe do repositório JogadorRepository
//import org.junit.jupiter.api.Test; // Import para o JUnit 5
//import org.springframework.beans.factory.annotation.Autowired; // Import para injeção de dependência
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc; // Import para configurar MockMvc
//import org.springframework.boot.test.context.SpringBootTest; // Import para indicar que estamos testando uma aplicação Spring Boot
//import org.springframework.http.MediaType; // Import para definir o tipo de mídia (JSON)
//import org.springframework.test.web.servlet.MockMvc; // Import para simular requisições HTTP
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; // Import para criar uma requisição POST
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath; // Import para validar respostas JSON
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; // Import para validar o status HTTP
//
//// Import adicional para conversão de objetos para JSON
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class JogadorControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private JogadorRepository jogadorRepository;
//
//    @Test
//    public void testCadastrarJogadorComEmailDuplicado() throws Exception {
//        Jogador jogadorExistente = new Jogador("Jogador1", "email@example.com", "senha123");
//        jogadorRepository.save(jogadorExistente);
//
//        Jogador jogadorNovo = new Jogador("Jogador2", "email@example.com", "senha456");
//        mockMvc.perform(post("/jogadores/cadastrar")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(jogadorNovo)))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.message").value("Este email já está em uso"));
//    }
//
//    private static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//}