package pl.paulb.dndmanager;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.paulb.dndmanager.model.Session;
import pl.paulb.dndmanager.repository.SessionRepository;
import pl.paulb.dndmanager.service.SessionService;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class SessionServiceTest {

    @Autowired
    private SessionService sessionService;

    @MockBean
    private SessionRepository sessionRepository;

    @Test
    public void testCreateSession() {
        Session session = new Session();
        session.setSessionDate(LocalDateTime.now());
        session.setNotes("Test session");

        Mockito.when(sessionRepository.save(any(Session.class))).thenReturn(session);

        Session savedSession = sessionService.createSession(session);
        assertNotNull(savedSession);
    }
}

