package br.com.fatec.atv2.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/disciplinas")
@Tag(name = "Disciplinas", description = "API para gerenciamento de disciplinas acadêmicas")    
public class DisciplinasController {

    private final Map<Integer, Map<String,String>> DisciplinasBD = new HashMap<>();

    public DisciplinasController(){
        DisciplinasBD.put(1, Map.of("id", "1", "name", "Integração e entrega contínua"));
        DisciplinasBD.put(2, Map.of("id", "2", "name", "Desenvolvimento web"));
    }

    // Lista todas as disciplinas
    @Operation(summary = "Obter catálogo de disciplinas", description = "Recupera a lista completa de todas as disciplinas cadastradas")
    @ApiResponse(responseCode = "200", description = "Catálogo obtido com sucesso")
    @GetMapping
    public List<Map<String, String>> getAllDisciplinas() {
        return new ArrayList<>(DisciplinasBD.values());
    }

    // Lista a disciplina pelo id
    @Operation(summary = "Buscar disciplina por identificador", description = "Recupera os dados de uma disciplina específica baseado no ID fornecido")
    @ApiResponse(responseCode = "200", description = "Disciplina encontrada com sucesso")
    @Parameter(name = "id", description = "Identificador único da disciplina", required = true)
    @GetMapping("/{id}")
    public Map<String, String> getDisciplineById(@PathVariable int id){
        return DisciplinasBD.get(id);
    }

    // Cria uma nova disciplina
    @Operation(summary = "Adicionar nova disciplina", description = "Cadastra uma nova disciplina no sistema com as informações fornecidas")
    @ApiResponse(responseCode = "201", description = "Disciplina adicionada com sucesso")
    @PostMapping
    public Map<String, String> createDiscipline(@RequestBody Map<String, String> discipline) {
        Map<String, String> newDiscipline = new HashMap<>();

        newDiscipline.put("id", String.valueOf(DisciplinasBD.size() + 1));
        newDiscipline.put("name", discipline.get("name"));

        DisciplinasBD.put(DisciplinasBD.size() + 1, newDiscipline);
        return newDiscipline;
    }
    
}