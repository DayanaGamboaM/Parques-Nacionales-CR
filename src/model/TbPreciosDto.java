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
@XmlRootElement(name = "TbPreciosDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class TbPreciosDto {

    @XmlTransient
    public SimpleStringProperty preId;
    @XmlTransient
    public SimpleStringProperty preModalidad;
    @XmlTransient
    public SimpleStringProperty prePrecio;
    @XmlTransient
    private Boolean modificado;

    public TbPreciosDto() {
        this.modificado = false;
        this.preId = new SimpleStringProperty();
        this.preModalidad = new SimpleStringProperty();
        this.prePrecio = new SimpleStringProperty();
    }
    
    public TbPreciosDto(TbPrecios tbprecios) {
        this();
        this.preId.set(tbprecios.getPreId().toString());
        this.preModalidad.set(tbprecios.getPreModalidad());
        this.prePrecio.set(tbprecios.getPrePrecio().toString());
        
    }

    public Long getPreId() {
        if(preId.get()!=null && !preId.get().isEmpty())
            return Long.valueOf(preId.get());
        else
            return null;
    }

    public void setPreId(Long preId) {
        this.preId.set(preId.toString());
    }
    
    public String getPreModalidad() {
        return preModalidad.get();
    }

    public void setPreModalidad(String preModalidad) {
        this.preModalidad.set(preModalidad);
    }
    
    public Long getPrePrecio() {
        if(prePrecio.get()!=null && !prePrecio.get().isEmpty())
            return Long.valueOf(prePrecio.get());
        else
            return null;
    }

    public void setPrePrecio(Long prePrecio) {
        this.prePrecio.set(prePrecio.toString());
    }
    
    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    @Override
    public String toString() {
        return "TbPaisesDto{" + "preId=" + preId + ", preModalidad=" + preModalidad + ", prePrecio=" + prePrecio +'}';
    }
}