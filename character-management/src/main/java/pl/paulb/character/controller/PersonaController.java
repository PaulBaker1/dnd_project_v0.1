package pl.paulb.character.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.paulb.character.dto.PersonaDTO;
import pl.paulb.character.service.PersonaService;

import java.util.List;

@RestController
@RequestMapping("/api/persona")
public class PersonaController {
    private final PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonaDTO createPersona(PersonaDTO dto) {
        return personaService.createPersona(dto);
    }

    @GetMapping("/{id}")
    public PersonaDTO getPersonaById(@PathVariable Long id) {
        return personaService.getPersonaById(id);
    }

    @GetMapping
    public List<PersonaDTO> getAllPersonas() {
        return personaService.getAllPersonas();
    }

    @PutMapping("/{id}")
    public PersonaDTO updatePersona(@PathVariable Long id, PersonaDTO dto) {
        return personaService.updatePersona(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletePersona(@PathVariable Long id) {
        personaService.deletePersona(id);
    }
}
