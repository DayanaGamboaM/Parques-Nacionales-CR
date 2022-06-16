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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author monge
 */
@Entity
@Table(name = "TB_ESPECIES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbEspecies.findAll", query = "SELECT t FROM TbEspecies t"),
    @NamedQuery(name = "TbEspecies.findByEspId", query = "SELECT t FROM TbEspecies t WHERE t.espId = :espId"),
    @NamedQuery(name = "TbEspecies.findByEspTipo", query = "SELECT t FROM TbEspecies t WHERE t.espTipo = :espTipo"),
    @NamedQuery(name = "TbEspecies.findByEspNomcientifico", query = "SELECT t FROM TbEspecies t WHERE t.espNomcientifico = :espNomcientifico"),
    @NamedQuery(name = "TbEspecies.findByEspNomcomun", query = "SELECT t FROM TbEspecies t WHERE t.espNomcomun = :espNomcomun"),
    @NamedQuery(name = "TbEspecies.findByEspPeso", query = "SELECT t FROM TbEspecies t WHERE t.espPeso = :espPeso"),
    @NamedQuery(name = "TbEspecies.findByEspTama\u00f1o", query = "SELECT t FROM TbEspecies t WHERE t.espTama\u00f1o = :espTama\u00f1o"),
    @NamedQuery(name = "TbEspecies.findByEspCaracprinc", query = "SELECT t FROM TbEspecies t WHERE t.espCaracprinc = :espCaracprinc")})
public class TbEspecies implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TB_ESPECIES_SEQ", sequenceName = "TB_ESPECIES_SEQ02", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_ESPECIES_SEQ")
    @Basic(optional = false)
    @Column(name = "ESP_ID")
    private Long espId;
    @Column(name = "ESP_TIPO")
    private String espTipo;
    @Column(name = "ESP_NOMCIENTIFICO")
    private String espNomcientifico;
    @Column(name = "ESP_NOMCOMUN")
    private String espNomcomun;
    @Column(name = "ESP_PESO")
    private Long espPeso;
    @Column(name = "ESP_TAMA\u00d1O")
    private String espTamaño;
    @Column(name = "ESP_CARACPRINC")
    private String espCaracprinc;
    @JoinColumn(name = "PAR_ID", referencedColumnName = "PAR_ID")
    @ManyToOne
    private TbParques parId;
    @JoinColumn(name = "REF_ID", referencedColumnName = "REF_ID")
    @ManyToOne
    private TbRefugios refId;

    public TbEspecies() {
    }

    public TbEspecies(TbEspeciesDto tbespeciesDto) {
        this.espId = tbespeciesDto.getEspId();
        actualizarEspecie(tbespeciesDto);
    }

    public void actualizarEspecie(TbEspeciesDto tbespeciesDto) {
        this.espTipo = tbespeciesDto.getEspTipo();
        this.espNomcientifico = tbespeciesDto.getEspNomcientifico();
        this.espNomcomun = tbespeciesDto.getEspNomcomun();
        this.espPeso = tbespeciesDto.getEspPeso();
        this.espTamaño = tbespeciesDto.getEspTamaño();
        this.espCaracprinc = tbespeciesDto.getEspCaracprinc();

    }
//    public TbEspecies(Long espId) {
//        this.espId = espId;
//    }

    public Long getEspId() {
        return espId;
    }

    public void setEspId(Long espId) {
        this.espId = espId;
    }

    public String getEspTipo() {
        return espTipo;
    }

    public void setEspTipo(String espTipo) {
        this.espTipo = espTipo;
    }

    public String getEspNomcientifico() {
        return espNomcientifico;
    }

    public void setEspNomcientifico(String espNomcientifico) {
        this.espNomcientifico = espNomcientifico;
    }

    public String getEspNomcomun() {
        return espNomcomun;
    }

    public void setEspNomcomun(String espNomcomun) {
        this.espNomcomun = espNomcomun;
    }

    public Long getEspPeso() {
        return espPeso;
    }

    public void setEspPeso(Long espPeso) {
        this.espPeso = espPeso;
    }

    public String getEspTamaño() {
        return espTamaño;
    }

    public void setEspTamaño(String espTamaño) {
        this.espTamaño = espTamaño;
    }

    public String getEspCaracprinc() {
        return espCaracprinc;
    }

    public void setEspCaracprinc(String espCaracprinc) {
        this.espCaracprinc = espCaracprinc;
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
        hash += (espId != null ? espId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbEspecies)) {
            return false;
        }
        TbEspecies other = (TbEspecies) object;
        if ((this.espId == null && other.espId != null) || (this.espId != null && !this.espId.equals(other.espId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbEspecies[ espId=" + espId + " ]";
    }
    
}
