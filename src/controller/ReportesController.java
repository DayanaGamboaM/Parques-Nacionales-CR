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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author monge
 */
public class ReportesController implements Initializable {

    @FXML
    private Button btnTotalComision;
    @FXML
    private Button btnMejorValoracion;
    @FXML
    private Button btnAtras;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnActTotalComisionPDF(ActionEvent event) throws DocumentException {
        crearPdf1();
    }

    public void crearPdf1() throws DocumentException{
        Document archivo = new Document();
        String rutaDePdf = System.getProperty("user.home") + "/hola1.pdf";
        try {
            FileOutputStream file = new FileOutputStream(rutaDePdf);

            PdfWriter pdfW = PdfWriter.getInstance(archivo, file);
            pdfW.setInitialLeading(20);
            Paragraph paragraph = new Paragraph("Comisiones a pagar"); //esto crea una linea de texto, se pueden hacer varias
            Paragraph paragraph2 = new Paragraph("-"); //esto crea una linea de texto, se pueden hacer varias
                

            archivo.open();
            paragraph.setAlignment(Element.ALIGN_CENTER);//esto es para alinear tonces solo usar en la primera
            archivo.add(paragraph);//aca se agregan al archivo
            archivo.add(paragraph2);//aca se agregan al archivo
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("PDF exitoso");
            alert.setHeaderText("Archivo PDF creado con exito");
            alert.showAndWait();

            archivo.close();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void OnActMejorValoracionPDF(ActionEvent event) throws DocumentException {
        crearPdf2();
    }
    
    public void crearPdf2() throws DocumentException{
        Document archivo = new Document();
        String rutaDePdf = System.getProperty("user.home") + "/hola2.pdf";
        try {
            FileOutputStream file = new FileOutputStream(rutaDePdf);

            PdfWriter pdfW = PdfWriter.getInstance(archivo, file);
            pdfW.setInitialLeading(20);
            Paragraph paragraph = new Paragraph("Mejores y Peores Valorados"); //esto crea una linea de texto, se pueden hacer varias
            Paragraph paragraph2 = new Paragraph("+"); //esto crea una linea de texto, se pueden hacer varias
            Paragraph paragraph3 = new Paragraph("-"); //esto crea una linea de texto, se pueden hacer varias
                

            archivo.open();
            paragraph.setAlignment(Element.ALIGN_CENTER);//esto es para alinear tonces solo usar en la primera
            archivo.add(paragraph);//aca se agregan al archivo
            archivo.add(paragraph2);//aca se agregan al archivo
            archivo.add(paragraph3);//aca se agregan al archivo
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("PDF exitoso");
            alert.setHeaderText("Archivo PDF creado con exito");
            alert.showAndWait();

            archivo.close();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void OnActAtras(ActionEvent event) throws IOException {
        Parent typesRango_parent = FXMLLoader.load(getClass().getResource("/view/PrimeraVista.fxml"));
        Scene typesRango_scene = new Scene(typesRango_parent);
        
        //  typesRango_scene.getStylesheets().add("vista.fxml");
        Stage app_stage;
        app_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(typesRango_scene);
        app_stage.setTitle("Bienvenido");
        app_stage.show();
    }
    
}
