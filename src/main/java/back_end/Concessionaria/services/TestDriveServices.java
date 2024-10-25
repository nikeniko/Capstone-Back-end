package back_end.Concessionaria.services;

import back_end.Concessionaria.controller.TestDriveController;
import back_end.Concessionaria.entities.*;
import back_end.Concessionaria.repository.*;
import back_end.Concessionaria.tools.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TestDriveServices {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VeicoloRepository veicoloRepository;

    @Autowired
    private PrenotazioneRepository prenotazioneTestDriveRepository;

    @Autowired
    private MailSender mailSender;

    public void handleTestDriveRequest(TestDriveController.TestDriveRequest request) throws Exception {
        if (request.getVehicle() == null || request.getVehicle().isEmpty()) {
            throw new Exception("Vehicle model cannot be null or empty.");
        }
        if (request.getDate() == null || request.getDate().isEmpty()) {
            throw new Exception("Date cannot be null or empty.");
        }
        Cliente cliente = clienteRepository.findByEmail(request.getEmail())
                .orElseGet(() -> {
                    Cliente newCliente = new Cliente();
                    newCliente.setNome(request.getNome());
                    newCliente.setCognome(request.getCognome());
                    newCliente.setEmail(request.getEmail());
                    newCliente.setTelefono(request.getTelefono());
                    return clienteRepository.save(newCliente);
                });
        System.out.println("Looking for vehicle with model: " + request.getVehicle());

        Veicolo veicolo = veicoloRepository.findByModello(request.getVehicle())
                .orElseThrow(() -> new Exception("Vehicle with model " + request.getVehicle() + " not found"));

        Prenotazione_test_drive prenotazione = new Prenotazione_test_drive();
        prenotazione.setCliente(cliente);
        prenotazione.setVeicolo(veicolo);
        prenotazione.setData_test_drive(LocalDate.parse(request.getDate()));

        prenotazioneTestDriveRepository.save(prenotazione);

        mailSender.sendTestDriveConfirmationEmail(cliente, veicolo.getModello(), request.getDate());
    }
}
