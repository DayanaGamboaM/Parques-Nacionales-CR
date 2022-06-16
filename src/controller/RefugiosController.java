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
import model.TbRefugiosDto;
import service.TbRefugiosService;
import util.AppContext;
import util.Formato;
import util.Mensaje;
import util.Respuesta;

/**
 * FXML Controller class
 *
 * @author monge
 */
public class RefugiosController extends Controller implements Initializable {

    @FXML
    private Button btnAtras;
    @FXML
    private Button btnEditar;
    @FXML
    private ComboBox<String> ComboBRef;
    @FXML
    private TextField txtFiltro1;
    @FXML
    private Button btnFiltrar;
    @FXML
    private Button btnEliminar;
    @FXML
    private TextField txtCoordenadas;
    @FXML
    private TextField txtIdRefugios;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtUbicacion;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private TextField txtReconocimientos;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtAreaReservada;
    @FXML
    private Button btnGuardar;
    
    TbRefugiosDto tbrefugiosDto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ComboBRef.setItems(FXCollections.observableArrayList("Parques","Refugios","Especies","Ubicacion","Precios","Tarjetas"));
    
        txtIdRefugios.setTextFormatter(Formato.getInstance().integerFormat());
        txtNombre.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtDescripcion.setTextFormatter(Formato.getInstance().letrasFormat(100));
        txtReconocimientos.setTextFormatter(Formato.getInstance().letrasFormat(100));
        txtDireccion.setTextFormatter(Formato.getInstance().letrasFormat(100));
        txtAreaReservada.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtCoordenadas.setTextFormatter(Formato.getInstance().letrasFormat(100));
        
        tbrefugiosDto = new TbRefugiosDto();
        nuevoRefugio();
    
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
    private void OnActFiltrar(ActionEvent event) {
    }

    @FXML
    private void OnActCombRef(ActionEvent event) throws IOException {
        if(ComboBRef.getSelectionModel().getSelectedItem() == "Parques"){
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
        else if (ComboBRef.getSelectionModel().getSelectedItem() == "Refugios"){
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
        else if (ComboBRef.getSelectionModel().getSelectedItem() == "Especies"){
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
        else if (ComboBRef.getSelectionModel().getSelectedItem() == "Ubicacion"){
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
        else if (ComboBRef.getSelectionModel().getSelectedItem() == "Precios"){
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
        else if (ComboBRef.getSelectionModel().getSelectedItem() == "Tarjetas"){
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
    private void OnActEditar(ActionEvent event) {
        try {
            TbRefugiosService service = new TbRefugiosService();
//            PaisesService service = (PaisesService) AppContext.getInstance().get("PaisesService");
            Respuesta respuesta = service.guardarRefugio(tbrefugiosDto);
            if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar refugio", getStage(), respuesta.getMensaje());
            } else {
                    unbindRefugio();
                    tbrefugiosDto = (TbRefugiosDto) respuesta.getResultado("TbRefugios");
                    bindRefugio(false);
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar refugio", getStage(), "Refugio actualizado correctamente.");
            }  
        } catch (Exception ex) {
            Logger.getLogger(RefugiosController.class.getName()).log(Level.SEVERE, "Error guardando el refugio.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar refugio", getStage(), "Ocurrio un error guardando el refugio.");
        }
    }
    
    @FXML
    private void OnActEliminar(ActionEvent event) {
        try {
            if (tbrefugiosDto.getRefId()== null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar refugio", getStage(), "Debe cargar el refugio que desea eliminar.");
            } else {

                TbRefugiosService service = (TbRefugiosService) AppContext.getInstance().get("TbRefugiosService");
                Respuesta respuesta = service.eliminarRefugio(tbrefugiosDto.getRefId());
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar refugio", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar refugio", getStage(), "Refugio eliminado correctamente.");
                    nuevoRefugio();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(RefugiosController.class.getName()).log(Level.SEVERE, "Error eliminando el refugio.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar refugio", getStage(), "Ocurrio un error eliminando el refugio.");
        }
    }

    @FXML
    private void OnActGuardar(ActionEvent event) {
        try {
            TbRefugiosService service = new TbRefugiosService();
//            PaisesService service = (PaisesService) AppContext.getInstance().get("PaisesService");
            Respuesta respuesta = service.guardarRefugio(tbrefugiosDto);
            if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar refugio", getStage(), respuesta.getMensaje());
            } else {
                    unbindRefugio();
                    tbrefugiosDto = (TbRefugiosDto) respuesta.getResultado("TbRefugios");
                    bindRefugio(false);
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar refugio", getStage(), "Refugio actualizado correctamente.");
            }  
        } catch (Exception ex) {
            Logger.getLogger(RefugiosController.class.getName()).log(Level.SEVERE, "Error guardando el refugio.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar refugio", getStage(), "Ocurrio un error guardando el refugio.");
        }
    }
    
    private void nuevoRefugio() {
        unbindRefugio();
        tbrefugiosDto = new TbRefugiosDto();
        bindRefugio(true);
        txtIdRefugios.clear();
        txtIdRefugios.requestFocus();
    }
    
    private void bindRefugio(Boolean nuevo) {
        if(!nuevo){
            txtIdRefugios.textProperty().bind(tbrefugiosDto.refId);
        }
        txtNombre.textProperty().bindBidirectional(tbrefugiosDto.refNombre);
        txtDescripcion.textProperty().bindBidirectional(tbrefugiosDto.refDescripcion);
        txtReconocimientos.textProperty().bindBidirectional(tbrefugiosDto.refReconocimientos);
        txtDireccion.textProperty().bindBidirectional(tbrefugiosDto.refDireccionexac);
        txtAreaReservada.textProperty().bindBidirectional(tbrefugiosDto.refAreareservada);
        txtCoordenadas.textProperty().bindBidirectional(tbrefugiosDto.refCoordgeo);

    }

    private void unbindRefugio() {
        txtIdRefugios.textProperty().unbind();
        txtNombre.textProperty().unbindBidirectional(tbrefugiosDto.refNombre);
        txtDescripcion.textProperty().unbindBidirectional(tbrefugiosDto.refDescripcion);
        txtReconocimientos.textProperty().unbindBidirectional(tbrefugiosDto.refReconocimientos);
        txtDireccion.textProperty().unbindBidirectional(tbrefugiosDto.refDireccionexac);
        txtAreaReservada.textProperty().unbindBidirectional(tbrefugiosDto.refAreareservada);
        txtCoordenadas.textProperty().unbindBidirectional(tbrefugiosDto.refCoordgeo);
    }

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void cargarRefugio(Long id){
        TbRefugiosService service = (TbRefugiosService) AppContext.getInstance().get("TbRefugiosService");
        Respuesta respuesta = service.getRefugio(id);

        if (respuesta.getEstado()) {
            unbindRefugio();
            tbrefugiosDto = (TbRefugiosDto) respuesta.getResultado("TbRefugios");
            bindRefugio(false);
         
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar refugio", getStage(), respuesta.getMensaje());
        }
    
    }
    
    @FXML
    private void KeyPressed_IdRef(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && !txtIdRefugios.getText().isEmpty()) {
            cargarRefugio(Long.valueOf(txtIdRefugios.getText()));
        }
    }

    
    
}
