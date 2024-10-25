package back_end.Concessionaria.repository;


import back_end.Concessionaria.entities.RichiestaPreventivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RichiestaPreventivoRepository extends JpaRepository<RichiestaPreventivo, Long> {
}