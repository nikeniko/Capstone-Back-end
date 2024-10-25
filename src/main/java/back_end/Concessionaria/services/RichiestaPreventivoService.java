package back_end.Concessionaria.services;

import back_end.Concessionaria.entities.RichiestaPreventivo;
import back_end.Concessionaria.repository.RichiestaPreventivoRepository;
import back_end.Concessionaria.tools.MailSender;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RichiestaPreventivoService {

    @Autowired
    private RichiestaPreventivoRepository richiestaPreventivoRepository;

    @Autowired
    private MailSender mailSender;

    @Transactional
    public RichiestaPreventivo saveRichiesta(RichiestaPreventivo richiestaPreventivo) {
        // Save the quotation request
        richiestaPreventivoRepository.save(richiestaPreventivo);

        // Prepare data from RichiestaPreventivo for the email
        String nomeCliente = richiestaPreventivo.getNome();
        String emailCliente = richiestaPreventivo.getEmail();
        String veicoloInfo = richiestaPreventivo.getMarca() + " " + richiestaPreventivo.getModello();
        double prezzo = richiestaPreventivo.getPrezzo();

        // Send a quotation confirmation email
        mailSender.sendQuotationConfirmationEmail(nomeCliente, emailCliente, veicoloInfo, prezzo);

        return richiestaPreventivo;
    }
}
