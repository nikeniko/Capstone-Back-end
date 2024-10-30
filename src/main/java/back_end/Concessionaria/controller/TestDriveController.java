package back_end.Concessionaria.controller;

import back_end.Concessionaria.services.TestDriveServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/testdrive")
public class TestDriveController {

    @Autowired
    private TestDriveServices testDriveService;

    @PostMapping("/request")
    public ResponseEntity<String> requestTestDrive(@RequestBody TestDriveRequest request) {
        System.out.println("Received test drive request: " + request);
        System.out.println("Vehicle Model: " + request.getVehicle());

        try {
            testDriveService.handleTestDriveRequest(request);
            return ResponseEntity.status(HttpStatus.OK).body("Test Drive booking submitted successfully!");
        } catch (Exception e) {
            System.err.println("Error processing test drive request: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process the test drive booking.");
        }
    }
    public static class TestDriveRequest {
        private String nome;
        private String cognome;
        private String email;
        private String telefono;
        private String vehicle;
        private String date;

        // Getters and setters
        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }

        public String getCognome() { return cognome; }
        public void setCognome(String cognome) { this.cognome = cognome; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getTelefono() { return telefono; }
        public void setTelefono(String telefono) { this.telefono = telefono; }

        public String getVehicle() { return vehicle; }
        public void setVehicle(String vehicle) { this.vehicle = vehicle; }

        public String getDate() { return date; }
        public void setDate(String date) { this.date = date; }
    }
}
