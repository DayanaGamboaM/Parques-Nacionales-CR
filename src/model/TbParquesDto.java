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
@XmlRootElement(name = "TbParquesDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class TbParquesDto {

    @XmlTransient
    public SimpleStringProperty parId;
    @XmlTransient
    public SimpleStringProperty parNombre;
    @XmlTransient
    public SimpleStringProperty parDescripcion;
    @XmlTransient
    public SimpleStringProperty parReconocimientos;
    @XmlTransient
    public SimpleStringProperty parDireccionexacta;
    @XmlTransient
    public SimpleStringProperty parAreareservada;
    @XmlTransient
    public SimpleStringProperty parCoordgeo;
    @XmlTransient
    private Boolean modificado;

    public TbParquesDto() {
        this.modificado = false;
        this.parId = new SimpleStringProperty();
        this.parNombre = new SimpleStringProperty();
        this.parDescripcion = new SimpleStringProperty();
        this.parReconocimientos = new SimpleStringProperty();
        this.parDireccionexacta = new SimpleStringProperty();
        this.parAreareservada = new SimpleStringProperty();
        this.parCoordgeo = new SimpleStringProperty();
        

    }
    
    public TbParquesDto(TbParques tbparques) {
        this();
        this.parId.set(tbparques.getParId().toString());
        this.parNombre.set(tbparques.getParNombre());
        this.parDescripcion.set(tbparques.getParDescripcion());
        this.parReconocimientos.set(tbparques.getParReconocimientos());
        this.parDireccionexacta.set(tbparques.getParDireccionexacta());
        this.parAreareservada.set(tbparques.getParAreareservada());
        this.parCoordgeo.set(tbparques.getParCoordgeo());
        
    }

    public Long getParId() {
        if(parId.get()!=null && !parId.get().isEmpty())
            return Long.valueOf(parId.get());
        else
            return null;
    }

    public void setParId(Long parId) {
        this.parId.set(parId.toString());
    }
    
    public String getParNombre() {
        return parNombre.get();
    }

    public void setParNombre(String parNombre) {
        this.parNombre.set(parNombre);
    }
    
    public String getParDescripcion() {
        return parDescripcion.get();
    }

    public void setParDescripcion(String parDescripcion) {
        this.parDescripcion.set(parDescripcion);
    }
    
    public String getParReconocimientos() {
        return parReconocimientos.get();
    }

    public void setParReconocimientos(String parReconocimientos) {
        this.parReconocimientos.set(parReconocimientos);
    }
    
    
    public String getParDireccionexacta() {
        return parDireccionexacta.get();
    }

    public void setParDireccionexacta(String parDireccionexacta) {
        this.parDireccionexacta.set(parDireccionexacta);
    }
    
    public String getParAreareservada() {
        return parAreareservada.get();
    }

    public void setParAreareservada(String parAreareservada) {
        this.parAreareservada.set(parAreareservada);
    }
    
    public String getParCoordgeo() {
        return parCoordgeo.get();
    }

    public void setParCoordgeo(String parCoordgeo) {
        this.parCoordgeo.set(parCoordgeo);
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    @Override
    public String toString() {
        return "TbEspeciesDto{" + "parId=" + parId + ", parNombre=" + parNombre + ", espNomcientifico=" + parDescripcion +", parReconocimientos=" + parReconocimientos +", parDireccionexacta=" + parDireccionexacta +", parAreareservada=" + parAreareservada +", parCoordgeo=" + parCoordgeo + '}';
    }

}