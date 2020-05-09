package sample;

import Modelo.Tbusuarios;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import loteria.bl.LoginServices;
import loteria.fx.LOTERIAFX;

import loteria.fx.NuevoEditarSucursalesController;

public class LoginController implements Initializable {

    @FXML
    ImageView ic;
    ActionEvent event;

    @FXML
    TextField Usernametxt;

    @FXML
    PasswordField Passwordtxt;

    @FXML
    private Button btn_login;

    private double xOffset = 0;
    private double yOffset = 0;

    private Tbusuarios user = null;
    
    private Stage CurrentStage = null;

    public Stage getCurrentStage() {
        return CurrentStage;
    }

    public void setCurrentStage(Stage CurrentStage) {
        this.CurrentStage = CurrentStage;
    }

    public Tbusuarios getUser() {
        return user;
    }

    public void setUser(Tbusuarios user) {
        this.user = user;
    }

    LoginServices logService;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logService = new LoginServices();
    }

    @FXML
    public void IniciarSesion() throws IOException {
        Tbusuarios userLog = null;
        try {
            userLog = logService.Verifyuser(Usernametxt.getText(), Passwordtxt.getText());
            if (userLog != null) {
                user = userLog;
                CurrentStage.hide();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * ** minimize ***
     */
    @FXML
    public void minclick(MouseEvent event) throws IOException {
        ((Stage) ((Circle) event.getSource()).getScene().getWindow()).setIconified(true);
    }

    /**
     * ** close screen ***
     */
    @FXML
    public void closeclick(MouseEvent event) throws IOException {
        System.exit(0);
    }
}
