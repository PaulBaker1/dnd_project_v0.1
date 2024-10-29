package pl.paulb.character.dto;

import pl.paulb.character.model.PersonaType;
import pl.paulb.character.model.RaceType;

import java.util.List;


public class PersonaDTO {

    private Long id;
    private String name;
    private int level;
    private int experiencePoints;
    private RaceType race;
    private PersonaType personaType;
    private List<AttributeDTO> attributes;
    private List<SkillDTO> skills;
    private List<SpellDTO> spells;
    private List<InventoryItemDTO> inventoryItems;


    public PersonaDTO() {
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public RaceType getRace() {
        return race;
    }

    public void setRace(RaceType race) {
        this.race = race;
    }

    public PersonaType getPersonaType() {
        return personaType;
    }

    public void setPersonaType(PersonaType personaType) {
        this.personaType = personaType;
    }

    public List<AttributeDTO> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeDTO> attributes) {
        this.attributes = attributes;
    }

    public List<SkillDTO> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillDTO> skills) {
        this.skills = skills;
    }

    public List<SpellDTO> getSpells() {
        return spells;
    }

    public void setSpells(List<SpellDTO> spells) {
        this.spells = spells;
    }

    public List<InventoryItemDTO> getInventoryItems() {
        return inventoryItems;
    }

    public void setInventoryItems(List<InventoryItemDTO> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }


}
