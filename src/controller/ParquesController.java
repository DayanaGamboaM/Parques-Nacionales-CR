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
import model.TbParquesDto;
import service.TbParquesService;
import util.AppContext;
import util.Formato;
import util.Mensaje;
import util.Respuesta;

/**
 * FXML Controller class
 *
 * @author monge
 */
public class ParquesController extends Controller implements Initializable {

    @FXML
    private Button btnAtras;
    @FXML
    private Button btnEditar;
    @FXML
    private ComboBox<String> ComboBParques;
    @FXML
    private TextField txtFiltro1;
    @FXML
    private Button btnFiltrar;
    @FXML
    private Button btnEliminar;
    @FXML
    private TextField txtAreaReservada;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtReconocimientos;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private TextField txtUbicacion;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtIdParques;
    @FXML
    private TextField txtCoordenadas;
    
    TbParquesDto tbparquesDto;
    @FXML
    private Button btnGuardar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ComboBParques.setItems(FXCollections.observableArrayList("Parques","Refugios","Especies","Ubicacion","Precios","Tarjetas"));
    
        txtIdParques.setTextFormatter(Formato.getInstance().integerFormat());
        txtNombre.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtDescripcion.setTextFormatter(Formato.getInstance().letrasFormat(100));
        txtReconocimientos.setTextFormatter(Formato.getInstance().letrasFormat(100));
        txtDireccion.setTextFormatter(Formato.getInstance().letrasFormat(100));
        txtAreaReservada.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtCoordenadas.setTextFormatter(Formato.getInstance().letrasFormat(100));
        
        tbparquesDto = new TbParquesDto();
        nuevoParque();
    
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
            TbParquesService service = new TbParquesService();
//            PaisesService service = (PaisesService) AppContext.getInstance().get("PaisesService");
            Respuesta respuesta = service.guardarParque(tbparquesDto);
            if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar parque", getStage(), respuesta.getMensaje());
            } else {
                    unbindParque();
                    tbparquesDto = (TbParquesDto) respuesta.getResultado("TbParques");
                    bindParque(false);
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar parque", getStage(), "Parque actualizado correctamente.");
            }  
        } catch (Exception ex) {
            Logger.getLogger(ParquesController.class.getName()).log(Level.SEVERE, "Error guardando el parque.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar parque", getStage(), "Ocurrio un error guardando el parque.");
        }
    }


    @FXML
    private void OnActFiltrar(ActionEvent event) {
    }

    @FXML
    private void OnActEliminar(ActionEvent event) {
        try {
            if (tbparquesDto.getParId()== null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar parque", getStage(), "Debe cargar el parque que desea eliminar.");
            } else {

                TbParquesService service = (TbParquesService) AppContext.getInstance().get("TbParquesService");
                Respuesta respuesta = service.eliminarParque(tbparquesDto.getParId());
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar parque", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar parque", getStage(), "Parque eliminado correctamente.");
                    nuevoParque();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ParquesController.class.getName()).log(Level.SEVERE, "Error eliminando la parque.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar parque", getStage(), "Ocurrio un error eliminando el parque.");
        }
    }

    @FXML
    private void OnActCombParq(ActionEvent event) throws IOException {
        if(ComboBParques.getSelectionModel().getSelectedItem() == "Parques"){
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
        else if (ComboBParques.getSelectionModel().getSelectedItem() == "Refugios"){
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
        else if (ComboBParques.getSelectionModel().getSelectedItem() == "Especies"){
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
        else if (ComboBParques.getSelectionModel().getSelectedItem() == "Ubicacion"){
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
        else if (ComboBParques.getSelectionModel().getSelectedItem() == "Precios"){
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
        else if (ComboBParques.getSelectionModel().getSelectedItem() == "Tarjetas"){
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
            TbParquesService service = new TbParquesService();
//            PaisesService service = (PaisesService) AppContext.getInstance().get("PaisesService");
            Respuesta respuesta = service.guardarParque(tbparquesDto);
            if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar parque", getStage(), respuesta.getMensaje());
            } else {
                    unbindParque();
                    tbparquesDto = (TbParquesDto) respuesta.getResultado("TbParques");
                    bindParque(false);
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar parque", getStage(), "Parque actualizado correctamente.");
            }  
        } catch (Exception ex) {
            Logger.getLogger(ParquesController.class.getName()).log(Level.SEVERE, "Error guardando el parque.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar parque", getStage(), "Ocurrio un error guardando el parque.");
        }
    }
    private void nuevoParque() {
        unbindParque();
        tbparquesDto = new TbParquesDto();
        bindParque(true);
        txtIdParques.clear();
        txtIdParques.requestFocus();
    }
    
    private void bindParque(Boolean nuevo) {
        if(!nuevo){
            txtIdParques.textProperty().bind(tbparquesDto.parId);
        }
        txtNombre.textProperty().bindBidirectional(tbparquesDto.parNombre);
        txtDescripcion.textProperty().bindBidirectional(tbparquesDto.parDescripcion);
        txtReconocimientos.textProperty().bindBidirectional(tbparquesDto.parReconocimientos);
        txtDireccion.textProperty().bindBidirectional(tbparquesDto.parDireccionexacta);
        txtAreaReservada.textProperty().bindBidirectional(tbparquesDto.parAreareservada);
        txtCoordenadas.textProperty().bindBidirectional(tbparquesDto.parCoordgeo);

    }

    private void unbindParque() {
        txtIdParques.textProperty().unbind();
        txtNombre.textProperty().unbindBidirectional(tbparquesDto.parNombre);
        txtDescripcion.textProperty().unbindBidirectional(tbparquesDto.parDescripcion);
        txtReconocimientos.textProperty().unbindBidirectional(tbparquesDto.parReconocimientos);
        txtDireccion.textProperty().unbindBidirectional(tbparquesDto.parDireccionexacta);
        txtAreaReservada.textProperty().unbindBidirectional(tbparquesDto.parAreareservada);
        txtCoordenadas.textProperty().unbindBidirectional(tbparquesDto.parCoordgeo);
    }

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void cargarParque(Long id){
        TbParquesService service = (TbParquesService) AppContext.getInstance().get("TbParquesService");
        Respuesta respuesta = service.getParque(id);

        if (respuesta.getEstado()) {
            unbindParque();
            tbparquesDto = (TbParquesDto) respuesta.getResultado("TbParques");
            bindParque(false);
         
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar parque", getStage(), respuesta.getMensaje());
        }
    
    }
    
    @FXML
    private void KeyPressed_IdPar(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && !txtIdParques.getText().isEmpty()) {
            cargarParque(Long.valueOf(txtIdParques.getText()));
        }
    }
    
}
