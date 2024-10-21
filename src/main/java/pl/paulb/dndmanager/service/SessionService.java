package pl.paulb.dndmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.paulb.dndmanager.model.Session;
import pl.paulb.dndmanager.model.SessionLog;
import pl.paulb.dndmanager.repository.SessionLogRepository;
import pl.paulb.dndmanager.repository.SessionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SessionLogRepository sessionLogRepository;

    public Session createSession(Session session) {
        return sessionRepository.save(session);
    }

    public Page<Session> getAllSessions(Pageable pageable) {
        return sessionRepository.findAll(pageable);
    }

    public Optional<Session> getSessionById(Long id) {
        return sessionRepository.findById(id);
    }

    public Optional<Page<SessionLog>> getCampaignHistory(Long campaignId, Pageable pageable) {
        return Optional.ofNullable(sessionLogRepository.findByCampaignId(campaignId, pageable));
    }

    public Optional<Session> updateSession(Long id, Session updatedSession) {
        return sessionRepository.findById(id).map(session -> {
            session.setSessionDate(updatedSession.getSessionDate());
            session.setNotes(updatedSession.getNotes());
            return sessionRepository.save(session);
        });
    }

    public boolean deleteSession(Long id) {
        return sessionRepository.findById(id).map(session -> {
            sessionRepository.delete(session);
            return true;
        }).orElse(false);
    }
}


