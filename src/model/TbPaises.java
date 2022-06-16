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
@Table(name = "TB_PAISES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbPaises.findAll", query = "SELECT t FROM TbPaises t"),
    @NamedQuery(name = "TbPaises.findByPaiId", query = "SELECT t FROM TbPaises t WHERE t.paiId = :paiId"),
    @NamedQuery(name = "TbPaises.findByPaiNombre", query = "SELECT t FROM TbPaises t WHERE t.paiNombre = :paiNombre"),
    @NamedQuery(name = "TbPaises.findByPaiContinente", query = "SELECT t FROM TbPaises t WHERE t.paiContinente = :paiContinente")})
public class TbPaises implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TB_PAISES_SEQ", sequenceName = "TB_PAISES_SEQ03", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_PAISES_SEQ")
    @Basic(optional = false)
    @Column(name = "PAI_ID")
    private Long paiId;
    @Column(name = "PAI_NOMBRE")
    private String paiNombre;
    @Column(name = "PAI_CONTINENTE")
    private String paiContinente;
    @OneToMany(mappedBy = "paiId")
    private Collection<TbVisitantes> tbVisitantesCollection;

    public TbPaises() {
    }
    
    public TbPaises(TbPaisesDto tbpaisesDto) {
        this.paiId = tbpaisesDto.getPaiId();
        actualizarPais(tbpaisesDto);
    }

    public void actualizarPais(TbPaisesDto tbpaisesDto) {
        this.paiNombre = tbpaisesDto.getPaiNombre();
        this.paiContinente = tbpaisesDto.getPaiContinente();
    }

//    public TbPaises(Long paiId) {
//        this.paiId = paiId;
//    }

    public Long getPaiId() {
        return paiId;
    }

    public void setPaiId(Long paiId) {
        this.paiId = paiId;
    }

    public String getPaiNombre() {
        return paiNombre;
    }

    public void setPaiNombre(String paiNombre) {
        this.paiNombre = paiNombre;
    }

    public String getPaiContinente() {
        return paiContinente;
    }

    public void setPaiContinente(String paiContinente) {
        this.paiContinente = paiContinente;
    }

    @XmlTransient
    public Collection<TbVisitantes> getTbVisitantesCollection() {
        return tbVisitantesCollection;
    }

    public void setTbVisitantesCollection(Collection<TbVisitantes> tbVisitantesCollection) {
        this.tbVisitantesCollection = tbVisitantesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paiId != null ? paiId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbPaises)) {
            return false;
        }
        TbPaises other = (TbPaises) object;
        if ((this.paiId == null && other.paiId != null) || (this.paiId != null && !this.paiId.equals(other.paiId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbPaises[ paiId=" + paiId + " ]";
    }
    
}
