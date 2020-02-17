/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteria.bl;

/**
 *
 * @author Admin
 */
public class Sucursales {
    private int Codigos;
    private int RTN;
    private String Nombre;
    private String Direccion;
    private String Encargado;
    private String Responsable;

    public String getEncargado() {
        return Encargado;
    }

    public void setEncargado(String Encargado) {
        this.Encargado = Encargado;
    }

    
    
    public int getCodigo() {
        return Codigos;
    }

    public void setCodigo(int Codigo) {
        this.Codigos = Codigo;
    }

    public int getRTN() {
        return RTN;
    }

    public void setRTN(int RTN) {
        this.RTN = RTN;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getResponsable() {
        return Encargado;
    }

    public void setResponsable(String Responsable) {
        this.Encargado = Responsable;
    }

    
}
