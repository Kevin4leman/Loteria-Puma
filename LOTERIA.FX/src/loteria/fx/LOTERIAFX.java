/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteria.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class LOTERIAFX extends Application {
  public static Boolean isSplashLoaded=false;
    static Stage stage;
    
    public static Stage getStage(){
        return stage;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        LOTERIAFX.stage=stage;
       Parent root = FXMLLoader.load(getClass()
                .getResource("/loteria/fx/Menu/main.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Punto de ventas");
        stage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
