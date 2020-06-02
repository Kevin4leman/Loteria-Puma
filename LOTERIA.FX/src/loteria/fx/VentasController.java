/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteria.fx;

import Modelo.Tbjuegos;
import Modelo.Tbjugada;
import Modelo.Tbjugadadetalle;
import Modelo.Tbsorteos;
import Modelo.Tbsucursales;
import Modelo.Tbticketheader;
import Modelo.Tbusuarios;
import com.jfoenix.controls.JFXTextField;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javax.naming.Binding;
import loteria.bl.TableActions;
import loteria.bl.VentasServices;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;

/**
 * FXML Controller class
 *
 * @author Chris
 */




public class VentasController implements Initializable {


    @FXML
    TextField txtNumero;
    
    @FXML
    HBox GamesBar;
    
    @FXML
    HBox BetsBar;
    
    @FXML
    Label GameLabel;
    
    @FXML
    Label CurrentBetLabel;
    
    @FXML
    Label TotalPlaysLabel;
    
    @FXML
    Label TotalBetsLabel;
    
    @FXML
    HBox NumbersBar;
    
    @FXML
    private TableColumn JugadaColumn;
    
    @FXML
    private TableColumn ApuestaColumn;
    
    @FXML
    private TableView TicketTable; 
    
    VentasServices Servicio;
    
    private Tbjuegos CurrentGame;
    
    private int CurrentBet;
    
    private SimpleIntegerProperty TotalBets;
    
    private SimpleIntegerProperty TotalPlays;
    
    private ArrayList<ArrayList<Tbjugadadetalle>> JugadasTicket;
    
    private Tbticketheader TicketH; 
    
    private ObservableList<VentasTbViewObj> TableData; 
    
    private TableActions TActions;
    
    private ArrayList<VentasTbViewObj> RowList;
    
    private Tbusuarios User;

    public Tbusuarios getUser() {
        return User;
    }

    public void setUser(Tbusuarios User) {
        this.User = User;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TActions = new TableActions();
        CurrentBet = 0;
        Servicio = new VentasServices();
        JugadasTicket = new ArrayList<>();
        TotalBets = new SimpleIntegerProperty(0);
        TotalPlays = new SimpleIntegerProperty(0);
        TableData = FXCollections.observableArrayList();
        RowList = new ArrayList<>();
        FillGamesBar();
        
        TotalPlaysLabel.textProperty().bind(TotalPlays.asString());
        TotalBetsLabel.textProperty().bind(TotalBets.asString());
        
        JugadaColumn.setCellValueFactory(new PropertyValueFactory<>("Jugada"));
        ApuestaColumn.setCellValueFactory(new PropertyValueFactory<>("Apuesta"));
        
        //NewTicket();
        
        
        System.out.println(Servicio.GenerarSerie(15));
    }
    
