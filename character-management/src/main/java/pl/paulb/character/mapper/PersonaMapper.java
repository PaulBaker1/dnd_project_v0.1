package pl.paulb.character.mapper;

import org.springframework.stereotype.Component;
import pl.paulb.character.dto.*;
import pl.paulb.character.model.Persona;

import java.util.stream.Collectors;

@Component
public class PersonaMapper {

    public PersonaDTO toDto(Persona persona) {
        PersonaDTO dto = new PersonaDTO();
        dto.setId(persona.getId());
        dto.setName(persona.getName());
        dto.setLevel(persona.getLevel());
        dto.setExperiencePoints(persona.getExperiencePoints());
        dto.setRace(persona.getRace());
        dto.setPersonaType(persona.getPersonaType(dto.getPersonaType()));
        dto.setAttributes(persona.getAttributes().stream()
                .map(attr -> new AttributeDTO(attr.getName(), attr.getValue()))
                .collect(Collectors.toList()));
        dto.setSkills(persona.getSkills().stream()
                .map(skill -> new SkillDTO(skill.getId(), skill.getName(), skill.getDescription(), skill.getRequiredLevel()))
                .collect(Collectors.toList()));
        dto.setSpells(persona.getSpells().stream()
                .map(spell -> new SpellDTO(spell.getId(), spell.getName(), spell.getDescription(), spell.getManaCost(), spell.getRequiredLevel()))
                .collect(Collectors.toList()));
        dto.setInventoryItems(persona.getInventory().stream()
                .map(item -> new InventoryItemDTO(item.getId(), item.getName(), item.getDescription(), item.getQuantity()))
                .collect(Collectors.toList()));
        return dto;
    }

    public Persona toEntity(PersonaDTO dto) {
        Persona persona = new Persona();
        persona.setName(dto.getName());
        persona.setLevel(dto.getLevel());
        persona.setExperiencePoints(dto.getExperiencePoints());
        persona.setRace(dto.getRace());
        persona.setPersonaType(dto.getPersonaType());
        // Map attributes, skills, spells, and inventory items if necessary
        return persona;
    }
}
