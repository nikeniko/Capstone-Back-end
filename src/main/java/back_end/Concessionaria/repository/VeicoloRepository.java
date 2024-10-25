package back_end.Concessionaria.repository;

import back_end.Concessionaria.entities.Veicolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VeicoloRepository extends JpaRepository<Veicolo, UUID> {


    Optional<Veicolo> findByModello(String modello);
}
