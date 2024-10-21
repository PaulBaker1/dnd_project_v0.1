package pl.paulb.dndmanager.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dm_id", nullable = false)
    private User dungeonMaster;

    private String title;

    private String description;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
    private List<Session> sessions;

    // Getters and Setters
}
