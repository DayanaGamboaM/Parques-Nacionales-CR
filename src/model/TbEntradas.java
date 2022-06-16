/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.ZoneId;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author monge
 */
@Entity
@Table(name = "TB_ENTRADAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbEntradas.findAll", query = "SELECT t FROM TbEntradas t"),
    @NamedQuery(name = "TbEntradas.findByEnId", query = "SELECT t FROM TbEntradas t WHERE t.enId = :enId"),
    @NamedQuery(name = "TbEntradas.findByEnFechavisita", query = "SELECT t FROM TbEntradas t WHERE t.enFechavisita = :enFechavisita"),
    @NamedQuery(name = "TbEntradas.findByEnSubtotal", query = "SELECT t FROM TbEntradas t WHERE t.enSubtotal = :enSubtotal"),
    @NamedQuery(name = "TbEntradas.findByEnIva", query = "SELECT t FROM TbEntradas t WHERE t.enIva = :enIva"),
    @NamedQuery(name = "TbEntradas.findByEnTotal", query = "SELECT t FROM TbEntradas t WHERE t.enTotal = :enTotal")})
public class TbEntradas implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TB_ENTRADAS_SEQ", sequenceName = "TB_ENTRADAS_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_ENTRADAS_SEQ")
    @Basic(optional = false)
    @Column(name = "EN_ID")
    private Long enId;
    @Column(name = "EN_FECHAVISITA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enFechavisita;
    @Column(name = "EN_SUBTOTAL")
    private Long enSubtotal;
    @Column(name = "EN_IVA")
    private Long enIva;
    @Column(name = "EN_TOTAL")
    private Long enTotal;
    @JoinColumn(name = "PAR_ID", referencedColumnName = "PAR_ID")
    @ManyToOne
    private TbParques parId;
    @JoinColumn(name = "PRE_ID", referencedColumnName = "PRE_ID")
    @ManyToOne
    private TbPrecios preId;
    @JoinColumn(name = "REF_ID", referencedColumnName = "REF_ID")
    @ManyToOne
    private TbRefugios refId;
    @JoinColumn(name = "TAR_ID", referencedColumnName = "TAR_ID")
    @ManyToOne
    private TbTarjetas tarId;
    @JoinColumn(name = "VIS_ID", referencedColumnName = "VIS_ID")
    @ManyToOne
    private TbVisitantes visId;

    public TbEntradas() {
    }
    
    public TbEntradas(TbEntradasDto tbentradasDto) {
        this.enId = tbentradasDto.getEnId();
        actualizarEntrada(tbentradasDto);
    }

    public void actualizarEntrada(TbEntradasDto tbentradasDto) {
        
        this.enFechavisita = Date.from(tbentradasDto.getEnFechavisita().atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.enSubtotal = tbentradasDto.getEnSubtotal();
        this.enIva = tbentradasDto.getEnIva();
        this.enTotal = tbentradasDto.getEnTotal();

    }

//    public TbEntradas(Long enId) {
//        this.enId = enId;
//    }

    public Long getEnId() {
        return enId;
    }

    public void setEnId(Long enId) {
        this.enId = enId;
    }

    public Date getEnFechavisita() {
        return enFechavisita;
    }

    public void setEnFechavisita(Date enFechavisita) {
        this.enFechavisita = enFechavisita;
    }

    public Long getEnSubtotal() {
        return enSubtotal;
    }

    public void setEnSubtotal(Long enSubtotal) {
        this.enSubtotal = enSubtotal;
    }

    public Long getEnIva() {
        return enIva;
    }

    public void setEnIva(Long enIva) {
        this.enIva = enIva;
    }

    public Long getEnTotal() {
        return enTotal;
    }

    public void setEnTotal(Long enTotal) {
        this.enTotal = enTotal;
    }

    public TbParques getParId() {
        return parId;
    }

    public void setParId(TbParques parId) {
        this.parId = parId;
    }

    public TbPrecios getPreId() {
        return preId;
    }

    public void setPreId(TbPrecios preId) {
        this.preId = preId;
    }

    public TbRefugios getRefId() {
        return refId;
    }

    public void setRefId(TbRefugios refId) {
        this.refId = refId;
    }

    public TbTarjetas getTarId() {
        return tarId;
    }

    public void setTarId(TbTarjetas tarId) {
        this.tarId = tarId;
    }

    public TbVisitantes getVisId() {
        return visId;
    }

    public void setVisId(TbVisitantes visId) {
        this.visId = visId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (enId != null ? enId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbEntradas)) {
            return false;
        }
        TbEntradas other = (TbEntradas) object;
        if ((this.enId == null && other.enId != null) || (this.enId != null && !this.enId.equals(other.enId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbEntradas[ enId=" + enId + " ]";
    }
    
}
