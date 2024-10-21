package pl.paulb.dndmanager.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "session_logs")
public class SessionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "campaign_id", nullable = false)
    private Campaign campaign;

    @Column(name = "log_date", nullable = false)
    private LocalDateTime logDate;

    @Column(name = "log_notes", columnDefinition = "TEXT")
    private String logNotes;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public LocalDateTime getLogDate() {
        return logDate;
    }

    public void setLogDate(LocalDateTime logDate) {
        this.logDate = logDate;
    }

    public String getLogNotes() {
        return logNotes;
    }

    public void setLogNotes(String logNotes) {
        this.logNotes = logNotes;
    }
}
