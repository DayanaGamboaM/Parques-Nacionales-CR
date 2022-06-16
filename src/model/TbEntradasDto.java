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
@XmlRootElement(name = "TbEntradasDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class TbEntradasDto {

    @XmlTransient
    public SimpleStringProperty enId;
    @XmlTransient
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public ObjectProperty<LocalDate> enFechavisita;
    @XmlTransient
    public SimpleStringProperty enSubtotal;
    @XmlTransient
    public SimpleStringProperty enIva;
    @XmlTransient
    public SimpleStringProperty enTotal;
    @XmlTransient
    private Boolean modificado;

    public TbEntradasDto() {
        this.modificado = false;
        this.enId = new SimpleStringProperty();
        this.enFechavisita = new SimpleObjectProperty();
        this.enSubtotal = new SimpleStringProperty();
        this.enIva = new SimpleStringProperty();
        this.enTotal = new SimpleStringProperty();
        

    }
    
    public TbEntradasDto(TbEntradas tbentradas) {
        this();
        this.enId.set(tbentradas.getEnId().toString());
        this.enFechavisita.set(tbentradas.getEnFechavisita().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        this.enSubtotal.set(tbentradas.getEnSubtotal().toString());
        this.enIva.set(tbentradas.getEnIva().toString());
        this.enTotal.set(tbentradas.getEnTotal().toString());
        
    }

    public Long getEnId() {
        if(enId.get()!=null && !enId.get().isEmpty())
            return Long.valueOf(enId.get());
        else
            return null;
    }

    public void setEnId(Long enId) {
        this.enId.set(enId.toString());
    }
    
    public LocalDate getEnFechavisita() {
        return enFechavisita.get();
    }

    public void setEnFechavisita(LocalDate enFechavisita) {
        this.enFechavisita.set(enFechavisita);
    }

    public Long getEnSubtotal() {
        if(enSubtotal.get()!=null && !enSubtotal.get().isEmpty())
            return Long.valueOf(enSubtotal.get());
        else
            return null;
    }

    public void setEnSubtotal(Long enSubtotal) {
        this.enSubtotal.set(enSubtotal.toString());
    }
    
    public Long getEnIva() {
        if(enIva.get()!=null && !enIva.get().isEmpty())
            return Long.valueOf(enIva.get());
        else
            return null;
    }

    public void setEnIva(Long enIva) {
        this.enIva.set(enIva.toString());
    }
    
    public Long getEnTotal() {
        if(enTotal.get()!=null && !enTotal.get().isEmpty())
            return Long.valueOf(enTotal.get());
        else
            return null;
    }

    public void setEnTotal(Long enTotal) {
        this.enTotal.set(enTotal.toString());
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    @Override
    public String toString() {
        return "TbEntradasDto{" + "enId=" + enId + ", enFechavisita=" + enFechavisita + ", enSubtotal=" + enSubtotal +", enIva=" + enIva +", enTotal=" + enTotal + '}';
    }

}
