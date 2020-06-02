/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteria.fx;

import Modelo.Tbsucursales;
import Modelo.Tbusuarios;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import loteria.bl.LotoServices;
import loteria.bl.TableActions;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class UsuariosController implements Initializable {
    
    @FXML
    private TableView TablaUsers; 
    
    @FXML
    private TableColumn colEliminar;
    
    @FXML
    private TableColumn UserIdCol;
    
    @FXML
    private TableColumn userNameCol;
    
    @FXML
    private TableColumn userRoleCol;
    
    @FXML
    private TableColumn tbSucursalCol;
    
    @FXML
    private TextField userNameTxt;
    
    @FXML
    private TextField userPasswordtxt;
    
    @FXML
    private ComboBox<String> userRolCbx;
    
    @FXML
    private ComboBox<Tbsucursales> SucursalCbx;
    
    private Tbusuarios UserNew;
    
    ObservableList<Tbusuarios> dataUsuarios;
    /**
     * Initializes the controller class.
     */
    private TableActions ta;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ta = new TableActions();
        
        UserNew = new Tbusuarios();
        
        UserIdCol.setCellValueFactory(new PropertyValueFactory<>("UserId"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        userRoleCol.setCellValueFactory(new PropertyValueFactory<>("userRole"));
        tbSucursalCol.setCellValueFactory(new PropertyValueFactory<>("SucursalNombre"));
        
        userRolCbx.getItems().addAll("Admin","Vendedor");
        LotoServices serv = new LotoServices();
        SucursalCbx.getItems().addAll(serv.obtenerSucursaless());
        cargarDatos();
        definirColumnaEliminar();
        
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
                        TablaUsers.getSelectionModel().select(getTableRow().getItem());
                        Tbusuarios user = (Tbusuarios) getTableRow().getItem();
                        Alert alert = new Alert(AlertType.CONFIRMATION, "Eliminar " + user.getUserName() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                        alert.showAndWait();
                        if (alert.getResult() == ButtonType.YES)
                        {
                        ta.eliminarUsuario(user);
                        cargarDatos();
                        }
                    });
                    setGraphic(btn);
                    setText(null);
                }
            }            
        });        
    }
    
     private void cargarDatos() {
       dataUsuarios = FXCollections.observableArrayList(ta.ObtenerUsuarios());     
       TablaUsers.setItems(dataUsuarios);
       TablaUsers.refresh();
    }
    
    public void RegistrarUsuario()
    {
        UserNew.setUserName(userNameTxt.getText());
        UserNew.setUserPassword(userPasswordtxt.getText());
        UserNew.setTbsucursales(SucursalCbx.getValue());
        UserNew.setUserRole(userRolCbx.getValue());
        
        ta.TbUsuariossInsert(UserNew);
        
        UserNew = new Tbusuarios();
        
        cargarDatos();
    }
}
