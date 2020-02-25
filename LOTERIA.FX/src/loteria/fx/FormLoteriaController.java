/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteria.fx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import loteria.bl.LotoServices;
import loteria.bl.Prueba;
import loteria.bl.Sucursales;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FormLoteriaController implements Initializable {

//    @FXML
//    private TextField txtCodigo;
//
//    @FXML
//    private TextField txtRTN;
//
//    @FXML
//    private TextField txtNombreSuc;
//
//    @FXML
//    private TextField txtNombreRepe;
//
//    @FXML
//    private TextField txtDireccion;
//    
    @FXML
    private TextField txtBuscar;
    
    @FXML
    private TableView tbSucursales; 
            
    @FXML
    private TableColumn colResponsable;
    
    @FXML
    private TableColumn colCodigo;
    
    @FXML
    private TableColumn colRTN;
    
    @FXML
    private TableColumn colNombreSuc;
    
    @FXML
    private TableColumn colDireccion;
    
    @FXML
    private TableColumn colEditar;
    
    @FXML
    private TableColumn colEliminar;
    
    Sucursales Suc;
    
    LotoServices servicio;
    
    ObservableList<Sucursales> dataSucursales;
            
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        servicio = new LotoServices();
        
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("Codigo"));
        colRTN.setCellValueFactory(new PropertyValueFactory<>("RTN"));
        colNombreSuc.setCellValueFactory(new PropertyValueFactory("Nombre"));
        //colEncargado.setCellValueFactory(new PropertyValueFactory("Encargado"));
        colDireccion.setCellValueFactory(new PropertyValueFactory("Direccion"));
        colResponsable.setCellValueFactory(new PropertyValueFactory("Responsable"));
        dataSucursales = FXCollections.observableArrayList();
        
        definirColumnaEditar();
        definirColumnaEliminar();
       
       //cargarDatos();
    }    
    
//    
    public void nuevoSucursales() throws IOException {
        Sucursales nuevoSucursales = new Sucursales();
        abrirVentanaModal(nuevoSucursales, "Nuevo Sucursales");
    }
//    
    public void buscar() {
        cargarDatos();
    }
//    
    public String guardar(Sucursales Sucursal) {
        String resultado = servicio.guardar(Sucursal);
        if (resultado.equals("")) {
            cargarDatos();          
        }
        return resultado;
    }
//    
    private void cargarDatos() {
        if (txtBuscar.getText() == null || txtBuscar.getText().equals("")) {
            dataSucursales = FXCollections.observableArrayList(servicio.obtenerSucursaless());     
        } else {
            dataSucursales = FXCollections
                    .observableArrayList(servicio.obtenerSucursaless(txtBuscar.getText()));
        }
              
       tbSucursales.setItems(dataSucursales);
       tbSucursales.refresh();
    }
//
    private void abrirVentanaModal(Sucursales Sucursal, String titulo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NuevoEditarSucursales.fxml"));
        Parent root = (Parent) loader.load();
        
        NuevoEditarSucursalesController controller = loader.getController();
        controller.setController(this);
        controller.setSucursal(Sucursal);
        
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(titulo);
        stage.show();
    }
    
    private void definirColumnaEditar() {
        colEditar.setCellFactory(param -> new TableCell<String, String>() {
            final Button btn = new Button("Editar");
            
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    btn.setOnAction(event -> {
                        Sucursales Sucursales = (Sucursales) getTableRow().getItem();
                        try {
                            abrirVentanaModal(Sucursales, "Editar Sucursales");
                        } catch (IOException ex) {
                            Logger.getLogger(NuevoEditarSucursalesController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    setGraphic(btn);
                    setText(null);
                }
            }            
        });
    }

    private void definirColumnaEliminar() {
        colEliminar.setCellFactory(param -> new TableCell<String, String>() {
            final Button btn = new Button("Eliminar");
            
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    btn.setOnAction(event -> {
                        Sucursales Sucursales = (Sucursales) getTableRow().getItem();
                        servicio.eliminar(Sucursales);
                        cargarDatos();
                    });
                    setGraphic(btn);
                    setText(null);
                }
            }            
        });        
    }
}
