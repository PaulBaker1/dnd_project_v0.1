package pl.paulb.dndmanager.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "campaign_id", nullable = false)
    private Campaign campaign;

    private LocalDateTime sessionDate;

    private String status;

    private String notes;

    // Getters and Setters
}
