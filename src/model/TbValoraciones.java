/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author monge
 */
@Entity
@Table(name = "TB_VALORACIONES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbValoraciones.findAll", query = "SELECT t FROM TbValoraciones t"),
    @NamedQuery(name = "TbValoraciones.findByValId", query = "SELECT t FROM TbValoraciones t WHERE t.valId = :valId"),
    @NamedQuery(name = "TbValoraciones.findByValPuntaje", query = "SELECT t FROM TbValoraciones t WHERE t.valPuntaje = :valPuntaje"),
    @NamedQuery(name = "TbValoraciones.findByValComentario", query = "SELECT t FROM TbValoraciones t WHERE t.valComentario = :valComentario")})
public class TbValoraciones implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TB_VALORACIONES_SEQ", sequenceName = "TB_VALORACIONES_SEQ09", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_VALORACIONES_SEQ")
    @Basic(optional = false)
    @Column(name = "VAL_ID")
    private Long valId;
    @Column(name = "VAL_PUNTAJE")
    private Long valPuntaje;
    @Column(name = "VAL_COMENTARIO")
    private String valComentario;
    @JoinColumn(name = "PAR_ID", referencedColumnName = "PAR_ID")
    @ManyToOne
    private TbParques parId;
    @JoinColumn(name = "REF_ID", referencedColumnName = "REF_ID")
    @ManyToOne
    private TbRefugios refId;

    public TbValoraciones() {
    }
    
    public TbValoraciones(TbValoracionesDto tbvaloracionesDto) {
        this.valId = tbvaloracionesDto.getValId();
        actualizarValoracion(tbvaloracionesDto);
    }

    public void actualizarValoracion(TbValoracionesDto tbvaloracionesDto) {
        this.valPuntaje = tbvaloracionesDto.getValPuntaje();
        this.valComentario = tbvaloracionesDto.getValComentario();

    }

//    public TbValoraciones(Long valId) {
//        this.valId = valId;
//    }

    public Long getValId() {
        return valId;
    }

    public void setValId(Long valId) {
        this.valId = valId;
    }

    public Long getValPuntaje() {
        return valPuntaje;
    }

    public void setValPuntaje(Long valPuntaje) {
        this.valPuntaje = valPuntaje;
    }

    public String getValComentario() {
        return valComentario;
    }

    public void setValComentario(String valComentario) {
        this.valComentario = valComentario;
    }

    public TbParques getParId() {
        return parId;
    }

    public void setParId(TbParques parId) {
        this.parId = parId;
    }

    public TbRefugios getRefId() {
        return refId;
    }

    public void setRefId(TbRefugios refId) {
        this.refId = refId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (valId != null ? valId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbValoraciones)) {
            return false;
        }
        TbValoraciones other = (TbValoraciones) object;
        if ((this.valId == null && other.valId != null) || (this.valId != null && !this.valId.equals(other.valId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbValoraciones[ valId=" + valId + " ]";
    }
    
}
