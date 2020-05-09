/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteria.fx;

import Modelo.Tbusuarios;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.LoginController;
import loteria.fx.Menu.MainController;

/**
 *
 * @author Admin
 */
public class LOTERIAFX extends Application {

    public static Boolean isSplashLoaded = false;
    static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    Tbusuarios UsuarioActivo = null;
/*
    @Override
    public void start(Stage stage) throws Exception {
        LOTERIAFX.stage = stage;

        FXMLLoader MenuLoader = new FXMLLoader(getClass().getResource("/loteria/fx/Menu/main.fxml"));
        Parent root = MenuLoader.load();
        MainController MenuController = MenuLoader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Loterias");

        //stage.show();
        Stage LoginStage = new Stage();
        FXMLLoader loaderLogin = new FXMLLoader(getClass().getResource("/loteria/fx/sample/profile.fxml"));
        Parent LoginRoot = (Parent) loaderLogin.load();
        LoginController LogCon = loaderLogin.getController();

        Scene LoginScene = new Scene(LoginRoot);
        LoginStage.setScene(LoginScene);
        LoginStage.setTitle("Login");

        LoginStage.show();
        LogCon.setCurrentStage(LoginStage);
        LoginStage.setOnHiding(event -> {
            UsuarioActivo = LogCon.getUser();
            if (LogCon.getUser() != null) {
                stage.show();
                MenuController.setUser(LogCon.getUser());
                MenuController.IniciarMenu();
                System.out.println(MenuController.getUser().getUserName() + "");
            }
        });
    }
*/
    
        @Override
    public void start(Stage stage) throws Exception {
        LOTERIAFX.stage = stage;

        FXMLLoader MenuLoader = new FXMLLoader(getClass().getResource("/MenuTesting/MainMenu.fxml"));
        Parent root = MenuLoader.load();
        //MainController MenuController = MenuLoader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Loterias");

        stage.show();

    }
    private void UpdateUserData() {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