    @FXML
    public void NewTicket()
    {
        TicketH = new Tbticketheader();
        TicketH.setTbsucursales(User.getTbsucursales());
        UpdateCurrentbet(0);
        TotalBets.setValue(0);
        TotalPlays.setValue(0);
        JugadasTicket = new ArrayList<>();
        ClearAllNumbers();
    }
    
    
    private void FillGamesBar()
    {
        ArrayList<Tbjuegos> Juegos = Servicio.ObtenerJuegos();
        ImageView icono;
        for(Tbjuegos juego: Juegos)
        {
            Button GameBtn = new Button();
            
            GameBtn.setMinSize(75, 60);
            try
            {
                icono = new ImageView(juego.getImageView());
                icono.setFitHeight(60);
                icono.setFitWidth(75);
                GameBtn.setGraphic(icono);
                
            }
            catch(Exception ex)
            {
                GameBtn.setText(juego.getJgoDescripcion());
            }
            
            GameBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                UpdateCurrentbet(0);
                Object object = event.getSource();
                CurrentGame = juego;
                TicketH.setTbsorteos(Servicio.GetLastSorteo(CurrentGame));
                System.out.println("Juego actual: " + CurrentGame.getJgoDescripcion());
                FillBetsBar(juego.getJgoApuestaMin());
                FillNumbersBar(juego.getJgoCantNumeros());
                GameLabel.setText(juego.getJgoDescripcion());
            });
            GamesBar.getChildren().add(GameBtn);
        }
    }
    

    private void FillBetsBar(int MinBet)
    {
        BetsBar.getChildren().clear();
        UpdateCurrentbet(0);
        for(int i = 1; i <= 10; i++)
        {
            int CurrentVal = MinBet * i;
            Button BetValueBtn = new Button();
            BetValueBtn.setMinSize(75, 60);
            BetValueBtn.setText(Integer.toString(MinBet * i) + ".00");
            BetValueBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                Object object = event.getSource();
                UpdateCurrentbet(CurrentVal);
            });
           
            BetsBar.getChildren().add(BetValueBtn);
        }
    }
    
    @FXML
    private void GuardarJugada()
    {
        VentasTbViewObj ItemToAdd = new VentasTbViewObj();
        Tbjugada Jugada = new Tbjugada();
        Jugada.setJdaApuesta(CurrentBet);
        Jugada.setTbjuegos(CurrentGame);
        Jugada.setTbticketheader(TicketH);
        Tbjugadadetalle Detalle;
        ArrayList<Tbjugadadetalle> ListaNumeros = new ArrayList<Tbjugadadetalle>();
        boolean JugadaValida = true;
        TotalBets.set(TotalBets.getValue() + CurrentBet);
        
        for(Node NumberField: NumbersBar.getChildren())
        {
            if(((TextField)NumberField).getText().length() < 2)
            {
                JugadaValida = false;
                return;
            }
        }
        if(JugadaValida)
        {
            for(Node NumberField: NumbersBar.getChildren())
            {
                Detalle = new Tbjugadadetalle();
                Detalle.setJdetNumero(Integer.parseInt(((TextField)NumberField).getText()));
                Detalle.setTbjugada(Jugada);
                ListaNumeros.add(Detalle);
            }
        }
        ClearAllNumbers();
        System.out.println("Numeros de la ultima jugada: ");
        for(Tbjugadadetalle n: ListaNumeros)
        {
            System.out.println("Numero: " + n.getJdetNumero() + " Juego: " + n.getTbjugada().getTbjuegos().getJgoDescripcion() + " Apuesta: " + n.getTbjugada().getJdaApuesta());
        }
        
        ItemToAdd.setJugada(Servicio.JugadaToString(ListaNumeros));
        ItemToAdd.setApuesta(Jugada.getJdaApuesta());
        
        RowList.add(ItemToAdd);
        JugadasTicket.add(ListaNumeros);
        TotalPlays.set(JugadasTicket.size());
        TableData = FXCollections.observableArrayList(RowList);
        TicketTable.setItems(TableData);
        TicketTable.refresh();
    }
    
    @FXML
    private void ProcesarTicket() throws JRException
    {
        if(JugadasTicket.size() > 0)
        {
            TicketH.setTkheFechaVenta(Date.from(Instant.now()));
            TicketH.setTkheSerialNumber(Servicio.GenerarSerie(15));
            TicketH.setTkheId(TActions.TbTicketHeaderInsert(TicketH));
            int i = 0; 
            for(ArrayList<Tbjugadadetalle> Jda : JugadasTicket)
            {
                Jda.get(0).getTbjugada().setJdaId(TActions.TbJuagadaInsert(Jda.get(0).getTbjugada()));
                i++;
                for(Tbjugadadetalle jgdet: Jda)
                {
                    TActions.TbJugadaDetalleInsert(jgdet);
                }
            }
            TicketReportView ReportView = new TicketReportView();
            ReportView.ShowReport(TicketH);
            TableData.clear();
            TicketTable.refresh();
            NewTicket();
        }
    }
    
    private void UpdateCurrentbet(int bet)
    {
        CurrentBet = bet;
        CurrentBetLabel.setText(Integer.toString(CurrentBet));
        System.out.println("Apuesta Actual" + CurrentBet);
    }
    
    
    private void FillNumbersBar(int e)
    {
        NumbersBar.getChildren().clear();
        for(int i = 1; i <= e; i++)
        {
            TextField NumberField = new TextField();
            NumberField.setPrefSize(60, 45);
            NumberField.setFont(Font.font(31));
            NumberField.setPadding(new Insets(-10));
            NumberField.setEditable(false);
            NumberField.setAlignment(Pos.CENTER);
            
            NumbersBar.getChildren().add(NumberField);
        }
        ClearAllNumbers();
    }
  
    
    @FXML
    private void HandleButton1()
    {
        WriteNumber(1);
    }
    
    @FXML
    private void HandleButton2()
    {
        WriteNumber(2);
    }
    
    @FXML
    private void HandleButton3()
    {
        WriteNumber(3);
    }
    
    @FXML
    private void HandleButton4()
    {
        WriteNumber(4);
    }
    
    @FXML
    private void HandleButton5()
    {
        WriteNumber(5);
    }
    
    @FXML
    private void HandleButton6()
    {
        WriteNumber(6);
    }
    
    @FXML
    private void HandleButton7()
    {
        WriteNumber(7);
    }
    
    @FXML
    private void HandleButton8()
    {
        WriteNumber(8);
    }
    
    @FXML
    private void HandleButton9()
    {
        WriteNumber(9);
    }
    
    @FXML
    private void HandleButton0()
    {
        WriteNumber(0);
    }
    
    @FXML
    private void HandleButtonBackspace()
    {
        //Obtiene la longitd de la lista
        //Recorre cada elemento de la lista en orden inverso
        for(int i = NumbersBar.getChildren().size(); i > 0; i--)
        {
            //Parsea cada indice a Textfield para poder manipulkar la data dentro del control
            //Si la longitud es mayor a 0 entra al if
            if( ((TextField)NumbersBar.getChildren().get(i - 1)).getText().length() >= 1)
            {
                //Remueve el ultimo caracter del TextField usando el subtring(0, lengh - 1)
                ((TextField)NumbersBar.getChildren().get(i - 1)).setText(((TextField)NumbersBar.getChildren().get(i - 1)).getText().substring(0, ((TextField)NumbersBar.getChildren().get(i -1 )).getText().length() - 1));
                //Esta es la linea de codigo mas larga y vulgar que he visto.
                //Seguramente habia una forma mas simple de hacerlo pero me da miedo JAVA :^(
                return;
            }   
        }
    }
    
    private void ClearAllNumbers()
    {
        for(Node NumberField: NumbersBar.getChildren())
        {
            ((TextField)NumberField).setText("");
            
        }
    }
    
    private void WriteNumber(int Num)
    {
        for(Node NumberField: NumbersBar.getChildren())
        {
            
            if(((TextField)NumberField).getText().length() < 2 && Integer.parseInt(((TextField)NumberField).getText() + Num) <= CurrentGame.getJgoMaxNumero() && Integer.parseInt(((TextField)NumberField).getText() + Num) >= CurrentGame.getJgoMinNumero())
            {
                ((TextField)NumberField).setText(((TextField)NumberField).getText() + Integer.toString(Num));
                return;
            }
            else if(((TextField)NumberField).getText().length() == 0 && Num == 0 && Integer.parseInt(((TextField)NumberField).getText() + Num) <= CurrentGame.getJgoMaxNumero() && Integer.parseInt(((TextField)NumberField).getText() + Num) >= CurrentGame.getJgoMinNumero())
            {
                ((TextField)NumberField).setText(((TextField)NumberField).getText() + Integer.toString(Num));
                return;
            }
        }
    }
   
}
