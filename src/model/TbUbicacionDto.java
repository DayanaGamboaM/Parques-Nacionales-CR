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
@XmlRootElement(name = "TbUbicacionDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class TbUbicacionDto {

    @XmlTransient
    public SimpleStringProperty ubiId;
    @XmlTransient
    public SimpleStringProperty ubiProvincia;
    @XmlTransient
    public SimpleStringProperty ubiCanton;
    @XmlTransient
    public SimpleStringProperty ubiDistrito;
    @XmlTransient
    private Boolean modificado;

    public TbUbicacionDto() {
        this.modificado = false;
        this.ubiId = new SimpleStringProperty();
        this.ubiProvincia = new SimpleStringProperty();
        this.ubiCanton = new SimpleStringProperty();
        this.ubiDistrito = new SimpleStringProperty();
    }
    
    public TbUbicacionDto(TbUbicacion tbubicacion) {
        this();
        this.ubiId.set(tbubicacion.getUbiId().toString());
        this.ubiProvincia.set(tbubicacion.getUbiProvincia());
        this.ubiCanton.set(tbubicacion.getUbiCanton());
        this.ubiDistrito.set(tbubicacion.getUbiDistrito());
        
    }

    public Long getUbiId() {
        if(ubiId.get()!=null && !ubiId.get().isEmpty())
            return Long.valueOf(ubiId.get());
        else
            return null;
    }

    public void setUbiId(Long ubiId) {
        this.ubiId.set(ubiId.toString());
    }
    
    public String getUbiProvincia() {
        return ubiProvincia.get();
    }

    public void setUbiProvincia(String ubiProvincia) {
        this.ubiProvincia.set(ubiProvincia);
    }
    
    public String getUbiCanton() {
        return ubiCanton.get();
    }

    public void setUbiCanton(String ubiCanton) {
        this.ubiCanton.set(ubiCanton);
    }
    
    public String getUbiDistrito() {
        return ubiDistrito.get();
    }

    public void setUbiDistrito(String ubiDistrito) {
        this.ubiDistrito.set(ubiDistrito);
    }
    
    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    @Override
    public String toString() {
        return "TbUbicacionDto{" + "ubiId=" + ubiId + ", ubiProvincia=" + ubiProvincia + ", ubiCanton=" + ubiCanton +", ubiDistrito=" + ubiDistrito +'}';
    }
}