package back_end.Concessionaria.controller;

import back_end.Concessionaria.entities.RichiestaPreventivo;
import back_end.Concessionaria.services.RichiestaPreventivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/richiesta")
public class RichiestaPreventivoController {

    @Autowired
    private RichiestaPreventivoService service;

    @PostMapping
    public ResponseEntity<RichiestaPreventivo> createRichiesta(@RequestBody RichiestaPreventivo richiesta) {
        RichiestaPreventivo savedRichiesta = service.saveRichiesta(richiesta);
        return new ResponseEntity<>(savedRichiesta, HttpStatus.CREATED);
    }
}
