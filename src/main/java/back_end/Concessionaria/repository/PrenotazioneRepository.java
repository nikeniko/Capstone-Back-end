package back_end.Concessionaria.repository;

import back_end.Concessionaria.entities.Prenotazione_test_drive;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione_test_drive, UUID> {
}