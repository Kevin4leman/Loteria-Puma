package Modelo;
// Generated 01-jun-2020 14:41:13 by Hibernate Tools 4.3.1



/**
 * Tbjugadadetalle generated by hbm2java
 */
public class Tbjugadadetalle  implements java.io.Serializable {


     private Integer jdetId;
     private Tbjugada tbjugada;
     private int jdetNumero;

    public Tbjugadadetalle() {
    }

    public Tbjugadadetalle(Tbjugada tbjugada, int jdetNumero) {
       this.tbjugada = tbjugada;
       this.jdetNumero = jdetNumero;
    }
   
    public Integer getJdetId() {
        return this.jdetId;
    }
    
    public void setJdetId(Integer jdetId) {
        this.jdetId = jdetId;
    }
    public Tbjugada getTbjugada() {
        return this.tbjugada;
    }
    
    public void setTbjugada(Tbjugada tbjugada) {
        this.tbjugada = tbjugada;
    }
    public int getJdetNumero() {
        return this.jdetNumero;
    }
    
    public void setJdetNumero(int jdetNumero) {
        this.jdetNumero = jdetNumero;
    }




}


