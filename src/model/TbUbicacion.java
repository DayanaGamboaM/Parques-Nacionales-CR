/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author monge
 */
@Entity
@Table(name = "TB_UBICACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbUbicacion.findAll", query = "SELECT t FROM TbUbicacion t"),
    @NamedQuery(name = "TbUbicacion.findByUbiId", query = "SELECT t FROM TbUbicacion t WHERE t.ubiId = :ubiId"),
    @NamedQuery(name = "TbUbicacion.findByUbiProvincia", query = "SELECT t FROM TbUbicacion t WHERE t.ubiProvincia = :ubiProvincia"),
    @NamedQuery(name = "TbUbicacion.findByUbiCanton", query = "SELECT t FROM TbUbicacion t WHERE t.ubiCanton = :ubiCanton"),
    @NamedQuery(name = "TbUbicacion.findByUbiDistrito", query = "SELECT t FROM TbUbicacion t WHERE t.ubiDistrito = :ubiDistrito")})
public class TbUbicacion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TB_UBICACION_SEQ", sequenceName = "TB_UBICACION_SEQ08", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_UBICACION_SEQ")
    @Basic(optional = false)
    @Column(name = "UBI_ID")
    private Long ubiId;
    @Column(name = "UBI_PROVINCIA")
    private String ubiProvincia;
    @Column(name = "UBI_CANTON")
    private String ubiCanton;
    @Column(name = "UBI_DISTRITO")
    private String ubiDistrito;
    @OneToMany(mappedBy = "ubiId")
    private Collection<TbParques> tbParquesCollection;
    @OneToMany(mappedBy = "ubiId")
    private Collection<TbRefugios> tbRefugiosCollection;

    public TbUbicacion() {
    }
    
    public TbUbicacion(TbUbicacionDto tbubicacionDto) {
        this.ubiId = tbubicacionDto.getUbiId();
        actualizarUbicacion(tbubicacionDto);
    }

    public void actualizarUbicacion(TbUbicacionDto tbubicacionDto) {
        this.ubiProvincia = tbubicacionDto.getUbiProvincia();
        this.ubiCanton = tbubicacionDto.getUbiCanton();
        this.ubiDistrito = tbubicacionDto.getUbiDistrito();

    }

//    public TbUbicacion(Long ubiId) {
//        this.ubiId = ubiId;
//    }

    public Long getUbiId() {
        return ubiId;
    }

    public void setUbiId(Long ubiId) {
        this.ubiId = ubiId;
    }

    public String getUbiProvincia() {
        return ubiProvincia;
    }

    public void setUbiProvincia(String ubiProvincia) {
        this.ubiProvincia = ubiProvincia;
    }

    public String getUbiCanton() {
        return ubiCanton;
    }

    public void setUbiCanton(String ubiCanton) {
        this.ubiCanton = ubiCanton;
    }

    public String getUbiDistrito() {
        return ubiDistrito;
    }

    public void setUbiDistrito(String ubiDistrito) {
        this.ubiDistrito = ubiDistrito;
    }

    @XmlTransient
    public Collection<TbParques> getTbParquesCollection() {
        return tbParquesCollection;
    }

    public void setTbParquesCollection(Collection<TbParques> tbParquesCollection) {
        this.tbParquesCollection = tbParquesCollection;
    }

    @XmlTransient
    public Collection<TbRefugios> getTbRefugiosCollection() {
        return tbRefugiosCollection;
    }

    public void setTbRefugiosCollection(Collection<TbRefugios> tbRefugiosCollection) {
        this.tbRefugiosCollection = tbRefugiosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ubiId != null ? ubiId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbUbicacion)) {
            return false;
        }
        TbUbicacion other = (TbUbicacion) object;
        if ((this.ubiId == null && other.ubiId != null) || (this.ubiId != null && !this.ubiId.equals(other.ubiId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbUbicacion[ ubiId=" + ubiId + " ]";
    }
    
}
