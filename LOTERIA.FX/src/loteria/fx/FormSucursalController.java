/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteria.fx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import loteria.bl.Prueba;
import loteria.bl.Sucursales;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FormSucursalController implements Initializable {

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtRTN;

    @FXML
    private TextField txtNombreSuc;

    @FXML
    private TextField txtNombreRepe;

    @FXML
    private TextField txtDireccion;
    
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
    
    ObservableList<Sucursales> dataSucursales;
            
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Suc = new Sucursales();
        
        colCodigo.setCellValueFactory(new PropertyValueFactory("Codigo"));
        colRTN.setCellValueFactory(new PropertyValueFactory("RTN"));
        colNombreSuc.setCellValueFactory(new PropertyValueFactory("Nombre"));
        //colEncargado.setCellValueFactory(new PropertyValueFactory("Encargado"));
        colDireccion.setCellValueFactory(new PropertyValueFactory("Direccion"));
        colResponsable.setCellValueFactory(new PropertyValueFactory("Responsable"));
        
        dataSucursales = FXCollections.observableArrayList();
        
        tbSucursales.setItems(dataSucursales);
    }    
    
    
    public void RegistrarSuc()
    {
        Sucursales NewSucur = new Sucursales();
        
        NewSucur.setCodigo(Integer.parseInt((txtCodigo.getText())));
        NewSucur.setRTN(Integer.parseInt((txtRTN.getText())));
        NewSucur.setNombre(txtNombreSuc.getText());
        NewSucur.setResponsable(txtNombreRepe.getText());
        NewSucur.setDireccion(txtDireccion.getText());
        NewSucur.setEncargado(txtNombreRepe.getText());
        
        dataSucursales.add(NewSucur);
    }
    
    
}
