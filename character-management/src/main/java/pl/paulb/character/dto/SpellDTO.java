package pl.paulb.character.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link pl.paulb.character.model.Spell}
 */
@Value
public class SpellDTO implements Serializable {
    Long id;
    String name;
    String description;
    int manaCost;
    int requiredLevel;

    public SpellDTO(Long id, String name, String description, int manaCost, int requiredLevel) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.manaCost = manaCost;
        this.requiredLevel = requiredLevel;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }
}