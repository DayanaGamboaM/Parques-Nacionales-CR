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
import model.TbPreciosDto;
import service.TbPreciosService;
import util.AppContext;
import util.Formato;
import util.Mensaje;
import util.Respuesta;

/**
 * FXML Controller class
 *
 * @author monge
 */
public class PreciosController extends Controller implements Initializable {

    @FXML
    private Button btnAtras;
    @FXML
    private Button btnEditar;
    @FXML
    private ComboBox<String> ComboBPre;
    @FXML
    private TextField txtFiltro1;
    @FXML
    private Button btnFiltrar;
    @FXML
    private Button btnEliminar;
    @FXML
    private TextField txtIdPrecio;
    
    TbPreciosDto tbpreciosDto;
    @FXML
    private Button btnGuardar;
    @FXML
    private TextField txtModalidad;
    @FXML
    private TextField txtPrecio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ComboBPre.setItems(FXCollections.observableArrayList("Parques","Refugios","Especies","Ubicacion","Precios","Tarjetas"));
    
        txtIdPrecio.setTextFormatter(Formato.getInstance().integerFormat());
        txtModalidad.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtPrecio.setTextFormatter(Formato.getInstance().integerFormat());
        
        tbpreciosDto = new TbPreciosDto();
        nuevoPrecio();
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
            TbPreciosService service = new TbPreciosService();
//            PaisesService service = (PaisesService) AppContext.getInstance().get("PaisesService");
            Respuesta respuesta = service.guardarPrecio(tbpreciosDto);
            if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar precio", getStage(), respuesta.getMensaje());
            } else {
                    unbindPrecio();
                    tbpreciosDto = (TbPreciosDto) respuesta.getResultado("TbPrecios");
                    bindPrecio(false);
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar precio", getStage(), "Precio actualizado correctamente.");
            }  
        } catch (Exception ex) {
            Logger.getLogger(PreciosController.class.getName()).log(Level.SEVERE, "Error guardando el precio.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar precio", getStage(), "Ocurrio un error guardando el precio.");
        }
    }


    @FXML
    private void OnActFiltrar(ActionEvent event) {
    }

    @FXML
    private void OnActEliminar(ActionEvent event) {
        try {
            if (tbpreciosDto.getPreId()== null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar precio", getStage(), "Debe cargar el precio que desea eliminar.");
            } else {

                TbPreciosService service = (TbPreciosService) AppContext.getInstance().get("TbPreciosService");
                Respuesta respuesta = service.eliminarPrecio(tbpreciosDto.getPreId());
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar precio", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar precio", getStage(), "Precio eliminado correctamente.");
                    nuevoPrecio();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(PreciosController.class.getName()).log(Level.SEVERE, "Error eliminando el precio.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar precio", getStage(), "Ocurrio un error eliminando el precio.");
        }
    }

    @FXML
    private void OnActCombPre(ActionEvent event) throws IOException {
        if(ComboBPre.getSelectionModel().getSelectedItem() == "Parques"){
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
        else if (ComboBPre.getSelectionModel().getSelectedItem() == "Refugios"){
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
        else if (ComboBPre.getSelectionModel().getSelectedItem() == "Especies"){
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
        else if (ComboBPre.getSelectionModel().getSelectedItem() == "Ubicacion"){
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
        else if (ComboBPre.getSelectionModel().getSelectedItem() == "Precios"){
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
        else if (ComboBPre.getSelectionModel().getSelectedItem() == "Tarjetas"){
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
            TbPreciosService service = new TbPreciosService();
//            PaisesService service = (PaisesService) AppContext.getInstance().get("PaisesService");
            Respuesta respuesta = service.guardarPrecio(tbpreciosDto);
            if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar precio", getStage(), respuesta.getMensaje());
            } else {
                    unbindPrecio();
                    tbpreciosDto = (TbPreciosDto) respuesta.getResultado("TbPrecios");
                    bindPrecio(false);
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar precio", getStage(), "Precio actualizado correctamente.");
            }  
        } catch (Exception ex) {
            Logger.getLogger(PreciosController.class.getName()).log(Level.SEVERE, "Error guardando el precio.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar precio", getStage(), "Ocurrio un error guardando el precio.");
        }
    }
    
    private void nuevoPrecio() {
        unbindPrecio();
        tbpreciosDto = new TbPreciosDto();
        bindPrecio(true);
        txtIdPrecio.clear();
        txtIdPrecio.requestFocus();
    }
    
    private void bindPrecio(Boolean nuevo) {
        if(!nuevo){
            txtIdPrecio.textProperty().bind(tbpreciosDto.preId);
        }
        txtModalidad.textProperty().bindBidirectional(tbpreciosDto.preModalidad);
        txtPrecio.textProperty().bindBidirectional(tbpreciosDto.prePrecio);

    }

    private void unbindPrecio() {
        txtIdPrecio.textProperty().unbind();
        txtModalidad.textProperty().unbindBidirectional(tbpreciosDto.preModalidad);
        txtPrecio.textProperty().unbindBidirectional(tbpreciosDto.prePrecio);
        
    }

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void cargarPrecios(Long id){
        TbPreciosService service = (TbPreciosService) AppContext.getInstance().get("TbPreciosService");
        Respuesta respuesta = service.getPrecio(id);

        if (respuesta.getEstado()) {
            unbindPrecio();
            tbpreciosDto = (TbPreciosDto) respuesta.getResultado("TbPrecios");
            bindPrecio(false);
         
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar ubicacion", getStage(), respuesta.getMensaje());
        }
    
    }
    
    @FXML
    private void KeyPressed_IdPre(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && !txtIdPrecio.getText().isEmpty()) {
            cargarPrecios(Long.valueOf(txtIdPrecio.getText()));
        }
    }
    
    
}
