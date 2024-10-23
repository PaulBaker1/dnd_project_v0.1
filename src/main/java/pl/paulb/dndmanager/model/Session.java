package pl.paulb.dndmanager.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import pl.paulb.dndmanager.deserializer.CustomDateTimeDeserializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "sessions")
public class Session {

    // DateTimeFormatter to handle nanoseconds
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.n");
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private LocalDateTime sessionDate;
    @Column(name = "notes")
    private String notes;

    // PostLoad method if you're manually parsing the date
    /*@PostLoad
    public void postLoad() {
        try {
            this.sessionDate = LocalDateTime.parse(sessionDate.toString(), FORMATTER);
        } catch (DateTimeParseException e) {
            // Handle exception if the format is wrong
            e.printStackTrace();
        }
    }*/

    @PostLoad
    public void postLoad() {
        try {
            this.sessionDate = LocalDateTime.parse(sessionDate.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
    }


    @PrePersist
    @PreUpdate
    public void truncateDateTime() {
        if (sessionDate != null) {
            sessionDate = sessionDate.truncatedTo(ChronoUnit.MICROS);
        }
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDateTime sessionDate) {
        this.sessionDate = sessionDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
