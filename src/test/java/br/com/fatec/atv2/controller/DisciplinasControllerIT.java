package br.com.fatec.atv2.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DisciplinasController.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DisciplinasControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllDisciplinas() throws Exception {
        mockMvc.perform(get("/disciplinas"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].name").value("Integração e entrega contínua"))
            .andExpect(jsonPath("$[1].name").value("Desenvolvimento web"));
    }

    @Test
    void testGetDisciplineById() throws Exception {
        mockMvc.perform(get("/disciplinas/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("1"))
            .andExpect(jsonPath("$.name").value("Integração e entrega contínua"));
    }

    @Test
    void testGetDisciplineById_NotFound() throws Exception {
        mockMvc.perform(get("/disciplinas/999"))
            .andExpect(status().isOk())
            .andExpect(content().string(""));
    }

    @Test
    void testCreateDiscipline() throws Exception {
        String novaDisciplina = """
                {
                    "name": "Internet das coisas"
                }
                """;
        mockMvc.perform(post("/disciplinas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(novaDisciplina))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("3"))
            .andExpect(jsonPath("$.name").value("Internet das coisas"));
    }
}