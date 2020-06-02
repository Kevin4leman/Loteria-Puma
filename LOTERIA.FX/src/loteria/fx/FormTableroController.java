/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteria.fx;

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
import javafx.scene.chart.BarChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import loteria.bl.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.jdbc.Work;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FormTableroController implements Initializable {

      @FXML
    private VBox pieChartVentas;

    @FXML
    private BarChart<?, ?> barChartVentas;
    
    
    @FXML
    private TableView tableview;
    
    private ObservableList<ObservableList> data;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buildData();
    }    
    
    public void buildData(){
          
          data = FXCollections.observableArrayList();
          try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "select th.tkhe_Id as `ID`, th.tkhe_FechaVenta as `Fecha de venta`, suc.nombreSucursal as `Sucursal` ,sum(jda.jda_Apuesta) as `Inversion total`\n" +
                        "  from tbticketheader th inner join tbjugada jda on jda.tkhe_Id = th.tkhe_Id\n" +
                        "  inner join tbsucursales suc on suc.codigo = th.suc_Id\n" +
                        "  GROUP by th.tkhe_Id\n" +
                        "  order by th.tkhe_Id DESC\n" +
                        "  limit 10";
            //ResultSet
            ResultSet rs;
            rs = session.doReturningWork(new ReturningWork<ResultSet>() {
                @Override
                public ResultSet execute(Connection cnctn) throws SQLException {
                   return cnctn.createStatement().executeQuery(SQL);
                }
            });
            //rs = c.createStatement().executeQuery(SQL);

            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;                
                TableColumn col = new TableColumn(rs.getMetaData().getColumnLabel(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });

                tableview.getColumns().addAll(col); 
                System.out.println("Column ["+i+"] ");
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);
            tableview.refresh();
          }catch(Exception e){
              e.printStackTrace();
              System.out.println("Error on Building Data");             
          }
      }
    
}
