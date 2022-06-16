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
@XmlRootElement(name = "TbValoracionesDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class TbValoracionesDto {

    @XmlTransient
    public SimpleStringProperty valId;
    @XmlTransient
    public SimpleStringProperty valPuntaje;
    @XmlTransient
    public SimpleStringProperty valComentario;
    @XmlTransient
    private Boolean modificado;

    public TbValoracionesDto() {
        this.modificado = false;
        this.valId = new SimpleStringProperty();
        this.valPuntaje = new SimpleStringProperty();
        this.valComentario = new SimpleStringProperty();
    }
    
    public TbValoracionesDto(TbValoraciones tbvaloraciones) {
        this();
        this.valId.set(tbvaloraciones.getValId().toString());
        this.valPuntaje.set(tbvaloraciones.getValPuntaje().toString());
        this.valComentario.set(tbvaloraciones.getValComentario());
        
    }

    public Long getValId() {
        if(valId.get()!=null && !valId.get().isEmpty())
            return Long.valueOf(valId.get());
        else
            return null;
    }

    public void setValId(Long valId) {
        this.valId.set(valId.toString());
    }
    
    
    public Long getValPuntaje() {
        if(valPuntaje.get()!=null && !valPuntaje.get().isEmpty())
            return Long.valueOf(valPuntaje.get());
        else
            return null;
    }

    public void setValPuntaje(Long valPuntaje) {
        this.valPuntaje.set(valPuntaje.toString());
    }
    
    public String getValComentario() {
        return valComentario.get();
    }

    public void setValComentario(String valComentario) {
        this.valComentario.set(valComentario);
    }
    
    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    @Override
    public String toString() {
        return "TbPaisesDto{" + "valId=" + valId + ", valPuntaje=" + valPuntaje + ", valComentario=" + valComentario +'}';
    }
}