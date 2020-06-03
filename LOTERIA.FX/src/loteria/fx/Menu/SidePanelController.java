package loteria.fx.Menu;

import Modelo.Tbusuarios;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class SidePanelController implements Initializable {

    @FXML
    JFXListView listView;

    private AbrirFormularioCallback callback;

    private Tbusuarios UsuarioActivo = null;

    public Tbusuarios getUsuarioActivo() {
        return UsuarioActivo;
    }

    public void setUsuarioActivo(Tbusuarios UsuarioActivo) {
        this.UsuarioActivo = UsuarioActivo;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }

    public void StartMenu()
    {
        
        if(this.UsuarioActivo.getUserRole().equals("Admin"))
        {
            listView.getItems().add(new Label("Juegos"));
            listView.getItems().add(new Label("Sucursales"));
            listView.getItems().add(new Label("Usuarios"));
            listView.getItems().add(new Label("FormTablero"));
        }
        else
        {
            listView.getItems().add(new Label("Ventas"));
        }
        
        listView.getItems().add(new Label("Salir"));
        //FormTablero
        listView.setOnMouseClicked(event -> {
            AbrirForm();
        });
    }
    
    private void AbrirForm()
    {
        Label label = (Label)listView.getSelectionModel().getSelectedItem();

            if (label.getText().equals("Salir")) {
                System.exit(0);
            } else {
                callback.abrirFormulario(label.getText(), UsuarioActivo);
            }
    }
    
    public void setCallback(AbrirFormularioCallback callback) {
        this.callback = callback;
    }
}
