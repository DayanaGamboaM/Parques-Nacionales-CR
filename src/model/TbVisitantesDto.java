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
@XmlRootElement(name = "TbVisitantesDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class TbVisitantesDto {

    @XmlTransient
    public SimpleStringProperty visId;
    @XmlTransient
    public SimpleStringProperty visCedPasap;
    @XmlTransient
    public SimpleStringProperty visNombre;
    @XmlTransient
    public SimpleStringProperty visApellidos;
    @XmlTransient
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public ObjectProperty<LocalDate> visFechanacimiento;
    @XmlTransient
    public SimpleStringProperty visCorreoelectronico;
    @XmlTransient
    public SimpleStringProperty visRazonvisita;
    @XmlTransient
    private Boolean modificado;

    public TbVisitantesDto() {
        this.modificado = false;
        this.visId = new SimpleStringProperty();
        this.visCedPasap = new SimpleStringProperty();
        this.visNombre = new SimpleStringProperty();
        this.visApellidos = new SimpleStringProperty();
        this.visFechanacimiento = new SimpleObjectProperty();
        this.visCorreoelectronico = new SimpleStringProperty();
        this.visRazonvisita = new SimpleStringProperty();
    }
    
    public TbVisitantesDto(TbVisitantes tbvisitantes) {
        this();
        this.visId.set(tbvisitantes.getVisId().toString());
        this.visCedPasap.set(tbvisitantes.getVisCedPasap().toString());
        this.visNombre.set(tbvisitantes.getVisNombre());
        this.visApellidos.set(tbvisitantes.getVisApellidos());
        this.visFechanacimiento.set(tbvisitantes.getVisFechanacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        this.visCorreoelectronico.set(tbvisitantes.getVisCorreoelectronico());
        this.visRazonvisita.set(tbvisitantes.getVisRazonvisita());
    }

    public Long getVisId() {
        if(visId.get()!=null && !visId.get().isEmpty())
            return Long.valueOf(visId.get());
        else
            return null;
    }

    public void setVisId(Long visId) {
        this.visId.set(visId.toString());
    }
    
    public Long getVisCedPasap() {
        if(visCedPasap.get()!=null && !visCedPasap.get().isEmpty())
            return Long.valueOf(visCedPasap.get());
        else
            return null;
    }

    public void setVisCedPasap(Long visCedPasap) {
        this.visCedPasap.set(visCedPasap.toString());
    }
    
    public String getVisNombre() {
        return visNombre.get();
    }

    public void setVisNombre(String visNombre) {
        this.visNombre.set(visNombre);
    }
    
    public String getVisApellidos() {
        return visApellidos.get();
    }

    public void setVisApellidos(String visApellidos) {
        this.visApellidos.set(visApellidos);
    }
    
    public LocalDate getVisFechanacimiento() {
        return visFechanacimiento.get();
    }

    public void setVisFechanacimiento(LocalDate visFechanacimiento) {
        this.visFechanacimiento.set(visFechanacimiento);
    }

    public String getVisCorreoelectronico() {
        return visCorreoelectronico.get();
    }

    public void setVisCorreoelectronico(String visCorreoelectronico) {
        this.visCorreoelectronico.set(visCorreoelectronico);
    }
    
    public String getVisRazonvisita() {
        return visRazonvisita.get();
    }

    public void setVisRazonvisita(String visRazonvisita) {
        this.visRazonvisita.set(visRazonvisita);
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    @Override
    public String toString() {
        return "TbEntradasDto{" + "visId=" + visId + ", visCedPasap=" + visCedPasap + ", visNombre=" + visNombre +", visApellidos=" + visApellidos +", visFechanacimiento=" + visFechanacimiento +", visCorreoelectronico=" + visCorreoelectronico +", visRazonvisita=" + visRazonvisita +'}';
    }

}
