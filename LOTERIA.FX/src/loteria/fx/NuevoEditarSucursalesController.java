/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteria.fx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import loteria.bl.Sucursales;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class NuevoEditarSucursalesController implements Initializable {

    @FXML
    private JFXTextField txtCodigo;

    @FXML
    private JFXTextField txtRTN;

    @FXML
    private JFXTextField txtNombreSuc;

    @FXML
    private JFXTextField txtNombreRepe;

    @FXML
    private JFXTextField txtDireccion;
    
    @FXML
    JFXButton btnCancelar;
    
    @FXML
    ImageView imgViewImagen;
    
    private FormSucursalController controller;
    private Sucursales Sucursal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void aceptar() {
        String resultado = controller.guardar(Sucursal);
        if (resultado.equals("")) {
            cerrar();   
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sucursales");
            alert.setHeaderText("Errores de validación de datos");
            alert.setContentText(resultado);
            alert.showAndWait();
        }
    }
    
    public void cancelar() {
        cerrar();
    }
    
    private void cerrar() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    
    public void setController(FormSucursalController controller) {
        this.controller = controller;
    }
    
    public void setSucursal(Sucursales Sucursal) {
        this.Sucursal = Sucursal;
        
        txtCodigo.textProperty().bindBidirectional(Sucursal.idProperty(), new NumberStringConverter());
        txtRTN.textProperty().bindBidirectional(Sucursal.RTNProperty(), new NumberStringConverter());        
        txtNombreSuc.textProperty().bindBidirectional(Sucursal.NombreProperty());        
        txtDireccion.textProperty().bindBidirectional(Sucursal.DireccionProperty());        
        txtNombreRepe.textProperty().bindBidirectional(Sucursal.ResponsableProperty());
        imgViewImagen.imageProperty().bind(Sucursal.imageViewProperty());
    }
    public void agregarImagen() {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extensiones = 
          new FileChooser.ExtensionFilter(
            "Imagenes", "*.jpg", "*.png");

        fc.getExtensionFilters().add(extensiones);

        File archivo = fc.showOpenDialog(null);

        if (archivo != null) {
            Image image = new Image(archivo.toURI().toString());
            Sucursal.setImageView(image);
        }

    }

    public void removerImagen() {
        Sucursal.setImageView(null);
    }
}

