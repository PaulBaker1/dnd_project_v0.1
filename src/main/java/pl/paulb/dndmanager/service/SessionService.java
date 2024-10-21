package pl.paulb.dndmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.paulb.dndmanager.model.Session;
import pl.paulb.dndmanager.repository.SessionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public Session createSession(Session session) {
        return sessionRepository.save(session);
    }

    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    public Optional<Session> getSessionById(Long id) {
        return sessionRepository.findById(id);
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


