package pl.paulb.character.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.paulb.character.dto.PersonaDTO;
import pl.paulb.character.mapper.PersonaMapper;
import pl.paulb.character.model.Persona;
import pl.paulb.character.repository.PersonaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaService {
    private final PersonaRepository personaRepository;
    private final PersonaMapper personaMapper;

    @Autowired
    public PersonaService(PersonaRepository personaRepository, PersonaMapper personaMapper) {
        this.personaRepository = personaRepository;
        this.personaMapper = personaMapper;
    }

    @Transactional
    public PersonaDTO createPersona(PersonaDTO dto) {
        Persona persona = personaMapper.toEntity(dto);
        personaRepository.save(persona);
        return personaMapper.toDto(persona);
    }

    public PersonaDTO getPersonaById(Long id) {
        return personaRepository.findById(id).map(personaMapper::toDto).orElseThrow(() -> new IllegalArgumentException("Persona not found with id: " + id));
    }

    public List<PersonaDTO> getAllPersonas() {
        return personaRepository.findAll().stream().map(personaMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public PersonaDTO updatePersona(Long id, PersonaDTO dto) {
        Persona persona = personaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Persona not found with id: " + id));
        persona.setName(dto.getName());
        persona.setLevel(dto.getLevel());
        persona.setExperiencePoints(dto.getExperiencePoints());
        persona.setRace(dto.getRace());
        persona.getPersonaType(dto.getPersonaType());

        persona = personaRepository.save(persona);
        return personaMapper.toDto(personaRepository.save(persona));
    }

    public void deletePersona(Long id) {
        if (!personaRepository.existsById(id)) {
            throw new IllegalArgumentException("Persona not found with id: " + id);
        } else {
            personaRepository.deleteById(id);
        }
    }
}
