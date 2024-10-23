package pl.paulb.dndmanager.controller;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.paulb.dndmanager.model.Session;
import pl.paulb.dndmanager.model.SessionLog;
import pl.paulb.dndmanager.service.CampaignService;
import pl.paulb.dndmanager.service.SessionService;

import java.time.Duration;
import java.util.Optional;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    // Define a bucket for rate limiting
    private final Bucket bucket = Bucket.builder()
            .addLimit(Bandwidth.classic(10, Refill.intervally(10, Duration.ofMinutes(1))))
            .build();
    @Autowired
    private SessionService sessionService;
    @Autowired
    private CampaignService campaignService;

    @PostMapping
    public ResponseEntity<Session> createSession(@RequestBody Session session) {
        if (bucket.tryConsume(1)) {
            // Proceed with creating the session if rate limit is not exceeded
            Session createdSession = sessionService.createSession(session);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSession);
        } else {
            // Respond with 429 Too Many Requests if the rate limit is exceeded
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
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


