/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import static javafx.scene.control.Alert.AlertType.ERROR;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import util.Mensaje;


/**
 * FXML Controller class
 *
 * @author monge
 */
public class EntradasController extends Controller implements Initializable {

    @FXML
    private Button btnAtras;
    @FXML
    private DatePicker dateDiasEntrada;
    @FXML
    private Button btnAgregar;
    @FXML
    private ChoiceBox<String> choiceTipoTarjeta;
    @FXML
    private TextField txtNombreVisitante;
    @FXML
    private Button btnVender;
    @FXML
    private Label lblImpuesto;
    @FXML
    private Label lblSubtotal;
    @FXML
    private Label lblTotal;
    @FXML
    private ChoiceBox<String> choiceLugarEntrada;
    @FXML
    private TextField txtSitio;
    @FXML
    private TextField txtFechaVisita;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtIdEntrada;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        choiceTipoTarjeta.setItems(FXCollections.observableArrayList("VISA","Mastercard","American Express","Dinner Club","Union Pay"));

        choiceLugarEntrada.setItems(FXCollections.observableArrayList("Parques","Refugios"));

//        txtIdPais.setTextFormatter(Formato.getInstance().integerFormat());
//        txtNombrePais.setTextFormatter(Formato.getInstance().letrasFormat(30));
//        
//        txtIdJugador.setTextFormatter(Formato.getInstance().integerFormat());
//        txtNombre.setTextFormatter(Formato.getInstance().letrasFormat(30));
//        txtApellidos.setTextFormatter(Formato.getInstance().letrasFormat(30));
    }    

    @FXML
    private void OnActAtras(ActionEvent event) throws IOException  {

        Parent typesRango_parent = FXMLLoader.load(getClass().getResource("/view/PrimeraVista.fxml"));
        Scene typesRango_scene = new Scene(typesRango_parent);
        
        //  typesRango_scene.getStylesheets().add("vista.fxml");
        Stage app_stage;
        app_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(typesRango_scene);
        app_stage.setTitle("Bienvenido");
        app_stage.show();
//        FlowController.getInstance().goViewInWindow("PrimeraVista", "principal");
//        getStage().close();
    }

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void OnActAgregar(ActionEvent event) {
    }

    @FXML
    private void OnActVender(ActionEvent event) throws DocumentException {
        
        String micorreo = "dayamonge2022@gmail.com";
        String micontrasena = "sewmrkhpdkpzcqcm";
        String receptor = "dayamonge2022@gmail.com";//aca tiene que trar el correo de la DB del visitante

        
        try {
            Properties p = new Properties();
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            p.setProperty("mail.smtp.port", "587");
            p.setProperty("mail.smtp.user", micorreo);
            p.setProperty("mail.smtp.auth", "true");
            Session s = Session.getDefaultInstance(p);
            MimeMessage mensaje = new MimeMessage(s);
            mensaje.setFrom(new InternetAddress(micorreo));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
            mensaje.setSubject("Confirmacion");//titulo del correo
            mensaje.setText("El sistema de registro del SINAC confirma su venta de entrada al parque/refugio solicitado, agradecemos su compra, disfrute de su visita!"); //mensae del correo
            Transport t = s.getTransport("smtp");
            t.connect(micorreo, micontrasena);
            t.sendMessage(mensaje, mensaje.getAllRecipients());
            t.close();
            new Mensaje().show(Alert.AlertType.INFORMATION, "Informacion", "Correo Enviado de Manera Satisfactoria");
        } catch (Exception e) {
            new Mensaje().showModal(ERROR, "Error de envio ", getStage(), "El correo no se a enviado");
        }
    }
    
    
}
