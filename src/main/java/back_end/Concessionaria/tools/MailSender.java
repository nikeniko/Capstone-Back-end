package back_end.Concessionaria.tools;

import back_end.Concessionaria.entities.Cliente;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class MailSender {

    private static final Logger logger = LoggerFactory.getLogger(MailSender.class);

    @Value("${MAILGUN_KEY}")
    private String apiKey;

    @Value("${MAILGUN_DOMAIN}")
    private String domainName;

    @Value("${EMAIL_FROM}")
    private String emailFrom;

    public void sendTestDriveConfirmationEmail(Cliente cliente, String veicolo, String date) {
        try {
            HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/" + this.domainName + "/messages")
                    .basicAuth("api", this.apiKey)
                    .queryString("from", this.emailFrom)
                    .queryString("to", cliente.getEmail())
                    .queryString("subject", "Prenotazione Test Drive Confermata!")
                    .queryString("text", "Salve " + cliente.getNome() + ", \n" +
                            "Grazie per aver prenotato un test drive per il veicolo: " + veicolo + ". \n" +
                            "La tua prenotazione è stata confermata per il giorno: " + date + ". \n" +
                            "Ti aspettiamo per la tua esperienza di guida!")
                    .asJson();

            if (response.getStatus() < 200 || response.getStatus() >= 300) {
                throw new RuntimeException("Failed to send email: " + response.getStatus() + " " + response.getBody());
            }
        } catch (Exception e) {
            logger.error("Error sending email: {}", e.getMessage());
        }
    }

    public void sendQuotationConfirmationEmail(String nomeCliente, String emailCliente, String veicolo, double prezzo) {
        try {
            HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/" + this.domainName + "/messages")
                    .basicAuth("api", this.apiKey)
                    .queryString("from", this.emailFrom)
                    .queryString("to", emailCliente)
                    .queryString("subject", "Richiesta di Preventivo Confermata!")
                    .queryString("text", "Salve " + nomeCliente + ", \n\n" +
                            "Grazie per aver richiesto un preventivo per il veicolo: " + veicolo + ". \n" +
                            "Il prezzo indicativo è di €" + String.format("%.2f", prezzo) + ". \n\n" +
                            "Un nostro consulente ti contatterà a breve per maggiori dettagli. \n" +
                            "Cordiali saluti, \n\n" +
                            "Il Team di Autodrive")
                    .asJson();

            if (response.getStatus() < 200 || response.getStatus() >= 300) {
                throw new RuntimeException("Failed to send email: " + response.getBody());
            }
        } catch (Exception e) {
            logger.error("Error sending email: {}", e.getMessage());
        }
    }
}
