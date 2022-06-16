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
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.TbEspeciesDto;
import service.TbEspeciesService;
import util.Formato;
import util.Mensaje;
import util.Respuesta;
import controller.Controller;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import util.AppContext;

/**
 * FXML Controller class
 *
 * @author monge
 */
public class EspeciesController extends Controller implements Initializable {

    @FXML
    private Button btnAtras;
    @FXML
    private Button btnEditar;
    @FXML
    private ComboBox<String> ComboBEspecies;
    @FXML
    private TextField txtFiltro1;
    @FXML
    private Button btnFiltrar;
    @FXML
    private Button btnEliminar;
    @FXML
    private TextField txtIdEspecies;
    @FXML
    private TextField txtTipoEspecie;
    @FXML
    private TextField txtNomCientifico;
    @FXML
    private TextField txtNomComun;
    @FXML
    private TextField txtPeso;
    @FXML
    private TextField txtTamaño;
    @FXML
    private TextField txtCaracteristicas;
    @FXML
    private Button btnGuardar;
    
    TbEspeciesDto tbespeciesDto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ComboBEspecies.setItems(FXCollections.observableArrayList("Parques","Refugios","Especies","Ubicacion","Precios","Tarjetas"));
    
        txtIdEspecies.setTextFormatter(Formato.getInstance().integerFormat());
        txtTipoEspecie.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtNomCientifico.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtNomComun.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtPeso.setTextFormatter(Formato.getInstance().integerFormat());
        txtTamaño.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtCaracteristicas.setTextFormatter(Formato.getInstance().letrasFormat(100));
        
