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
@RequestMapping("/disciplines")
@Tag(name = "Disciplines", description = "API para disciplinas de DSM")    
public class DisciplinesController {

    private final Map<Integer, Map<String,String>> disciplinesBD = new HashMap<>();

    public DisciplinesController(){
        disciplinesBD.put(1, Map.of("id", "1", "name", "Integração e entrega contínua"));
        disciplinesBD.put(2, Map.of("id", "2", "name", "Laboratório de desenvolvimento web"));
    }

    // Lista todas as disciplinas
    @Operation(summary = "Lista todas as disciplinas", description = "Retorna uma lista com todas as disciplinas cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista de disciplinas retornada com sucesso")
    @GetMapping
    public List<Map<String, String>> getAllDisciplines() {
        return new ArrayList<>(disciplinesBD.values());
    }

    // Lista a disciplina pelo id
    @Operation(summary = "Obtém uma disciplina pelo ID", description = "Retorna os detalhes de uma disciplina específica com base no ID fornecido")
    @ApiResponse(responseCode = "200", description = "Disciplina retornada com sucesso")
    @Parameter(name = "id", description = "ID da disciplina a ser obtida", required = true)
    @GetMapping("/{id}")
    public Map<String, String> getDisciplineById(@PathVariable int id){
        return disciplinesBD.get(id);
    }

    // Cria uma nova disciplina
    @Operation(summary = "Cria uma nova disciplina", description = "Adiciona uma nova disciplina ao sistema com os detalhes fornecidos")
    @ApiResponse(responseCode = "201", description = "Disciplina criada com sucesso")
    @PostMapping
    public Map<String, String> createDiscipline(@RequestBody Map<String, String> discipline) {
        Map<String, String> newDiscipline = new HashMap<>();

        newDiscipline.put("id", String.valueOf(disciplinesBD.size() + 1));
        newDiscipline.put("name", discipline.get("name"));

        disciplinesBD.put(disciplinesBD.size() + 1, newDiscipline);
        return newDiscipline;
    }
    
}