package back_end.Concessionaria.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "RichiestaPreventivo ")
@Getter
@Setter
@NoArgsConstructor
public class RichiestaPreventivo {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email")
    private String email;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modello")
    private String modello;

    @Column(name = "anno")
    private int anno;

    @Column(name = "prezzo")
    private double prezzo;

    @Column(name = "chilometraggio")
    private int chilometraggio;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "consentoMarketing")
    private Boolean consentoMarketing;

    @Column(name = "data_richiesta")
    private LocalDate dataRichiesta;

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getChilometraggio() {
        return chilometraggio;
    }

    public void setChilometraggio(int chilometraggio) {
        this.chilometraggio = chilometraggio;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Boolean getConsentoMarketing() {
        return consentoMarketing;
    }

    public void setConsentoMarketing(Boolean consentoMarketing) {
        this.consentoMarketing = consentoMarketing;
    }

    public LocalDate getDataRichiesta() {
        return dataRichiesta;
    }

    public void setDataRichiesta(LocalDate dataRichiesta) {
        this.dataRichiesta = dataRichiesta;
    }
}










