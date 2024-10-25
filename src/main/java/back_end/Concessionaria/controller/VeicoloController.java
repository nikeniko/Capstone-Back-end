package back_end.Concessionaria.controller;

import back_end.Concessionaria.entities.Veicolo;
import back_end.Concessionaria.repository.VeicoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/veicoli")
public class VeicoloController {

    @Autowired
    private VeicoloRepository veicoloRepository;

    @PostMapping("/create")
    public ResponseEntity<Veicolo> createVeicolo(@RequestBody Veicolo veicolo) {
        try {
            Veicolo newVeicolo = veicoloRepository.save(veicolo);
            return new ResponseEntity<>(newVeicolo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Veicolo>> getAllVeicoli() {
        try {
            List<Veicolo> veicoli = veicoloRepository.findAll();
            if (veicoli.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(veicoli, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veicolo> getVeicoloById(@PathVariable("id") UUID id) {
        Optional<Veicolo> veicoloData = veicoloRepository.findById(id);
        if (veicoloData.isPresent()) {
            return new ResponseEntity<>(veicoloData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Veicolo> updateVeicolo(@PathVariable("id") UUID id, @RequestBody Veicolo veicoloDetails) {
        Optional<Veicolo> veicoloData = veicoloRepository.findById(id);

        if (veicoloData.isPresent()) {
            Veicolo updatedVeicolo = veicoloData.get();
            updatedVeicolo.setMarca(veicoloDetails.getMarca());
            updatedVeicolo.setModello(veicoloDetails.getModello());
            updatedVeicolo.setAnno(veicoloDetails.getAnno());
            updatedVeicolo.setPrezzo(veicoloDetails.getPrezzo());
            updatedVeicolo.setChilometraggio(veicoloDetails.getChilometraggio());
            updatedVeicolo.setDescrizione(veicoloDetails.getDescrizione());
            updatedVeicolo.setDisponibilita(veicoloDetails.getDisponibilita());
            updatedVeicolo.setImmagine(veicoloDetails.getImmagine());

            return new ResponseEntity<>(veicoloRepository.save(updatedVeicolo), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteVeicolo(@PathVariable("id") UUID id) {
        try {
            veicoloRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
