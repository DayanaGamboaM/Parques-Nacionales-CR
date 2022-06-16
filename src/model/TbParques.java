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
@Table(name = "TB_PARQUES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbParques.findAll", query = "SELECT t FROM TbParques t"),
    @NamedQuery(name = "TbParques.findByParId", query = "SELECT t FROM TbParques t WHERE t.parId = :parId"),
    @NamedQuery(name = "TbParques.findByParNombre", query = "SELECT t FROM TbParques t WHERE t.parNombre = :parNombre"),
    @NamedQuery(name = "TbParques.findByParDescripcion", query = "SELECT t FROM TbParques t WHERE t.parDescripcion = :parDescripcion"),
    @NamedQuery(name = "TbParques.findByParReconocimientos", query = "SELECT t FROM TbParques t WHERE t.parReconocimientos = :parReconocimientos"),
    @NamedQuery(name = "TbParques.findByParDireccionexacta", query = "SELECT t FROM TbParques t WHERE t.parDireccionexacta = :parDireccionexacta"),
    @NamedQuery(name = "TbParques.findByParAreareservada", query = "SELECT t FROM TbParques t WHERE t.parAreareservada = :parAreareservada"),
    @NamedQuery(name = "TbParques.findByParCoordgeo", query = "SELECT t FROM TbParques t WHERE t.parCoordgeo = :parCoordgeo")})
public class TbParques implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TB_PARQUES_SEQ", sequenceName = "TB_PARQUES_SEQ04", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_PARQUES_SEQ")
    @Basic(optional = false)
    @Column(name = "PAR_ID")
    private Long parId;
    @Column(name = "PAR_NOMBRE")
    private String parNombre;
    @Column(name = "PAR_DESCRIPCION")
    private String parDescripcion;
    @Column(name = "PAR_RECONOCIMIENTOS")
    private String parReconocimientos;
    @Column(name = "PAR_DIRECCIONEXACTA")
    private String parDireccionexacta;
    @Column(name = "PAR_AREARESERVADA")
    private String parAreareservada;
    @Column(name = "PAR_COORDGEO")
    private String parCoordgeo;
    @OneToMany(mappedBy = "parId")
    private Collection<TbEntradas> tbEntradasCollection;
    @JoinColumn(name = "UBI_ID", referencedColumnName = "UBI_ID")
    @ManyToOne
    private TbUbicacion ubiId;
    @OneToMany(mappedBy = "parId")
    private Collection<TbValoraciones> tbValoracionesCollection;
    @OneToMany(mappedBy = "parId")
    private Collection<TbEspecies> tbEspeciesCollection;

    public TbParques() {
    }
    
    public TbParques(TbParquesDto tbparquesDto) {
        this.parId = tbparquesDto.getParId();
        actualizarParque(tbparquesDto);
    }

    public void actualizarParque(TbParquesDto tbparquesDto) {
        this.parNombre = tbparquesDto.getParNombre();
        this.parDescripcion = tbparquesDto.getParDescripcion();
        this.parReconocimientos = tbparquesDto.getParReconocimientos();
        this.parDireccionexacta = tbparquesDto.getParDireccionexacta();
        this.parAreareservada = tbparquesDto.getParAreareservada();
        this.parCoordgeo = tbparquesDto.getParCoordgeo();
        

    }

//    public TbParques(Long parId) {
//        this.parId = parId;
//    }

    public Long getParId() {
        return parId;
    }

    public void setParId(Long parId) {
        this.parId = parId;
    }

    public String getParNombre() {
        return parNombre;
    }

    public void setParNombre(String parNombre) {
        this.parNombre = parNombre;
    }

    public String getParDescripcion() {
        return parDescripcion;
    }

    public void setParDescripcion(String parDescripcion) {
        this.parDescripcion = parDescripcion;
    }

    public String getParReconocimientos() {
        return parReconocimientos;
    }

    public void setParReconocimientos(String parReconocimientos) {
        this.parReconocimientos = parReconocimientos;
    }

    public String getParDireccionexacta() {
        return parDireccionexacta;
    }

    public void setParDireccionexacta(String parDireccionexacta) {
        this.parDireccionexacta = parDireccionexacta;
    }

    public String getParAreareservada() {
        return parAreareservada;
    }

    public void setParAreareservada(String parAreareservada) {
        this.parAreareservada = parAreareservada;
    }

    public String getParCoordgeo() {
        return parCoordgeo;
    }

    public void setParCoordgeo(String parCoordgeo) {
        this.parCoordgeo = parCoordgeo;
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
        hash += (parId != null ? parId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbParques)) {
            return false;
        }
        TbParques other = (TbParques) object;
        if ((this.parId == null && other.parId != null) || (this.parId != null && !this.parId.equals(other.parId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbParques[ parId=" + parId + " ]";
    }
    
}
