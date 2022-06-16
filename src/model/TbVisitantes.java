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
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author monge
 */
@Entity
@Table(name = "TB_VISITANTES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbVisitantes.findAll", query = "SELECT t FROM TbVisitantes t"),
    @NamedQuery(name = "TbVisitantes.findByVisId", query = "SELECT t FROM TbVisitantes t WHERE t.visId = :visId"),
    @NamedQuery(name = "TbVisitantes.findByVisCedPasap", query = "SELECT t FROM TbVisitantes t WHERE t.visCedPasap = :visCedPasap"),
    @NamedQuery(name = "TbVisitantes.findByVisNombre", query = "SELECT t FROM TbVisitantes t WHERE t.visNombre = :visNombre"),
    @NamedQuery(name = "TbVisitantes.findByVisApellidos", query = "SELECT t FROM TbVisitantes t WHERE t.visApellidos = :visApellidos"),
    @NamedQuery(name = "TbVisitantes.findByVisFechanacimiento", query = "SELECT t FROM TbVisitantes t WHERE t.visFechanacimiento = :visFechanacimiento"),
    @NamedQuery(name = "TbVisitantes.findByVisCorreoelectronico", query = "SELECT t FROM TbVisitantes t WHERE t.visCorreoelectronico = :visCorreoelectronico"),
    @NamedQuery(name = "TbVisitantes.findByVisRazonvisita", query = "SELECT t FROM TbVisitantes t WHERE t.visRazonvisita = :visRazonvisita")})
public class TbVisitantes implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TB_VISITANTES_SEQ", sequenceName = "TB_VISITANTES_SEQ10", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_VISITANTES_SEQ")
    @Basic(optional = false)
    @Column(name = "VIS_ID")
    private Long visId;
    @Column(name = "VIS_CED_PASAP")
    private Long visCedPasap;
    @Column(name = "VIS_NOMBRE")
    private String visNombre;
    @Column(name = "VIS_APELLIDOS")
    private String visApellidos;
    @Column(name = "VIS_FECHANACIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date visFechanacimiento;
    @Column(name = "VIS_CORREOELECTRONICO")
    private String visCorreoelectronico;
    @Column(name = "VIS_RAZONVISITA")
    private String visRazonvisita;
    @OneToMany(mappedBy = "visId")
    private Collection<TbEntradas> tbEntradasCollection;
    @JoinColumn(name = "PAI_ID", referencedColumnName = "PAI_ID")
    @ManyToOne
    private TbPaises paiId;

    public TbVisitantes() {
    }
    
    public TbVisitantes(TbVisitantesDto tbvisitantesDto) {
        this.visId = tbvisitantesDto.getVisId();
        actualizarVisitante(tbvisitantesDto);
    }

    public void actualizarVisitante(TbVisitantesDto tbvisitantesDto) {
        this.visCedPasap = tbvisitantesDto.getVisCedPasap();
        this.visNombre = tbvisitantesDto.getVisNombre();
        this.visApellidos = tbvisitantesDto.getVisApellidos();
        this.visFechanacimiento = Date.from(tbvisitantesDto.getVisFechanacimiento().atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.visCorreoelectronico = tbvisitantesDto.getVisCorreoelectronico();
        this.visRazonvisita = tbvisitantesDto.getVisRazonvisita();
        

    }

//    public TbVisitantes(Long visId) {
//        this.visId = visId;
//    }

    public Long getVisId() {
        return visId;
    }

    public void setVisId(Long visId) {
        this.visId = visId;
    }

    public Long getVisCedPasap() {
        return visCedPasap;
    }

    public void setVisCedPasap(Long visCedPasap) {
        this.visCedPasap = visCedPasap;
    }

    public String getVisNombre() {
        return visNombre;
    }

    public void setVisNombre(String visNombre) {
        this.visNombre = visNombre;
    }

    public String getVisApellidos() {
        return visApellidos;
    }

    public void setVisApellidos(String visApellidos) {
        this.visApellidos = visApellidos;
    }

    public Date getVisFechanacimiento() {
        return visFechanacimiento;
    }

    public void setVisFechanacimiento(Date visFechanacimiento) {
        this.visFechanacimiento = visFechanacimiento;
    }

    public String getVisCorreoelectronico() {
        return visCorreoelectronico;
    }

    public void setVisCorreoelectronico(String visCorreoelectronico) {
        this.visCorreoelectronico = visCorreoelectronico;
    }

    public String getVisRazonvisita() {
        return visRazonvisita;
    }

    public void setVisRazonvisita(String visRazonvisita) {
        this.visRazonvisita = visRazonvisita;
    }

    @XmlTransient
    public Collection<TbEntradas> getTbEntradasCollection() {
        return tbEntradasCollection;
    }

    public void setTbEntradasCollection(Collection<TbEntradas> tbEntradasCollection) {
        this.tbEntradasCollection = tbEntradasCollection;
    }

    public TbPaises getPaiId() {
        return paiId;
    }

    public void setPaiId(TbPaises paiId) {
        this.paiId = paiId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (visId != null ? visId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbVisitantes)) {
            return false;
        }
        TbVisitantes other = (TbVisitantes) object;
        if ((this.visId == null && other.visId != null) || (this.visId != null && !this.visId.equals(other.visId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbVisitantes[ visId=" + visId + " ]";
    }
    
}
