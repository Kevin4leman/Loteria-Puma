/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteria.bl;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Admin
 */
public class Sucursales {
    private SimpleIntegerProperty Codigos;
    private SimpleIntegerProperty RTN;
    private SimpleStringProperty Nombre;
    private SimpleStringProperty Direccion;
    private SimpleStringProperty Responsable;

    public Sucursales() {
        Codigos =  new SimpleIntegerProperty();
        RTN =  new SimpleIntegerProperty();;
        Nombre = new SimpleStringProperty();
        Direccion = new SimpleStringProperty();
        Responsable = new SimpleStringProperty();
    }

    
    public SimpleIntegerProperty idProperty() {
        return Codigos;
    }

    
    public int getCodigo() {
        return Codigos.get();
    }

    public void setCodigo(int Codigo) {
        this.Codigos.set(Codigo);
    }

    public int getRTN() {
        return RTN.get();
    }

    public void setRTN(int RTN) {
        this.RTN.set(RTN);
    }

     public SimpleIntegerProperty RTNProperty() {
        return RTN;
    }
    
    public String getNombre() {
        return Nombre.get();
    }

    public void setNombre(String Nombre) {
        this.Nombre.set(Nombre);
    }

     public SimpleStringProperty NombreProperty() {
        return Nombre;
    }
    
    public String getDireccion() {
        return Direccion.get();
    }

    public void setDireccion(String Direccion) {
        this.Direccion.set(Direccion);
    }

     public SimpleStringProperty DireccionProperty() {
        return Direccion;
    }
     
    public String getResponsable() {
        return Responsable.get();
    }

    public void setResponsable(String Responsable) {
        this.Responsable.set(Responsable);
    }
    
     public SimpleStringProperty ResponsableProperty() {
        return Responsable;
    }
    
    
}
