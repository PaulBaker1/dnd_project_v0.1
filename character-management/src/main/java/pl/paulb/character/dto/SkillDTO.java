package pl.paulb.character.dto;

import com.sun.jdi.ClassType;
import pl.paulb.character.model.PersonaType;
import pl.paulb.character.model.RaceType;

import java.util.List;

public class SkillDTO {
    private Long id;
    private String name;
    private String description;
    private int requiredLevel;

    public SkillDTO(Long id, String name, String description, int requiredLevel) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.requiredLevel = requiredLevel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel;
    }
}
