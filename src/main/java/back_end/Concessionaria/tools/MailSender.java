package back_end.Concessionaria.tools;

import back_end.Concessionaria.entities.Cliente;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailSender {

    @Value("${MAILGUN_KEY}")
    private String apiKey;

    @Value("${MAILGUN_DOMAIN}")
    private String domainName;

    @Value("${EMAIL_FROM}")
    private String emailFrom;

    /**
     * Send a test drive booking confirmation email to the client.
     *
     * @param cliente The client details for sending the email.
     * @param veicolo The vehicle details for the test drive.
     * @param date    The date of the test drive.
     */
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

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed to send email: " + response.getBody());
            }
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }

    /**
     * Send a quotation request confirmation email to the client.
     *
     * @param nomeCliente The client's name.
     * @param emailCliente The client's email address.
     * @param veicolo The vehicle details for the quotation.
     * @param prezzo  The quoted price for the vehicle.
     */
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

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed to send email: " + response.getBody());
            }
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}
