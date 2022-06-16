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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.TbUbicacionDto;
import service.TbUbicacionService;
import util.AppContext;
import util.Formato;
import util.Mensaje;
import util.Respuesta;

/**
 * FXML Controller class
 *
 * @author monge
 */
public class UbicacionController extends Controller implements Initializable {

    @FXML
    private Button btnAtras;
    @FXML
    private Button btnEditar;
    @FXML
    private ComboBox<String> ComboBUbi;
    @FXML
    private TextField txtFiltro1;
    @FXML
    private Button btnFiltrar;
    @FXML
    private Button btnEliminar;
    @FXML
    private TextField txtProvincia;
    @FXML
    private TextField txtCanton;
    @FXML
    private TextField txtDistrito;
    @FXML
    private Button btnGuardar;
    @FXML
    private TextField txtIdUbicacion;
    
    TbUbicacionDto tbubicacionDto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ComboBUbi.setItems(FXCollections.observableArrayList("Parques","Refugios","Especies","Ubicacion","Precios","Tarjetas"));
    
        txtIdUbicacion.setTextFormatter(Formato.getInstance().integerFormat());
        txtProvincia.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtCanton.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtDistrito.setTextFormatter(Formato.getInstance().letrasFormat(30));
        
        tbubicacionDto = new TbUbicacionDto();
        nuevoUbicacion();
    
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
            TbUbicacionService service = new TbUbicacionService();
//            PaisesService service = (PaisesService) AppContext.getInstance().get("PaisesService");
            Respuesta respuesta = service.guardarUbicacion(tbubicacionDto);
            if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar ubicacion", getStage(), respuesta.getMensaje());
            } else {
                    unbindUbicacion();
                    tbubicacionDto = (TbUbicacionDto) respuesta.getResultado("TbUbicacion");
                    bindUbicacion(false);
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar ubicacion", getStage(), "Ubicacion actualizado correctamente.");
            }  
        } catch (Exception ex) {
            Logger.getLogger(UbicacionController.class.getName()).log(Level.SEVERE, "Error guardando la ubicacion.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar ubicacion", getStage(), "Ocurrio un error guardando la ubicacion.");
        }
    }


    @FXML
    private void OnActFiltrar(ActionEvent event) {
    }

    @FXML
    private void OnActEliminar(ActionEvent event) {
        try {
            if (tbubicacionDto.getUbiId()== null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar ubicacion", getStage(), "Debe cargar la ubicacion que desea eliminar.");
            } else {

                TbUbicacionService service = (TbUbicacionService) AppContext.getInstance().get("TbUbicacionService");
                Respuesta respuesta = service.eliminarUbicacion(tbubicacionDto.getUbiId());
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar ubicacion", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar ubicacion", getStage(), "Ubicacion eliminado correctamente.");
                    nuevoUbicacion();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(UbicacionController.class.getName()).log(Level.SEVERE, "Error eliminando la ubicacion.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar ubicacion", getStage(), "Ocurrio un error eliminando la ubicacion.");
        }
    }

    @FXML
    private void OnActCombUbi(ActionEvent event) throws IOException {
        if(ComboBUbi.getSelectionModel().getSelectedItem() == "Parques"){
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
        else if (ComboBUbi.getSelectionModel().getSelectedItem() == "Refugios"){
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
        else if (ComboBUbi.getSelectionModel().getSelectedItem() == "Especies"){
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
        else if (ComboBUbi.getSelectionModel().getSelectedItem() == "Ubicacion"){
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
        else if (ComboBUbi.getSelectionModel().getSelectedItem() == "Precios"){
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
        else if (ComboBUbi.getSelectionModel().getSelectedItem() == "Tarjetas"){
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
            TbUbicacionService service = new TbUbicacionService();
//            PaisesService service = (PaisesService) AppContext.getInstance().get("PaisesService");
            Respuesta respuesta = service.guardarUbicacion(tbubicacionDto);
            if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar ubicacion", getStage(), respuesta.getMensaje());
            } else {
                    unbindUbicacion();
                    tbubicacionDto = (TbUbicacionDto) respuesta.getResultado("TbUbicacion");
                    bindUbicacion(false);
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar ubicacion", getStage(), "Ubicacion actualizado correctamente.");
            }  
        } catch (Exception ex) {
            Logger.getLogger(UbicacionController.class.getName()).log(Level.SEVERE, "Error guardando la ubicacion.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar ubicacion", getStage(), "Ocurrio un error guardando la ubicacion.");
        }
    }
    
    private void nuevoUbicacion() {
        unbindUbicacion();
        tbubicacionDto = new TbUbicacionDto();
        bindUbicacion(true);
        txtIdUbicacion.clear();
        txtIdUbicacion.requestFocus();
    }
    
    private void bindUbicacion(Boolean nuevo) {
        if(!nuevo){
            txtIdUbicacion.textProperty().bind(tbubicacionDto.ubiId);
        }
        txtProvincia.textProperty().bindBidirectional(tbubicacionDto.ubiProvincia);
        txtCanton.textProperty().bindBidirectional(tbubicacionDto.ubiCanton);
        txtDistrito.textProperty().bindBidirectional(tbubicacionDto.ubiDistrito);

    }

    private void unbindUbicacion() {
        txtIdUbicacion.textProperty().unbind();
        txtProvincia.textProperty().unbindBidirectional(tbubicacionDto.ubiProvincia);
        txtCanton.textProperty().unbindBidirectional(tbubicacionDto.ubiCanton);
        txtDistrito.textProperty().unbindBidirectional(tbubicacionDto.ubiDistrito);
 
    }

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void cargarUbicacion(Long id){
        TbUbicacionService service = (TbUbicacionService) AppContext.getInstance().get("TbUbicacionService");
        Respuesta respuesta = service.getUbicacion(id);

        if (respuesta.getEstado()) {
            unbindUbicacion();
            tbubicacionDto = (TbUbicacionDto) respuesta.getResultado("TbUbicacion");
            bindUbicacion(false);
         
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar ubicacion", getStage(), respuesta.getMensaje());
        }
    
    }
    
    @FXML
    private void KeyPressed_IdUbi(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && !txtIdUbicacion.getText().isEmpty()) {
            cargarUbicacion(Long.valueOf(txtIdUbicacion.getText()));
        }
        
    }
}
