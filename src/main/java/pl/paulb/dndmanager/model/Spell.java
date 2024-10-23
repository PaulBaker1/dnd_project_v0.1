package pl.paulb.dndmanager.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Spell {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String description;
    private int maxUsesPerDay;
    private int currentUses;

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

    public int getMaxUsesPerDay() {
        return maxUsesPerDay;
    }

    public void setMaxUsesPerDay(int maxUsesPerDay) {
        this.maxUsesPerDay = maxUsesPerDay;
    }

    public int getCurrentUses() {
        return currentUses;
    }

    public void setCurrentUses(int currentUses) {
        this.currentUses = currentUses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spell spell = (Spell) o;
        return Objects.equals(id, spell.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
