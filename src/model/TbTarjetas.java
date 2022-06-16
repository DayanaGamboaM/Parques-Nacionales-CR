/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "TB_TARJETAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbTarjetas.findAll", query = "SELECT t FROM TbTarjetas t"),
    @NamedQuery(name = "TbTarjetas.findByTarId", query = "SELECT t FROM TbTarjetas t WHERE t.tarId = :tarId"),
    @NamedQuery(name = "TbTarjetas.findByTarNombre", query = "SELECT t FROM TbTarjetas t WHERE t.tarNombre = :tarNombre"),
    @NamedQuery(name = "TbTarjetas.findByTarComision", query = "SELECT t FROM TbTarjetas t WHERE t.tarComision = :tarComision")})
public class TbTarjetas implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TB_TARJETAS_SEQ", sequenceName = "TB_TARJETAS_SEQ07", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_TARJETAS_SEQ")
    @Basic(optional = false)
    @Column(name = "TAR_ID")
    private Long tarId;
    @Column(name = "TAR_NOMBRE")
    private String tarNombre;
    @Column(name = "TAR_COMISION")
    private Long tarComision;
    @OneToMany(mappedBy = "tarId")
    private Collection<TbEntradas> tbEntradasCollection;

    public TbTarjetas() {
    }
    
    public TbTarjetas(TbTarjetasDto tbtarjetasDto) {
        this.tarId = tbtarjetasDto.getTarId();
        actualizarTarjeta(tbtarjetasDto);
    }

    public void actualizarTarjeta(TbTarjetasDto tbtarjetasDto) {
        this.tarNombre = tbtarjetasDto.getTarNombre();
        this.tarComision = tbtarjetasDto.getTarComision();

    }

//    public TbTarjetas(Long tarId) {
//        this.tarId = tarId;
//    }

    public Long getTarId() {
        return tarId;
    }

    public void setTarId(Long tarId) {
        this.tarId = tarId;
    }

    public String getTarNombre() {
        return tarNombre;
    }

    public void setTarNombre(String tarNombre) {
        this.tarNombre = tarNombre;
    }

    public Long getTarComision() {
        return tarComision;
    }

    public void setTarComision(Long tarComision) {
        this.tarComision = tarComision;
    }

    @XmlTransient
    public Collection<TbEntradas> getTbEntradasCollection() {
        return tbEntradasCollection;
    }

    public void setTbEntradasCollection(Collection<TbEntradas> tbEntradasCollection) {
        this.tbEntradasCollection = tbEntradasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tarId != null ? tarId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbTarjetas)) {
            return false;
        }
        TbTarjetas other = (TbTarjetas) object;
        if ((this.tarId == null && other.tarId != null) || (this.tarId != null && !this.tarId.equals(other.tarId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbTarjetas[ tarId=" + tarId + " ]";
    }
    
}
