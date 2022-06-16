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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.TbVisitantesDto;
import service.TbVisitantesService;
import util.AppContext;
import util.Formato;
import util.Mensaje;
import util.Respuesta;

/**
 * FXML Controller class
 *
 * @author monge
 */
public class VisitantesController extends Controller implements Initializable {

    @FXML
    private Button btnEditar;
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
    private TextField txtRazonVisita;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCed_Pas;
    @FXML
    private TextField txtIdVisitantes;
    @FXML
    private ComboBox<String> ComboBVisit;
    
    TbVisitantesDto tbvisitantesDto;
    @FXML
    private DatePicker dateNacimiento;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ComboBVisit.setItems(FXCollections.observableArrayList("Paises"));
        
        txtIdVisitantes.setTextFormatter(Formato.getInstance().integerFormat());
        txtCed_Pas.setTextFormatter(Formato.getInstance().integerFormat());
        txtNombre.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtApellidos.setTextFormatter(Formato.getInstance().letrasFormat(30));
//        dateNacimiento.setTextFormatter(Formato.getInstance().letrasFormat(100));
        txtCorreo.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtRazonVisita.setTextFormatter(Formato.getInstance().letrasFormat(50));
        
        tbvisitantesDto = new TbVisitantesDto();
        nuevoVisitante();

    }    

    @FXML
    private void OnActEditar(ActionEvent event) {
        try {
            TbVisitantesService service = new TbVisitantesService();
//            PaisesService service = (PaisesService) AppContext.getInstance().get("PaisesService");
            Respuesta respuesta = service.guardarVisitante(tbvisitantesDto);
            if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar visitante", getStage(), respuesta.getMensaje());
            } else {
                    unbindVisitante();
                    tbvisitantesDto = (TbVisitantesDto) respuesta.getResultado("TbParques");
                    bindVisitante(false);
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar visitante", getStage(), "Visitante actualizado correctamente.");
            }  
        } catch (Exception ex) {
            Logger.getLogger(VisitantesController.class.getName()).log(Level.SEVERE, "Error guardando el visitante.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar visitante", getStage(), "Ocurrio un error guardando el visitante.");
        }
    }


    @FXML
    private void OnActGuardar(ActionEvent event) {
        try {
            TbVisitantesService service = new TbVisitantesService();
//            PaisesService service = (PaisesService) AppContext.getInstance().get("PaisesService");
            Respuesta respuesta = service.guardarVisitante(tbvisitantesDto);
            if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar visitante", getStage(), respuesta.getMensaje());
            } else {
                    unbindVisitante();
                    tbvisitantesDto = (TbVisitantesDto) respuesta.getResultado("TbVisitantes");
                    bindVisitante(false);
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar visitante", getStage(), "Visitante actualizado correctamente.");
            }  
        } catch (Exception ex) {
            Logger.getLogger(VisitantesController.class.getName()).log(Level.SEVERE, "Error guardando el visitante.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar visitante", getStage(), "Ocurrio un error guardando el visitante.");
        }
    }

    @FXML
    private void OnActFiltrar(ActionEvent event) {
    }

    @FXML
    private void OnActEliminar(ActionEvent event) {
        try {
            if (tbvisitantesDto.getVisId()== null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar visitante", getStage(), "Debe cargar el visitante que desea eliminar.");
            } else {

                TbVisitantesService service = (TbVisitantesService) AppContext.getInstance().get("TbVisitantesService");
                Respuesta respuesta = service.eliminarVisitante(tbvisitantesDto.getVisId());
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar visitante", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar visitante", getStage(), "Visitante eliminado correctamente.");
                    nuevoVisitante();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(VisitantesController.class.getName()).log(Level.SEVERE, "Error eliminando la visitante.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar visitante", getStage(), "Ocurrio un error eliminando el visitante.");
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


    @FXML
    private void OnActCombVisit(ActionEvent event) throws IOException {
        if(ComboBVisit.getSelectionModel().getSelectedItem() == "Paises"){
        Parent typesRango_parent = FXMLLoader.load(getClass().getResource("/view/Paises.fxml"));
        Scene typesRango_scene = new Scene(typesRango_parent);
        
        //  typesRango_scene.getStylesheets().add("vista.fxml");
        Stage app_stage;
        app_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(typesRango_scene);
        app_stage.setTitle("Paises");
        app_stage.show();
        }
    }
    
    private void nuevoVisitante() {
        unbindVisitante();
        tbvisitantesDto = new TbVisitantesDto();
        bindVisitante(true);
        txtIdVisitantes.clear();
        txtIdVisitantes.requestFocus();
    }
    
    private void bindVisitante(Boolean nuevo) {
        if(!nuevo){
            txtIdVisitantes.textProperty().bind(tbvisitantesDto.visId);
        }
        txtCed_Pas.textProperty().bindBidirectional(tbvisitantesDto.visCedPasap);
        txtNombre.textProperty().bindBidirectional(tbvisitantesDto.visNombre);
        txtApellidos.textProperty().bindBidirectional(tbvisitantesDto.visApellidos);
        dateNacimiento.valueProperty().bindBidirectional(tbvisitantesDto.visFechanacimiento);
        txtCorreo.textProperty().bindBidirectional(tbvisitantesDto.visCorreoelectronico);
        txtRazonVisita.textProperty().bindBidirectional(tbvisitantesDto.visRazonvisita);

    }

    private void unbindVisitante() {
        txtIdVisitantes.textProperty().unbind();
        txtCed_Pas.textProperty().unbindBidirectional(tbvisitantesDto.visCedPasap);
        txtNombre.textProperty().unbindBidirectional(tbvisitantesDto.visNombre);
        txtApellidos.textProperty().unbindBidirectional(tbvisitantesDto.visApellidos);
        dateNacimiento.valueProperty().unbindBidirectional(tbvisitantesDto.visFechanacimiento);
        txtCorreo.textProperty().unbindBidirectional(tbvisitantesDto.visCorreoelectronico);
        txtRazonVisita.textProperty().unbindBidirectional(tbvisitantesDto.visRazonvisita);
    }

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void cargarVisitante(Long id){
        TbVisitantesService service = (TbVisitantesService) AppContext.getInstance().get("TbVisitantesService");
        Respuesta respuesta = service.getVisitante(id);

        if (respuesta.getEstado()) {
            unbindVisitante();
            tbvisitantesDto = (TbVisitantesDto) respuesta.getResultado("TbVisitantes");
            bindVisitante(false);
         
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar visitante", getStage(), respuesta.getMensaje());
        }
    
    }
    
    @FXML
    private void KeyPressed_IdVis(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && !txtIdVisitantes.getText().isEmpty()) {
            cargarVisitante(Long.valueOf(txtIdVisitantes.getText()));
        }
    }
    
}
