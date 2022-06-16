/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.TbValoracionesDto;
import util.FlowController;
import util.Mensaje;
import util.Respuesta;
import service.TbValoracionesService;

/**
 * FXML Controller class
 *
 * @author monge
 */
public class ValoracionController extends Controller implements Initializable {

    @FXML
    private Button btnAtras;
    @FXML
    private ImageView imgMono1;
    @FXML
    private ImageView imgMono2;
    @FXML
    private ImageView imgMono3;
    @FXML
    private ImageView imgMono4;
    @FXML
    private ImageView imgMono5;
    @FXML
    private Button btnGuardar;
    @FXML
    private TextField txtParId;
    @FXML
    private TextField txtRefId;
    
    @FXML
    private ImageView imgMonoArr1;
    @FXML
    private ImageView imgMonoArr2;
    @FXML
    private ImageView imgMonoArr3;
    @FXML
    private ImageView imgMonoArr4;
    @FXML
    private ImageView imgMonoArr5;
    
    TbValoracionesDto tbvaloracionesDto;
    @FXML
    private TextField txtComentario;
    @FXML
    private TextField txtIdValoracion;
    @FXML
    private TextField txtPuntaje;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
//        FlowController.getInstance().goViewInWindow("PrimeraVista", "principal");
//        getStage().showAndWait();
    }

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void OnActGuardar(ActionEvent event) {
        try {
            TbValoracionesService service = new TbValoracionesService();
//            PaisesService service = (PaisesService) AppContext.getInstance().get("PaisesService");
            Respuesta respuesta = service.guardarValoracion(tbvaloracionesDto);
            if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar valoracion", getStage(), respuesta.getMensaje());
            } else {
                    unbindValoracion();
                    tbvaloracionesDto = (TbValoracionesDto) respuesta.getResultado("TbValoraciones");
                    bindValoracion(false);
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar valoracion", getStage(), "Valoracion actualizado correctamente.");
            }  
        } catch (Exception ex) {
            Logger.getLogger(ValoracionController.class.getName()).log(Level.SEVERE, "Error guardando la valoracion.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar valoracion", getStage(), "Ocurrio un error guardando la valoracion.");
        }
    }
    
    private void nuevoValoracion() {
        unbindValoracion();
        tbvaloracionesDto = new TbValoracionesDto();
        bindValoracion(true);
        txtIdValoracion.clear();
        txtIdValoracion.requestFocus();
    }
    
    private void bindValoracion(Boolean nuevo) {
        if(!nuevo){
            txtIdValoracion.textProperty().bind(tbvaloracionesDto.valId);
        }
        txtPuntaje.textProperty().bindBidirectional(tbvaloracionesDto.valPuntaje);
        txtComentario.textProperty().bindBidirectional(tbvaloracionesDto.valComentario);
        

    }

    private void unbindValoracion() {
        txtIdValoracion.textProperty().unbind();
        txtPuntaje.textProperty().unbindBidirectional(tbvaloracionesDto.valPuntaje);
        txtComentario.textProperty().unbindBidirectional(tbvaloracionesDto.valComentario);
        
    }
    
    private void ComprobarValoracion(Image image){
        if(imgMonoArr1.getImage()==null){
            imgMonoArr1.setImage(image);
        }
    }
    private void ComprobarValoracion2(Image image){
        if(imgMonoArr2.getImage()==null){
            imgMonoArr2.setImage(image);
        }
    }
    private void ComprobarValoracion3(Image image){
        if(imgMonoArr3.getImage()==null){
            imgMonoArr3.setImage(image);
        }
    }
    private void ComprobarValoracion4(Image image){
        if(imgMonoArr4.getImage()==null){
            imgMonoArr4.setImage(image);
        }
    }
    private void ComprobarValoracion5(Image image){
        if(imgMonoArr5.getImage()==null){
            imgMonoArr5.setImage(image);
        }
    }

    @FXML
    private void MouseClicked1(MouseEvent event) {
        CheckNull(imgMono1.getImage());
        imgMono1.setDisable(true);
        
        ComprobarValoracion(imgMono1.getImage());
       
        txtPuntaje.setText("1");
    }

    @FXML
    private void MouseClicked2(MouseEvent event) {
        CheckNull(imgMono1.getImage());
        imgMono1.setDisable(true);
        CheckNull2(imgMono2.getImage());
        imgMono2.setDisable(true);
        
        ComprobarValoracion2(imgMono2.getImage());
        
        txtPuntaje.setText("2");
    }

    @FXML
    private void MouseClicked3(MouseEvent event) {
        CheckNull(imgMono1.getImage());
        imgMono1.setDisable(true);
        CheckNull2(imgMono2.getImage());
        imgMono2.setDisable(true);
        CheckNull3(imgMono3.getImage());
        imgMono3.setDisable(true);
        
        ComprobarValoracion3(imgMono3.getImage());
        
        txtPuntaje.setText("3");
    }

    @FXML
    private void MouseClicked4(MouseEvent event) {
        CheckNull(imgMono1.getImage());
        imgMono1.setDisable(true);
        CheckNull2(imgMono2.getImage());
        imgMono2.setDisable(true);
        CheckNull3(imgMono3.getImage());
        imgMono3.setDisable(true);
        CheckNull4(imgMono4.getImage());
        imgMono4.setDisable(true);
        
        ComprobarValoracion4(imgMono4.getImage());
        
        txtPuntaje.setText("4");
    }

    @FXML
    private void MouseClicked5(MouseEvent event) {
        CheckNull(imgMono1.getImage());
        imgMono1.setDisable(true);
        CheckNull2(imgMono2.getImage());
        imgMono2.setDisable(true);
        CheckNull3(imgMono3.getImage());
        imgMono3.setDisable(true);
        CheckNull4(imgMono4.getImage());
        imgMono4.setDisable(true);
        CheckNull5(imgMono5.getImage());
        imgMono5.setDisable(true);
        
        ComprobarValoracion5(imgMono5.getImage());
        
        txtPuntaje.setText("5");
    }
    
    private void CheckNull(Image image){
        if(imgMonoArr1.getImage()==null){
            imgMonoArr1.setImage(image);
        }
    }
    private void CheckNull2(Image image){
        if(imgMonoArr2.getImage()==null){
            imgMonoArr2.setImage(image);
        }
    }
    private void CheckNull3(Image image){
        if(imgMonoArr3.getImage()==null){
            imgMonoArr3.setImage(image);
        }
    }
    private void CheckNull4(Image image){
        if(imgMonoArr4.getImage()==null){
            imgMonoArr4.setImage(image);
        }
    }
    private void CheckNull5(Image image){
        if(imgMonoArr5.getImage()==null){
            imgMonoArr5.setImage(image);
        }
    }
}
