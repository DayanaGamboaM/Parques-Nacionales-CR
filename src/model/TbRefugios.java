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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "TB_REFUGIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbRefugios.findAll", query = "SELECT t FROM TbRefugios t"),
    @NamedQuery(name = "TbRefugios.findByRefId", query = "SELECT t FROM TbRefugios t WHERE t.refId = :refId"),
    @NamedQuery(name = "TbRefugios.findByRefNombre", query = "SELECT t FROM TbRefugios t WHERE t.refNombre = :refNombre"),
    @NamedQuery(name = "TbRefugios.findByRefDescripcion", query = "SELECT t FROM TbRefugios t WHERE t.refDescripcion = :refDescripcion"),
    @NamedQuery(name = "TbRefugios.findByRefReconocimientos", query = "SELECT t FROM TbRefugios t WHERE t.refReconocimientos = :refReconocimientos"),
    @NamedQuery(name = "TbRefugios.findByRefDireccionexac", query = "SELECT t FROM TbRefugios t WHERE t.refDireccionexac = :refDireccionexac"),
    @NamedQuery(name = "TbRefugios.findByRefAreareservada", query = "SELECT t FROM TbRefugios t WHERE t.refAreareservada = :refAreareservada"),
    @NamedQuery(name = "TbRefugios.findByRefCoordgeo", query = "SELECT t FROM TbRefugios t WHERE t.refCoordgeo = :refCoordgeo")})
public class TbRefugios implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TB_REFUGIOS_SEQ", sequenceName = "TB_REFUGIOS_SEQ06", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_REFUGIOS_SEQ")
    @Basic(optional = false)
    @Column(name = "REF_ID")
    private Long refId;
    @Column(name = "REF_NOMBRE")
    private String refNombre;
    @Column(name = "REF_DESCRIPCION")
    private String refDescripcion;
    @Column(name = "REF_RECONOCIMIENTOS")
    private String refReconocimientos;
    @Column(name = "REF_DIRECCIONEXAC")
    private String refDireccionexac;
    @Column(name = "REF_AREARESERVADA")
    private String refAreareservada;
    @Column(name = "REF_COORDGEO")
    private String refCoordgeo;
    @OneToMany(mappedBy = "refId")
    private Collection<TbEntradas> tbEntradasCollection;
    @JoinColumn(name = "UBI_ID", referencedColumnName = "UBI_ID")
    @ManyToOne
    private TbUbicacion ubiId;
    @OneToMany(mappedBy = "refId")
    private Collection<TbValoraciones> tbValoracionesCollection;
    @OneToMany(mappedBy = "refId")
    private Collection<TbEspecies> tbEspeciesCollection;

    public TbRefugios() {
    }
    
    public TbRefugios(TbRefugiosDto tbrefugiosDto) {
        this.refId = tbrefugiosDto.getRefId();
        actualizarRefugio(tbrefugiosDto);
    }

    public void actualizarRefugio(TbRefugiosDto tbrefugiosDto) {
        this.refNombre = tbrefugiosDto.getRefNombre();
        this.refDescripcion = tbrefugiosDto.getRefDescripcion();
        this.refReconocimientos = tbrefugiosDto.getRefReconocimientos();
        this.refDireccionexac = tbrefugiosDto.getRefDireccionexac();
        this.refAreareservada = tbrefugiosDto.getRefAreareservada();
        this.refCoordgeo = tbrefugiosDto.getRefCoordgeo();

    }

//    public TbRefugios(Long refId) {
//        this.refId = refId;
//    }

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public String getRefNombre() {
        return refNombre;
    }

    public void setRefNombre(String refNombre) {
        this.refNombre = refNombre;
    }

    public String getRefDescripcion() {
        return refDescripcion;
    }

    public void setRefDescripcion(String refDescripcion) {
        this.refDescripcion = refDescripcion;
    }

    public String getRefReconocimientos() {
        return refReconocimientos;
    }

    public void setRefReconocimientos(String refReconocimientos) {
        this.refReconocimientos = refReconocimientos;
    }

    public String getRefDireccionexac() {
        return refDireccionexac;
    }

    public void setRefDireccionexac(String refDireccionexac) {
        this.refDireccionexac = refDireccionexac;
    }

    public String getRefAreareservada() {
        return refAreareservada;
    }

    public void setRefAreareservada(String refAreareservada) {
        this.refAreareservada = refAreareservada;
    }

    public String getRefCoordgeo() {
        return refCoordgeo;
    }

    public void setRefCoordgeo(String refCoordgeo) {
        this.refCoordgeo = refCoordgeo;
    }

    @XmlTransient
    public Collection<TbEntradas> getTbEntradasCollection() {
        return tbEntradasCollection;
    }

    public void setTbEntradasCollection(Collection<TbEntradas> tbEntradasCollection) {
        this.tbEntradasCollection = tbEntradasCollection;
    }

    public TbUbicacion getUbiId() {
        return ubiId;
    }

    public void setUbiId(TbUbicacion ubiId) {
        this.ubiId = ubiId;
    }

    @XmlTransient
    public Collection<TbValoraciones> getTbValoracionesCollection() {
        return tbValoracionesCollection;
    }

    public void setTbValoracionesCollection(Collection<TbValoraciones> tbValoracionesCollection) {
        this.tbValoracionesCollection = tbValoracionesCollection;
    }

    @XmlTransient
    public Collection<TbEspecies> getTbEspeciesCollection() {
        return tbEspeciesCollection;
    }

    public void setTbEspeciesCollection(Collection<TbEspecies> tbEspeciesCollection) {
        this.tbEspeciesCollection = tbEspeciesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (refId != null ? refId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbRefugios)) {
            return false;
        }
        TbRefugios other = (TbRefugios) object;
        if ((this.refId == null && other.refId != null) || (this.refId != null && !this.refId.equals(other.refId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbRefugios[ refId=" + refId + " ]";
    }
    
}
