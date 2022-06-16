/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.time.ZoneId;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import util.LocalDateAdapter;

/**
 *
 * @author 
 */
@XmlRootElement(name = "TbTarjetasDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class TbTarjetasDto {

    @XmlTransient
    public SimpleStringProperty tarId;
    @XmlTransient
    public SimpleStringProperty tarNombre;
    @XmlTransient
    public SimpleStringProperty tarComision;
    @XmlTransient
    private Boolean modificado;

    public TbTarjetasDto() {
        this.modificado = false;
        this.tarId = new SimpleStringProperty();
        this.tarNombre = new SimpleStringProperty();
        this.tarComision = new SimpleStringProperty();
    }
    
    public TbTarjetasDto(TbTarjetas tbtarjetas) {
        this();
        this.tarId.set(tbtarjetas.getTarId().toString());
        this.tarNombre.set(tbtarjetas.getTarNombre());
        this.tarComision.set(tbtarjetas.getTarComision().toString());
        
    }

    public Long getTarId() {
        if(tarId.get()!=null && !tarId.get().isEmpty())
            return Long.valueOf(tarId.get());
        else
            return null;
    }

    public void setTarId(Long tarId) {
        this.tarId.set(tarId.toString());
    }
    
    public String getTarNombre() {
        return tarNombre.get();
    }

    public void setTarNombre(String tarNombre) {
        this.tarNombre.set(tarNombre);
    }
    
    public Long getTarComision() {
        if(tarComision.get()!=null && !tarComision.get().isEmpty())
            return Long.valueOf(tarComision.get());
        else
            return null;
    }

    public void setTarComision(Long tarComision) {
        this.tarComision.set(tarComision.toString());
    }
    
    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    @Override
    public String toString() {
        return "TbPaisesDto{" + "tarId=" + tarId + ", tarNombre=" + tarNombre + ", tarComision=" + tarComision +'}';
    }
}