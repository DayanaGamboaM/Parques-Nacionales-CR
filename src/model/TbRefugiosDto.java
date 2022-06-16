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
@XmlRootElement(name = "TbRefugiosDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class TbRefugiosDto {

    @XmlTransient
    public SimpleStringProperty refId;
    @XmlTransient
    public SimpleStringProperty refNombre;
    @XmlTransient
    public SimpleStringProperty refDescripcion;
    @XmlTransient
    public SimpleStringProperty refReconocimientos;
    @XmlTransient
    public SimpleStringProperty refDireccionexac;
    @XmlTransient
    public SimpleStringProperty refAreareservada;
    @XmlTransient
    public SimpleStringProperty refCoordgeo;
    @XmlTransient
    private Boolean modificado;

    public TbRefugiosDto() {
        this.modificado = false;
        this.refId = new SimpleStringProperty();
        this.refNombre = new SimpleStringProperty();
        this.refDescripcion = new SimpleStringProperty();
        this.refReconocimientos = new SimpleStringProperty();
        this.refDireccionexac = new SimpleStringProperty();
        this.refAreareservada = new SimpleStringProperty();
        this.refCoordgeo = new SimpleStringProperty();
        

    }
    
    public TbRefugiosDto(TbRefugios tbrefugios) {
        this();
        this.refId.set(tbrefugios.getRefId().toString());
        this.refNombre.set(tbrefugios.getRefNombre());
        this.refDescripcion.set(tbrefugios.getRefDescripcion());
        this.refReconocimientos.set(tbrefugios.getRefReconocimientos());
        this.refDireccionexac.set(tbrefugios.getRefDireccionexac());
        this.refAreareservada.set(tbrefugios.getRefAreareservada());
        this.refCoordgeo.set(tbrefugios.getRefCoordgeo());
        
    }

    public Long getRefId() {
        if(refId.get()!=null && !refId.get().isEmpty())
            return Long.valueOf(refId.get());
        else
            return null;
    }

    public void setRefId(Long refId) {
        this.refId.set(refId.toString());
    }
    
    public String getRefNombre() {
        return refNombre.get();
    }

    public void setRefNombre(String refNombre) {
        this.refNombre.set(refNombre);
    }
    
    public String getRefDescripcion() {
        return refDescripcion.get();
    }

    public void setRefDescripcion(String refDescripcion) {
        this.refDescripcion.set(refDescripcion);
    }
    
    public String getRefReconocimientos() {
        return refReconocimientos.get();
    }

    public void setRefReconocimientos(String refReconocimientos) {
        this.refReconocimientos.set(refReconocimientos);
    }
    
    
    public String getRefDireccionexac() {
        return refDireccionexac.get();
    }

    public void setRefDireccionexac(String refDireccionexac) {
        this.refDireccionexac.set(refDireccionexac);
    }
    
    public String getRefAreareservada() {
        return refAreareservada.get();
    }

    public void setRefAreareservada(String refAreareservada) {
        this.refAreareservada.set(refAreareservada);
    }
    
    public String getRefCoordgeo() {
        return refCoordgeo.get();
    }

    public void setRefCoordgeo(String refCoordgeo) {
        this.refCoordgeo.set(refCoordgeo);
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    @Override
    public String toString() {
        return "TbEspeciesDto{" + "refId=" + refId + ", refNombre=" + refNombre + ", refDescripcion=" + refDescripcion +", refReconocimientos=" + refReconocimientos +", refDireccionexac=" + refDireccionexac +", refAreareservada=" + refAreareservada +", refCoordgeo=" + refCoordgeo + '}';
    }

}