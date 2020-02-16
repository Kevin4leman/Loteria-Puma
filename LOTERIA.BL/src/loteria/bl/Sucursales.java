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
    private int Codigo;
    private int RTN;
    private String Nombre;
    private String Direccion;
    private String NombreResponsable;

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
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

    public String getNombreResponsable() {
        return NombreResponsable;
    }

    public void setNombreResponsable(String NombreResponsable) {
        this.NombreResponsable = NombreResponsable;
    }
}
