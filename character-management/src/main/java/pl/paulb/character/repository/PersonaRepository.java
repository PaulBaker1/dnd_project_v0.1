package pl.paulb.character.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.paulb.character.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

}
