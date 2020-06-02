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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import loteria.bl.HibernateUtil;
import loteria.bl.TableActions;
import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;

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
    
    @FXML
    private TableView tableview;
    
    private ObservableList<ObservableList> TableViewdata;
    
    private Tbjuegos juego;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        juego = new Tbjuegos();
        TableViewBuildData();
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
    
    public void TableViewBuildData(){
          
          TableViewdata = FXCollections.observableArrayList();
          try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "select jgo.jgo_Id as `Id`, jgo.jgo_Descripcion as `Nombre`, jgo.jgo_CantNumeros as `Numeros`, jgo.jgo_MinNumero as `Numero minimo`, jgo.jgo_MaxNumero as `Numero maximo` , jgo.jgo_ApuestaMin as `Apuesta minima`\n" +
                            "  FROM tbjuegos jgo";
            //ResultSet
            ResultSet rs;
            rs = session.doReturningWork(new ReturningWork<ResultSet>() {
                @Override
                public ResultSet execute(Connection cnctn) throws SQLException {
                   return cnctn.createStatement().executeQuery(SQL);
                }
            });
            
            session.close();
            //rs = c.createStatement().executeQuery(SQL);

            ///Table columns declaration
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;                
                TableColumn col = new TableColumn(rs.getMetaData().getColumnLabel(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });

                tableview.getColumns().addAll(col); 
                System.out.println("Column ["+i+"] ");
            }

            //Data to list
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                TableViewdata.add(row);

            }

            //FINALLY ADDED TO TableView
            tableview.setItems(TableViewdata);
            tableview.refresh();
          }catch(Exception e){
              e.printStackTrace();
              System.out.println("Error on Building Data");             
          }
      }
    
    public void GuardarJuego(){
        juego.setJgoDescripcion(txtNombre.getText());
        juego.setJgoCantNumeros(Integer.parseInt(txtNumeros.getText()));
        juego.setJgoMinNumero(Integer.parseInt(txtMinNumero.getText()));
        juego.setJgoMaxNumero(Integer.parseInt(txtMaxNumero.getText()));
        juego.setJgoApuestaMin(Integer.parseInt(txtApuestaMinima.getText()));
        
        
        txtNombre.clear();
        txtNumeros.clear();
        txtApuestaMinima.clear();
        txtMaxNumero.clear();
        txtApuestaMinima.clear();
                
        TableActions ta = new TableActions();
        
        ta.TbJuegosInsert(juego);
        juego = new Tbjuegos();
        tableview.getColumns().clear();
        TableViewBuildData();
    }
    
}
