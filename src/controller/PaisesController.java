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
import model.TbPaisesDto;
import service.TbPaisesService;
import util.AppContext;
import util.Formato;
import util.Mensaje;
import util.Respuesta;

/**
 * FXML Controller class
 *
 * @author monge
 */
public class PaisesController extends Controller implements Initializable {

    @FXML
    private Button btnEditar;
    @FXML
    private ComboBox<String> ComboBPai;
    @FXML
    private TextField txtFiltro1;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnFiltrar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnAtras;
    @FXML
    private TextField txtIdPaises;
    @FXML
    private TextField txtNomPais;
    @FXML
    private TextField txtContinente;
    
    TbPaisesDto tbpaisesDto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ComboBPai.setItems(FXCollections.observableArrayList("Visitantes"));
        
        txtIdPaises.setTextFormatter(Formato.getInstance().integerFormat());
        txtNomPais.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtContinente.setTextFormatter(Formato.getInstance().letrasFormat(30));
        
        tbpaisesDto = new TbPaisesDto();
        nuevoPais();

    }    

    @FXML
    private void OnActEditar(ActionEvent event) {
        try {
            TbPaisesService service = new TbPaisesService();
//            PaisesService service = (PaisesService) AppContext.getInstance().get("PaisesService");
            Respuesta respuesta = service.guardarPais(tbpaisesDto);
            if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar pais", getStage(), respuesta.getMensaje());
            } else {
                    unbindPais();
                    tbpaisesDto = (TbPaisesDto) respuesta.getResultado("TbPaises");
                    bindPais(false);
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar pais", getStage(), "Pais actualizado correctamente.");
            }  
        } catch (Exception ex) {
            Logger.getLogger(PaisesController.class.getName()).log(Level.SEVERE, "Error guardando el pais.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar pais", getStage(), "Ocurrio un error guardando el pais.");
        }
    }

    @FXML
    private void OnActCombPai(ActionEvent event) throws IOException {
        if(ComboBPai.getSelectionModel().getSelectedItem() == "Visitantes"){
        Parent typesRango_parent = FXMLLoader.load(getClass().getResource("/view/Visitantes.fxml"));
        Scene typesRango_scene = new Scene(typesRango_parent);
        
        //  typesRango_scene.getStylesheets().add("vista.fxml");
        Stage app_stage;
        app_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(typesRango_scene);
        app_stage.setTitle("Visitantes");
        app_stage.show();
        }
    }

    @FXML
    private void OnActGuardar(ActionEvent event) {
        try {
            TbPaisesService service = new TbPaisesService();
//            PaisesService service = (PaisesService) AppContext.getInstance().get("PaisesService");
            Respuesta respuesta = service.guardarPais(tbpaisesDto);
            if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar pais", getStage(), respuesta.getMensaje());
            } else {
                    unbindPais();
                    tbpaisesDto = (TbPaisesDto) respuesta.getResultado("TbPaises");
                    bindPais(false);
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar pais", getStage(), "Pais actualizado correctamente.");
            }  
        } catch (Exception ex) {
            Logger.getLogger(PaisesController.class.getName()).log(Level.SEVERE, "Error guardando el pais.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar pais", getStage(), "Ocurrio un error guardando el pais.");
        }
    }

    @FXML
    private void OnActFiltrar(ActionEvent event) {
    }

    @FXML
    private void OnActEliminar(ActionEvent event) {
        try {
            if (tbpaisesDto.getPaiId()== null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar pais", getStage(), "Debe cargar el pais que desea eliminar.");
            } else {

                TbPaisesService service = (TbPaisesService) AppContext.getInstance().get("TbPaisesService");
                Respuesta respuesta = service.eliminarPais(tbpaisesDto.getPaiId());
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar pais", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar pais", getStage(), "Pais eliminado correctamente.");
                    nuevoPais();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(PaisesController.class.getName()).log(Level.SEVERE, "Error eliminando el pais.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar pais", getStage(), "Ocurrio un error eliminando el pais.");
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
    
    private void cargarPais(Long id){
        TbPaisesService service = (TbPaisesService) AppContext.getInstance().get("TbPaisesService");
        Respuesta respuesta = service.getPais(id);

        if (respuesta.getEstado()) {
            unbindPais();
            tbpaisesDto = (TbPaisesDto) respuesta.getResultado("TbPaises");
            bindPais(false);
         
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar pais", getStage(), respuesta.getMensaje());
        }
    
    }
    
    @FXML
    private void KeyPressed_IdPai(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && !txtIdPaises.getText().isEmpty()) {
            cargarPais(Long.valueOf(txtIdPaises.getText()));
        }
    }
    
    private void nuevoPais() {
        unbindPais();
        tbpaisesDto = new TbPaisesDto();
        bindPais(true);
        txtIdPaises.clear();
        txtIdPaises.requestFocus();
    }
    
    private void bindPais(Boolean nuevo) {
        if(!nuevo){
            txtIdPaises.textProperty().bind(tbpaisesDto.paiId);
        }
        txtNomPais.textProperty().bindBidirectional(tbpaisesDto.paiNombre);
        txtContinente.textProperty().bindBidirectional(tbpaisesDto.paiContinente);

    }

    private void unbindPais() {
        txtIdPaises.textProperty().unbind();
        txtNomPais.textProperty().unbindBidirectional(tbpaisesDto.paiNombre);
        txtContinente.textProperty().unbindBidirectional(tbpaisesDto.paiContinente);
        
    }

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
