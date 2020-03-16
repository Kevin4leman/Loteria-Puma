/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteria.bl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name="tbSucursales")
public class Sucursales {
    private SimpleIntegerProperty suc_Id;
    private SimpleIntegerProperty suc_RTN;
    private SimpleStringProperty suc_NombreSuc;
    private SimpleStringProperty suc_Direccion;
    private SimpleStringProperty suc_Encargado;
    private SimpleObjectProperty imageView;
    private byte[] imagen;  

    public Sucursales() {
        suc_Id =  new SimpleIntegerProperty();
        suc_RTN =  new SimpleIntegerProperty();;
        suc_NombreSuc = new SimpleStringProperty();
        suc_Direccion = new SimpleStringProperty();
        suc_Encargado = new SimpleStringProperty();
        imageView = new SimpleObjectProperty();
        imagen = "0".getBytes(); 
    }

    
    public SimpleIntegerProperty idProperty() {
        return suc_Id;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getCodigo() {
        return suc_Id.get();
    }

    public void setCodigo(int Codigo) {
        this.suc_Id.set(Codigo);
    }

    public int getRTN() {
        return suc_RTN.get();
    }

    public void setRTN(int RTN) {
        this.suc_RTN.set(RTN);
    }

     public SimpleIntegerProperty RTNProperty() {
        return suc_RTN;
    }
    
    public String getNombreSucursal() {
        return suc_NombreSuc.get();
    }

    public void setNombreSucursal(String Nombre) {
        this.suc_NombreSuc.set(Nombre);
    }

     public SimpleStringProperty NombreProperty() {
        return suc_NombreSuc;
    }
    
    public String getDireccion() {
        return suc_Direccion.get();
    }

    public void setDireccion(String Direccion) {
        this.suc_Direccion.set(Direccion);
    }

     public SimpleStringProperty DireccionProperty() {
        return suc_Direccion;
    }
     
    public String getResponsable() {
        return suc_Encargado.get();
    }

    public void setResponsable(String Responsable) {
        this.suc_Encargado.set(Responsable);
    }
    
     public SimpleStringProperty ResponsableProperty() {
        return suc_Encargado;
    }
    @Lob
    @Column(name = "imagen", columnDefinition = "LONGBLOB")
    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;

        Image img = new Image(new ByteArrayInputStream(imagen));        
        imageViewProperty().set(img);
    }

    @Transient
    public Image getImageView() {
        Image img = new Image(new ByteArrayInputStream(imagen)) {};        
        return img;        
    }


    public void setImageView(Image image) {  
        if (image == null) {
            setImagen("0".getBytes());
            return;
        }

        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        try {
            ImageIO.write(bImage, "png", stream);
            byte[] bytes  = stream.toByteArray();
            stream.close();
            setImagen(bytes);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        imageView.set(image);
    }

    public SimpleObjectProperty imageViewProperty() {   
        return imageView;
    }    
    
}
