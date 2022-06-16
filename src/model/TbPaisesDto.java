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
@XmlRootElement(name = "TbPaisesDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class TbPaisesDto {

    @XmlTransient
    public SimpleStringProperty paiId;
    @XmlTransient
    public SimpleStringProperty paiNombre;
    @XmlTransient
    public SimpleStringProperty paiContinente;
    @XmlTransient
    private Boolean modificado;

    public TbPaisesDto() {
        this.modificado = false;
        this.paiId = new SimpleStringProperty();
        this.paiNombre = new SimpleStringProperty();
        this.paiContinente = new SimpleStringProperty();
    }
    
    public TbPaisesDto(TbPaises tbpaises) {
        this();
        this.paiId.set(tbpaises.getPaiId().toString());
        this.paiNombre.set(tbpaises.getPaiNombre());
        this.paiContinente.set(tbpaises.getPaiContinente());
        
    }

    public Long getPaiId() {
        if(paiId.get()!=null && !paiId.get().isEmpty())
            return Long.valueOf(paiId.get());
        else
            return null;
    }

    public void setPaiId(Long paiId) {
        this.paiId.set(paiId.toString());
    }
    
    public String getPaiNombre() {
        return paiNombre.get();
    }

    public void setPaiNombre(String paiNombre) {
        this.paiNombre.set(paiNombre);
    }
    
    public String getPaiContinente() {
        return paiContinente.get();
    }

    public void setPaiContinente(String paiContinente) {
        this.paiContinente.set(paiContinente);
    }
    
    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    @Override
    public String toString() {
        return "TbPaisesDto{" + "paiId=" + paiId + ", paiNombre=" + paiNombre + ", paiContinente=" + paiContinente +'}';
    }
}