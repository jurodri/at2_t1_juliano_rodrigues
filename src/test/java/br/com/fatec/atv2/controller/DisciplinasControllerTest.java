package br.com.fatec.atv2.controller;

import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class DisciplinasControllerTest {
    
    private final DisciplinasController disciplinasController = new DisciplinasController();

    @Test
    void testGetAllDisciplinas() {
        List<Map<String, String>> disciplinas = disciplinasController.getAllDisciplinas();
        assertEquals(2, disciplinas.size());
        assertEquals("Integração e entrega contínua", disciplinas.get(0).get("name"));
        assertEquals("Desenvolvimento web", disciplinas.get(1).get("name"));
    }

    @Test
    void testGetDisciplineById() {
        Map<String, String> disciplina = disciplinasController.getDisciplineById(1);
        assertEquals("1", disciplina.get("id"));
        assertEquals("Integração e entrega contínua", disciplina.get("name"));
    }

    @Test
    void testGetDisciplineById_NotFound() {
        Map<String, String> disciplina = disciplinasController.getDisciplineById(999);
        assertNull(disciplina);
    }

    @Test
    void testCreateDiscipline() {
        Map<String, String> novaDisciplina = Map.of("name", "Internet das coisas");

        Map<String, String> disciplina = disciplinasController.createDiscipline(novaDisciplina);

        assertEquals("Internet das coisas", disciplina.get("name"));
        assertEquals("3", disciplina.get("id"));
        
        Map<String, String> disciplinaRecuperada = disciplinasController.getDisciplineById(3);
        assertEquals("Internet das coisas", disciplinaRecuperada.get("name"));
    }
}