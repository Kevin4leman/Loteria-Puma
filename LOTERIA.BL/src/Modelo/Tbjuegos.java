package Modelo;
// Generated 01-jun-2020 14:41:13 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Tbjuegos generated by hbm2java
 */
public class Tbjuegos  implements java.io.Serializable {


     private Integer jgoId;
     private String jgoDescripcion;
     private Integer jgoCantNumeros;
     private Integer jgoMinNumero;
     private Integer jgoMaxNumero;
     private Integer jgoApuestaMin;
     private byte[] jgoImagen;
     private Set tbjugadas = new HashSet(0);
     private Set tbsorteoses = new HashSet(0);

    public Tbjuegos() {
    }

    public Tbjuegos(String jgoDescripcion, Integer jgoCantNumeros, Integer jgoMinNumero, Integer jgoMaxNumero, Integer jgoApuestaMin, byte[] jgoImagen, Set tbjugadas, Set tbsorteoses) {
       this.jgoDescripcion = jgoDescripcion;
       this.jgoCantNumeros = jgoCantNumeros;
       this.jgoMinNumero = jgoMinNumero;
       this.jgoMaxNumero = jgoMaxNumero;
       this.jgoApuestaMin = jgoApuestaMin;
       this.jgoImagen = jgoImagen;
       this.tbjugadas = tbjugadas;
       this.tbsorteoses = tbsorteoses;
    }
   
    public Integer getJgoId() {
        return this.jgoId;
    }
    
    public void setJgoId(Integer jgoId) {
        this.jgoId = jgoId;
    }
    public String getJgoDescripcion() {
        return this.jgoDescripcion;
    }
    
    public void setJgoDescripcion(String jgoDescripcion) {
        this.jgoDescripcion = jgoDescripcion;
    }
    public Integer getJgoCantNumeros() {
        return this.jgoCantNumeros;
    }
    
    public void setJgoCantNumeros(Integer jgoCantNumeros) {
        this.jgoCantNumeros = jgoCantNumeros;
    }
    public Integer getJgoMinNumero() {
        return this.jgoMinNumero;
    }
    
    public void setJgoMinNumero(Integer jgoMinNumero) {
        this.jgoMinNumero = jgoMinNumero;
    }
    public Integer getJgoMaxNumero() {
        return this.jgoMaxNumero;
    }
    
    public void setJgoMaxNumero(Integer jgoMaxNumero) {
        this.jgoMaxNumero = jgoMaxNumero;
    }
    public Integer getJgoApuestaMin() {
        return this.jgoApuestaMin;
    }
    
    public void setJgoApuestaMin(Integer jgoApuestaMin) {
        this.jgoApuestaMin = jgoApuestaMin;
    }
    public byte[] getJgoImagen() {
        return this.jgoImagen;
    }
    
    public void setJgoImagen(byte[] jgoImagen) {
        this.jgoImagen = jgoImagen;
    }
    public Set getTbjugadas() {
        return this.tbjugadas;
    }
    
    public void setTbjugadas(Set tbjugadas) {
        this.tbjugadas = tbjugadas;
    }
    public Set getTbsorteoses() {
        return this.tbsorteoses;
    }
    
    public void setTbsorteoses(Set tbsorteoses) {
        this.tbsorteoses = tbsorteoses;
    }




}


