package pl.paulb.dndmanager.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String Name;
    private int level = 1;  // Default starting level
    private int experience = 0;  // Default XP
    private int proficiencyBonus = 2;  // Starting proficiency bonus
    private int hitPoints;
    private int constitutionModifier;  // Affects hit point// increase

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Spell> spells = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> inventory;  // Player's inventory

    @Embedded
    private Currency wallet;  // Player's wallet using the Currency class

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getProficiencyBonus() {
        return proficiencyBonus;
    }

    public void setProficiencyBonus(int proficiencyBonus) {
        this.proficiencyBonus = proficiencyBonus;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getConstitutionModifier() {
        return constitutionModifier;
    }

    public void setConstitutionModifier(int constitutionModifier) {
        this.constitutionModifier = constitutionModifier;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public Currency getWallet() {
        return wallet;
    }

    public void setWallet(Currency wallet) {
        this.wallet = wallet;
    }

    public void levelUp(Integer newLevel) {
        this.level = newLevel;
        this.proficiencyBonus = calculateProficiencyBonus(newLevel);
        this.hitPoints += calculateHitPointIncrease();
    }

    private int calculateProficiencyBonus(int level) {
        if (level >= 17) return 6;
        if (level >= 13) return 5;
        if (level >= 9) return 4;
        if (level >= 5) return 3;
        return 2;
    }

    private int calculateHitPointIncrease() {
        return (this.constitutionModifier + 1);
    }
}
