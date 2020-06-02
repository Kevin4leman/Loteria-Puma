/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteria.fx;

import Modelo.Tbjuegos;
import Modelo.Tbusuarios;
import javafx.scene.control.TextField;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import loteria.bl.TableActions;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class IngresarJuegosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtNombre;
    
    @FXML
    private TextField txtNumeros;
    
    @FXML
    private TextField txtMinNumero;
    
    @FXML
    private TextField txtMaxNumero;
    
    @FXML
    private TextField txtApuestaMinima;
    
    private Tbjuegos juego;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        juego = new Tbjuegos();
    }    
    
    @FXML
    public void agregarImagen() {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extensiones = 
          new FileChooser.ExtensionFilter(
            "Imagenes", "*.jpg", "*.png");

        fc.getExtensionFilters().add(extensiones);

        File archivo = fc.showOpenDialog(null);

        if (archivo != null) {
            Image image = new Image(archivo.toURI().toString());
            juego.setImageView(image);
        }
    }
    /*
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
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Eliminar " + user.getUserName() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
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
    */
    public void GuardarJuego(){
        juego.setJgoDescripcion(txtNombre.getText());
        juego.setJgoCantNumeros(Integer.parseInt(txtNumeros.getText()));
        juego.setJgoMinNumero(Integer.parseInt(txtMinNumero.getText()));
        juego.setJgoMaxNumero(Integer.parseInt(txtMaxNumero.getText()));
        juego.setJgoApuestaMin(Integer.parseInt(txtApuestaMinima.getText()));
        
        TableActions ta = new TableActions();
        
        ta.TbJuegosInsert(juego);
        juego = new Tbjuegos();
    }
    
}
