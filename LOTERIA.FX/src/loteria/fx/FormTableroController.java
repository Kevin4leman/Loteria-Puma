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
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
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
    private PieChart pieChartVentas;

    @FXML
    private BarChart barChartVentas;
    
    
    @FXML
    private TableView tableview;
    
    private ObservableList<ObservableList> TableViewdata;
    
    private ObservableList <PieChart.Data> PieChartData;
    
    private XYChart.Series<String, Double> BarChartData;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableViewBuildData();
        PieVhartBuildData();
        BarChartBuildData();
    }    
    
    public void BarChartBuildData()
    {
        BarChartData = new XYChart.Series<>();
          try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "select Count(th.tkhe_Id) as `Tickets vendidos`, DATE_FORMAT(th.tkhe_FechaVenta, \"%W %d\") as `Fecha` \n" +
                            "  from tbticketheader th\n" +
                            "  GROUP BY DATE(th.tkhe_FechaVenta)\n" +
                            "LIMIT 7;";
            //ResultSet
            ResultSet rs;
            rs = session.doReturningWork(new ReturningWork<ResultSet>() {
                @Override
                public ResultSet execute(Connection cnctn) throws SQLException {
                    cnctn.createStatement().executeQuery(" SET lc_time_names = 'es_MX'");
                   return cnctn.createStatement().executeQuery(SQL);
                }
            });
            session.close();
            
            while (rs.next()) {
                String name = rs.getString("Fecha");
                Double no = rs.getDouble("Tickets vendidos");
                BarChartData = new XYChart.Series<>();
                BarChartData.getData().add(new XYChart.Data<>(name, no));
                barChartVentas.getData().addAll(BarChartData);
            }
          }catch(Exception e){
              System.out.println("Error on DB connection");
              return;
          }
    }
    
    public void PieVhartBuildData(){
          PieChartData = FXCollections.observableArrayList();
          try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT COUNT(jda.jda_id) as `Jugadas vendidas`, CONCAT(jgo.jgo_Descripcion, \" - \" ,COUNT(jda.jda_id)) as `Juego`\n" +
                            "  FROM tbjugada jda inner join tbjuegos jgo on jda.jgo_Id = jgo.jgo_Id\n" +
                            "  group by jgo.jgo_Id;";
            //ResultSet
            ResultSet rs;
            rs = session.doReturningWork(new ReturningWork<ResultSet>() {
                @Override
                public ResultSet execute(Connection cnctn) throws SQLException {
                   return cnctn.createStatement().executeQuery(SQL);
                }
            });
            session.close();
            while(rs.next()){
                //adding data on piechart data
                PieChartData.add(new PieChart.Data(rs.getString("Juego"), rs.getDouble("Jugadas vendidas")));
            }
            pieChartVentas.getData().addAll(PieChartData);
            
          }catch(Exception e){
              System.out.println("Error on DB connection");
              return;
          }
 
      }
    
    public void TableViewBuildData(){
          
          TableViewdata = FXCollections.observableArrayList();
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
            
            session.close();
            //rs = c.createStatement().executeQuery(SQL);

            ///Table columns declaration
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
    
}
