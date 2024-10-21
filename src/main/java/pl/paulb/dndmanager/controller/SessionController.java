package pl.paulb.dndmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.paulb.dndmanager.model.Session;
import pl.paulb.dndmanager.model.SessionLog;
import pl.paulb.dndmanager.service.CampaignService;
import pl.paulb.dndmanager.service.SessionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private CampaignService campaignService;

    @PostMapping
    public ResponseEntity<Session> createSession(@RequestBody Session session) {
        Session newSession = sessionService.createSession(session);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSession);
    }

    @GetMapping
    public ResponseEntity<Page<Session>> getAllSessions(Pageable pageable) {
        Page<Session> sessions = sessionService.getAllSessions(pageable);
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Session> getSessionById(@PathVariable Long id) {
        Optional<Session> session = sessionService.getSessionById(id);
        return session.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Session> updateSession(@PathVariable Long id, @RequestBody Session updatedSession) {
        Optional<Session> session = sessionService.updateSession(id, updatedSession);
        return session.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        boolean isDeleted = sessionService.deleteSession(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<Optional<Page<SessionLog>>> getCampaignHistory(@PathVariable Long id, Pageable pageable) {
        Optional<Page<SessionLog>> history = campaignService.getCampaignHistory(id, pageable);
        return ResponseEntity.ok(history);
    }

}


