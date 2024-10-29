package pl.paulb.character.dto;

public class AttributeDTO {
    private String name;
    private int value;

    public AttributeDTO(String name, Object value) {
        this.name = name;
        this.value = (int) value;
    }

    // Getters and setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
