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
import model.TbTarjetasDto;
import service.TbTarjetasService;
import util.AppContext;
import util.Formato;
import util.Mensaje;
import util.Respuesta;

/**
 * FXML Controller class
 *
 * @author monge
 */
public class TarjetasController extends Controller implements Initializable {

    @FXML
    private Button btnAtras;
    @FXML
    private Button btnEditar;
    @FXML
    private ComboBox<String> ComboBTar;
    @FXML
    private TextField txtFiltro1;
    @FXML
    private Button btnFiltrar;
    @FXML
    private Button btnEliminar;
    @FXML
    private TextField txtIdTarjetas;
    @FXML
    private TextField txtTipoTarjeta;
    @FXML
    private TextField txtComision;
    @FXML
    private Button btnGuardar;
    
    TbTarjetasDto tbtarjetasDto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ComboBTar.setItems(FXCollections.observableArrayList("Parques","Refugios","Especies","Ubicacion","Precios","Tarjetas"));
    
        txtIdTarjetas.setTextFormatter(Formato.getInstance().integerFormat());
        txtTipoTarjeta.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtComision.setTextFormatter(Formato.getInstance().integerFormat());
        
        tbtarjetasDto = new TbTarjetasDto();
        nuevoTarjeta();
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
            TbTarjetasService service = new TbTarjetasService();
//            PaisesService service = (PaisesService) AppContext.getInstance().get("PaisesService");
            Respuesta respuesta = service.guardarTarjeta(tbtarjetasDto);
            if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar tarjeta", getStage(), respuesta.getMensaje());
            } else {
                    unbindTarjeta();
                    tbtarjetasDto = (TbTarjetasDto) respuesta.getResultado("TbTarjetas");
                    bindTarjeta(false);
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar tarjeta", getStage(), "Tarjeta actualizada correctamente.");
            }  
        } catch (Exception ex) {
            Logger.getLogger(TarjetasController.class.getName()).log(Level.SEVERE, "Error guardando la tarjeta.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar tarjeta", getStage(), "Ocurrio un error guardando la tarjeta.");
        }
    }


    @FXML
    private void OnActFiltrar(ActionEvent event) {
    }

    @FXML
    private void OnActEliminar(ActionEvent event) {
        try {
            if (tbtarjetasDto.getTarId()== null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar tarjeta", getStage(), "Debe cargar la tarjeta que desea eliminar.");
            } else {

                TbTarjetasService service = (TbTarjetasService) AppContext.getInstance().get("TbTarjetasService");
                Respuesta respuesta = service.eliminarTarjeta(tbtarjetasDto.getTarId());
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar tarjeta", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar tarjeta", getStage(), "Tarjeta eliminado correctamente.");
                    nuevoTarjeta();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(TarjetasController.class.getName()).log(Level.SEVERE, "Error eliminando la tarjeta.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar tarjeta", getStage(), "Ocurrio un error eliminando la tarjeta.");
        }
    }

    @FXML
    private void OnActCombTar(ActionEvent event) throws IOException {
        if(ComboBTar.getSelectionModel().getSelectedItem() == "Parques"){
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
        else if (ComboBTar.getSelectionModel().getSelectedItem() == "Refugios"){
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
        else if (ComboBTar.getSelectionModel().getSelectedItem() == "Especies"){
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
        else if (ComboBTar.getSelectionModel().getSelectedItem() == "Ubicacion"){
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
        else if (ComboBTar.getSelectionModel().getSelectedItem() == "Precios"){
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
        else if (ComboBTar.getSelectionModel().getSelectedItem() == "Tarjetas"){
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
            TbTarjetasService service = new TbTarjetasService();
//            PaisesService service = (PaisesService) AppContext.getInstance().get("PaisesService");
            Respuesta respuesta = service.guardarTarjeta(tbtarjetasDto);
            if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar tarjeta", getStage(), respuesta.getMensaje());
            } else {
                    unbindTarjeta();
                    tbtarjetasDto = (TbTarjetasDto) respuesta.getResultado("TbTarjetas");
                    bindTarjeta(false);
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar tarjeta", getStage(), "Tarjeta actualizada correctamente.");
            }  
        } catch (Exception ex) {
            Logger.getLogger(TarjetasController.class.getName()).log(Level.SEVERE, "Error guardando la tarjeta.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar tarjeta", getStage(), "Ocurrio un error guardando la tarjeta.");
        }
    }
    private void nuevoTarjeta() {
        unbindTarjeta();
        tbtarjetasDto = new TbTarjetasDto();
        bindTarjeta(true);
        txtIdTarjetas.clear();
        txtIdTarjetas.requestFocus();
    }
    
    private void bindTarjeta(Boolean nuevo) {
        if(!nuevo){
            txtIdTarjetas.textProperty().bind(tbtarjetasDto.tarId);
        }
        txtTipoTarjeta.textProperty().bindBidirectional(tbtarjetasDto.tarNombre);
        txtComision.textProperty().bindBidirectional(tbtarjetasDto.tarComision);

    }

    private void unbindTarjeta() {
        txtIdTarjetas.textProperty().unbind();
        txtTipoTarjeta.textProperty().unbindBidirectional(tbtarjetasDto.tarNombre);
        txtComision.textProperty().unbindBidirectional(tbtarjetasDto.tarComision);
        
    }

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void cargarTarjeta(Long id){
        TbTarjetasService service = (TbTarjetasService) AppContext.getInstance().get("TbTarjetasService");
        Respuesta respuesta = service.getTarjeta(id);

        if (respuesta.getEstado()) {
            unbindTarjeta();
            tbtarjetasDto = (TbTarjetasDto) respuesta.getResultado("TbTarjetas");
            bindTarjeta(false);
         
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar tarjeta", getStage(), respuesta.getMensaje());
        }
    
    }

    @FXML
    private void KeyPressed_IdTar(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && !txtIdTarjetas.getText().isEmpty()) {
            cargarTarjeta(Long.valueOf(txtIdTarjetas.getText()));
        }
    }
    
    
    
}
