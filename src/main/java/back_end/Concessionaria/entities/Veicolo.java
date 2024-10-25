package back_end.Concessionaria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Veicoli")
public class Veicolo {

    @Id
    @GeneratedValue
    private UUID id;

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

    @Column(name = "disponibilita")
    private Boolean disponibilita;

    @Column(name = "immagine")
    private String immagine;

    @OneToMany(mappedBy = "veicolo")
    @JsonIgnore
    private List<Prenotazione_test_drive> prenotazioneTestDriveList;


    public Veicolo(String marca, String modello, int anno, double prezzo, int chilometraggio, String descrizione, Boolean disponibilita, String immagine) {
        this.marca = marca;
        this.modello = modello;
        this.anno = anno;
        this.prezzo = prezzo;
        this.chilometraggio = chilometraggio;
        this.descrizione = descrizione;
        this.disponibilita = disponibilita;
        this.immagine = immagine;
    }

    public Veicolo() {
    }

    public UUID getId() {
        return id;
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

    public Boolean getDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(Boolean disponibilita) {
        this.disponibilita = disponibilita;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public List<Prenotazione_test_drive> getPrenotazioneTestDriveList() {
        return prenotazioneTestDriveList;
    }

    public void setPrenotazioneTestDriveList(List<Prenotazione_test_drive> prenotazioneTestDriveList) {
        this.prenotazioneTestDriveList = prenotazioneTestDriveList;
    }
}
