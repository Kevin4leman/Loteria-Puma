/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteria.fx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

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
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colFecha;

    @FXML
    private TableColumn<?, ?> colImpuesto;

    @FXML
    private TableColumn<Juegos, Double> colTotal;

    @FXML
    private TableColumn<Factura, Boolean> colActivo;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
