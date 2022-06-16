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
@XmlRootElement(name = "TbEspeciesDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class TbEspeciesDto {

    @XmlTransient
    public SimpleStringProperty espId;
    @XmlTransient
    public SimpleStringProperty espTipo;
    @XmlTransient
    public SimpleStringProperty espNomcientifico;
    @XmlTransient
    public SimpleStringProperty espNomcomun;
    @XmlTransient
    public SimpleStringProperty espPeso;
    @XmlTransient
    public SimpleStringProperty espTamaño;
    @XmlTransient
    public SimpleStringProperty espCaracprinc;
    @XmlTransient
    private Boolean modificado;

    public TbEspeciesDto() {
        this.modificado = false;
        this.espId = new SimpleStringProperty();
        this.espTipo = new SimpleStringProperty();
        this.espNomcientifico = new SimpleStringProperty();
        this.espNomcomun = new SimpleStringProperty();
        this.espPeso = new SimpleStringProperty();
        this.espTamaño = new SimpleStringProperty();
        this.espCaracprinc = new SimpleStringProperty();
        

    }
    
    public TbEspeciesDto(TbEspecies tbespecies) {
        this();
        this.espId.set(tbespecies.getEspId().toString());
        this.espTipo.set(tbespecies.getEspTipo());
        this.espNomcientifico.set(tbespecies.getEspNomcientifico());
        this.espNomcomun.set(tbespecies.getEspNomcomun());
        this.espPeso.set(tbespecies.getEspPeso().toString());
        this.espTamaño.set(tbespecies.getEspTamaño());
        this.espCaracprinc.set(tbespecies.getEspCaracprinc());
        
    }

    public Long getEspId() {
        if(espId.get()!=null && !espId.get().isEmpty())
            return Long.valueOf(espId.get());
        else
            return null;
    }

    public void setEspId(Long espId) {
        this.espId.set(espId.toString());
    }
    
    public String getEspTipo() {
        return espTipo.get();
    }

    public void setEspTipo(String espTipo) {
        this.espTipo.set(espTipo);
    }
    
    public String getEspNomcientifico() {
        return espNomcientifico.get();
    }

    public void setEspNomcientifico(String espNomcientifico) {
        this.espNomcientifico.set(espNomcientifico);
    }
    
    public String getEspNomcomun() {
        return espNomcomun.get();
    }

    public void setEspNomcomun(String espNomcomun) {
        this.espNomcomun.set(espNomcomun);
    }
    
    public Long getEspPeso() {
        if(espPeso.get()!=null && !espPeso.get().isEmpty())
            return Long.valueOf(espPeso.get());
        else
            return null;
    }

    public void setEspPeso(Long espPeso) {
        this.espPeso.set(espPeso.toString());
    }
    
    public String getEspTamaño() {
        return espTamaño.get();
    }

    public void setEspTamaño(String espTamaño) {
        this.espTamaño.set(espTamaño);
    }
    
    public String getEspCaracprinc() {
        return espCaracprinc.get();
    }

    public void setEspCaracprinc(String espCaracprinc) {
        this.espCaracprinc.set(espCaracprinc);
    }
    


    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    @Override
    public String toString() {
        return "TbEspeciesDto{" + "espId=" + espId + ", espTipo=" + espTipo + ", espNomcientifico=" + espNomcientifico +", espNomcomun=" + espNomcomun +", espPeso=" + espPeso +", espTamaño=" + espTamaño +", espCaracprinc=" + espCaracprinc + '}';
    }

}