        tbespeciesDto = new TbEspeciesDto();
        nuevoEspecie();
    
    }    

    @FXML
    private void OnActAtras(ActionEvent event) throws IOException {
        Parent typesRango_parent = FXMLLoader.load(getClass().getResource("/view/Mantenimiento.fxml"));
        Scene typesRango_scene = new Scene(typesRango_parent);
        
        //  typesRango_scene.getStylesheets().add("vista.fxml");
        Stage app_stage;
        app_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(typesRango_scene);
        app_stage.setTitle("Bienvenido");
        app_stage.show();
    }

    @FXML
    private void OnActEditar(ActionEvent event) {
        try {
            TbEspeciesService service = new TbEspeciesService();
//            PaisesService service = (PaisesService) AppContext.getInstance().get("PaisesService");
            Respuesta respuesta = service.guardarEspecie(tbespeciesDto);
            if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar especie", getStage(), respuesta.getMensaje());
            } else {
                    unbindEspecie();
                    tbespeciesDto = (TbEspeciesDto) respuesta.getResultado("TbEspecies");
                    bindEspecie(false);
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar especie", getStage(), "Especie actualizado correctamente.");
            }  
        } catch (Exception ex) {
            Logger.getLogger(EspeciesController.class.getName()).log(Level.SEVERE, "Error guardando el especie.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar especie", getStage(), "Ocurrio un error guardando el especie.");
        }
    }


    @FXML
    private void OnActFiltrar(ActionEvent event) {
    }

    @FXML
    private void OnActEliminar(ActionEvent event) {
        try {
            if (tbespeciesDto.getEspId()== null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar especie", getStage(), "Debe cargar la especie que desea eliminar.");
            } else {

                TbEspeciesService service = (TbEspeciesService) AppContext.getInstance().get("TbEspeciesService");
                Respuesta respuesta = service.eliminarEspecie(tbespeciesDto.getEspId());
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar especie", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar especie", getStage(), "Especie eliminado correctamente.");
                    nuevoEspecie();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(EspeciesController.class.getName()).log(Level.SEVERE, "Error eliminando la especie.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar especie", getStage(), "Ocurrio un error eliminando la especie.");
        }
    }

    @FXML
    private void OnActCombEsp(ActionEvent event) throws IOException {
        if(ComboBEspecies.getSelectionModel().getSelectedItem() == "Parques"){
        Parent typesRango_parent = FXMLLoader.load(getClass().getResource("/view/Parques.fxml"));
        Scene typesRango_scene = new Scene(typesRango_parent);
        
        //  typesRango_scene.getStylesheets().add("vista.fxml");
        Stage app_stage;
        app_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(typesRango_scene);
        app_stage.setTitle("Parques");
        app_stage.show();
        }
        else if (ComboBEspecies.getSelectionModel().getSelectedItem() == "Refugios"){
        Parent typesRango_parent = FXMLLoader.load(getClass().getResource("/view/Refugios.fxml"));
        Scene typesRango_scene = new Scene(typesRango_parent);
        
        //  typesRango_scene.getStylesheets().add("vista.fxml");
        Stage app_stage;
        app_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(typesRango_scene);
        app_stage.setTitle("Refugios");
        app_stage.show();
        }
        else if (ComboBEspecies.getSelectionModel().getSelectedItem() == "Especies"){
        Parent typesRango_parent = FXMLLoader.load(getClass().getResource("/view/Especies.fxml"));
        Scene typesRango_scene = new Scene(typesRango_parent);
        
        //  typesRango_scene.getStylesheets().add("vista.fxml");
        Stage app_stage;
        app_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(typesRango_scene);
        app_stage.setTitle("Especies");
        app_stage.show();
        }
        else if (ComboBEspecies.getSelectionModel().getSelectedItem() == "Ubicacion"){
        Parent typesRango_parent = FXMLLoader.load(getClass().getResource("/view/Ubicacion.fxml"));
        Scene typesRango_scene = new Scene(typesRango_parent);
        
        //  typesRango_scene.getStylesheets().add("vista.fxml");
        Stage app_stage;
        app_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(typesRango_scene);
        app_stage.setTitle("Ubicacion");
        app_stage.show();
        }
        else if (ComboBEspecies.getSelectionModel().getSelectedItem() == "Precios"){
        Parent typesRango_parent = FXMLLoader.load(getClass().getResource("/view/Precios.fxml"));
        Scene typesRango_scene = new Scene(typesRango_parent);
        
        //  typesRango_scene.getStylesheets().add("vista.fxml");
        Stage app_stage;
        app_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(typesRango_scene);
        app_stage.setTitle("Precios");
        app_stage.show();
        }
        else if (ComboBEspecies.getSelectionModel().getSelectedItem() == "Tarjetas"){
        Parent typesRango_parent = FXMLLoader.load(getClass().getResource("/view/Tarjetas.fxml"));
        Scene typesRango_scene = new Scene(typesRango_parent);
        
        //  typesRango_scene.getStylesheets().add("vista.fxml");
        Stage app_stage;
        app_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(typesRango_scene);
        app_stage.setTitle("Tarjetas");
        app_stage.show();
        }
    }

    @FXML
    private void OnActGuardar(ActionEvent event) {
        try {
            TbEspeciesService service = new TbEspeciesService();
//            PaisesService service = (PaisesService) AppContext.getInstance().get("PaisesService");
            Respuesta respuesta = service.guardarEspecie(tbespeciesDto);
            
            if (!respuesta.getEstado()) {
//                System.out.println("-----------------estoy aqui-------------------");
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar especie", getStage(), respuesta.getMensaje());
            } else {
//                System.out.println("------------------estoy aqui-------------------");
                    unbindEspecie();
                    
                    tbespeciesDto = (TbEspeciesDto) respuesta.getResultado("TbEspecies");
                    System.out.println("------------------estoy aqui-------------------");
                    bindEspecie(false);
                    
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar especie", getStage(), "Especie actualizado correctamente.");
            }  
        } catch (Exception ex) {
            Logger.getLogger(EspeciesController.class.getName()).log(Level.SEVERE, "Error guardando el especie.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar especie", getStage(), "Ocurrio un error guardando el especie.");
        }
    }
    
    private void nuevoEspecie() {
        unbindEspecie();
        tbespeciesDto = new TbEspeciesDto();
        bindEspecie(true);
        txtIdEspecies.clear();
        txtIdEspecies.requestFocus();
    }
    
    private void bindEspecie(Boolean nuevo) {
        if(!nuevo){
            txtIdEspecies.textProperty().bind(tbespeciesDto.espId);
        }
        txtTipoEspecie.textProperty().bindBidirectional(tbespeciesDto.espTipo);
        txtNomCientifico.textProperty().bindBidirectional(tbespeciesDto.espNomcientifico);
        txtNomComun.textProperty().bindBidirectional(tbespeciesDto.espNomcomun);
        txtPeso.textProperty().bindBidirectional(tbespeciesDto.espPeso);
        txtTamaño.textProperty().bindBidirectional(tbespeciesDto.espTamaño);
        txtCaracteristicas.textProperty().bindBidirectional(tbespeciesDto.espCaracprinc);

    }

    private void unbindEspecie() {
        txtIdEspecies.textProperty().unbind();
        txtTipoEspecie.textProperty().unbindBidirectional(tbespeciesDto.espTipo);
        txtNomCientifico.textProperty().unbindBidirectional(tbespeciesDto.espNomcientifico);
        txtNomComun.textProperty().unbindBidirectional(tbespeciesDto.espNomcomun);
        txtPeso.textProperty().unbindBidirectional(tbespeciesDto.espPeso);
        txtTamaño.textProperty().unbindBidirectional(tbespeciesDto.espTamaño);
        txtCaracteristicas.textProperty().unbindBidirectional(tbespeciesDto.espCaracprinc);
    }

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void cargarEspecie(Long id){
        TbEspeciesService service = (TbEspeciesService) AppContext.getInstance().get("TbEspeciesService");
        Respuesta respuesta = service.getEspecie(id);

        if (respuesta.getEstado()) {
            unbindEspecie();
            tbespeciesDto = (TbEspeciesDto) respuesta.getResultado("TbEspecies");
            bindEspecie(false);
         
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar especie", getStage(), respuesta.getMensaje());
        }
    
    }

    @FXML
    private void KeyPressed_IdEsp(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && !txtIdEspecies.getText().isEmpty()) {
            cargarEspecie(Long.valueOf(txtIdEspecies.getText()));
        }
    }
    
}
