package back_end.Concessionaria.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Prenotazione_test_drive")
@Getter
@Setter
@NoArgsConstructor
public class Prenotazione_test_drive {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(name = "data_test_drive")
    private LocalDate data_test_drive;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "veicolo_id")
    private Veicolo veicolo;

    public UUID getId() {
        return id;
    }

    public LocalDate getData_test_drive() {
        return data_test_drive;
    }

    public void setData_test_drive(LocalDate data_test_drive) {
        this.data_test_drive = data_test_drive;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veicolo getVeicolo() {
        return veicolo;
    }

    public void setVeicolo(Veicolo veicolo) {
        this.veicolo = veicolo;
    }
}
