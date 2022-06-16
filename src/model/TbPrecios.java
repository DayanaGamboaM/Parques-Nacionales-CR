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
@Table(name = "TB_PRECIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbPrecios.findAll", query = "SELECT t FROM TbPrecios t"),
    @NamedQuery(name = "TbPrecios.findByPreId", query = "SELECT t FROM TbPrecios t WHERE t.preId = :preId"),
    @NamedQuery(name = "TbPrecios.findByPreModalidad", query = "SELECT t FROM TbPrecios t WHERE t.preModalidad = :preModalidad"),
    @NamedQuery(name = "TbPrecios.findByPrePrecio", query = "SELECT t FROM TbPrecios t WHERE t.prePrecio = :prePrecio")})
public class TbPrecios implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TB_PRECIOS_SEQ", sequenceName = "TB_ESPECIES_SEQ05", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_PRECIOS_SEQ")
    @Basic(optional = false)
    @Column(name = "PRE_ID")
    private Long preId;
    @Column(name = "PRE_MODALIDAD")
    private String preModalidad;
    @Column(name = "PRE_PRECIO")
    private Long prePrecio;
    @OneToMany(mappedBy = "preId")
    private Collection<TbEntradas> tbEntradasCollection;

    public TbPrecios() {
    }
    
    public TbPrecios(TbPreciosDto tbpreciosDto) {
        this.preId = tbpreciosDto.getPreId();
        actualizarPrecio(tbpreciosDto);
    }

    public void actualizarPrecio(TbPreciosDto tbpreciosDto) {
        this.preModalidad = tbpreciosDto.getPreModalidad();
        this.prePrecio = tbpreciosDto.getPrePrecio();

    }

//    public TbPrecios(Long preId) {
//        this.preId = preId;
//    }

    public Long getPreId() {
        return preId;
    }

    public void setPreId(Long preId) {
        this.preId = preId;
    }

    public String getPreModalidad() {
        return preModalidad;
    }

    public void setPreModalidad(String preModalidad) {
        this.preModalidad = preModalidad;
    }

    public Long getPrePrecio() {
        return prePrecio;
    }

    public void setPrePrecio(Long prePrecio) {
        this.prePrecio = prePrecio;
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
        hash += (preId != null ? preId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbPrecios)) {
            return false;
        }
        TbPrecios other = (TbPrecios) object;
        if ((this.preId == null && other.preId != null) || (this.preId != null && !this.preId.equals(other.preId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbPrecios[ preId=" + preId + " ]";
    }
    
}
