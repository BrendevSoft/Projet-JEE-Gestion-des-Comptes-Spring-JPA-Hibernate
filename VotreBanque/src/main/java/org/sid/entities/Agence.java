/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sid.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Brendev
 */
@Entity
@Table(name = "agence")
@NamedQueries({
    @NamedQuery(name = "Agence.findAll", query = "SELECT a FROM Agence a")})
public class Agence implements Serializable {

    @Size(max = 255)
    @Column(name = "nom_agence")
    private String nomAgence;
    @Size(max = 255)
    @Column(name = "adresse")
    private String adresse;
    @Size(max = 255)
    @Column(name = "type_agence")
    private String typeAgence;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_tel")
    private double numTel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAgence")
    private List<Personnel> personnelList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_agence")
    private Long idAgence;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "fax")
    private Double fax;
    @JoinColumn(name = "id_banque", referencedColumnName = "id_banque")
    @ManyToOne(optional = false)
    private Agbanque idBanque;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agence")
    private List<Affecter> affecterList;

    public Agence() {
    }

    public Agence(Long idAgence) {
        this.idAgence = idAgence;
    }

    public Agence(Long idAgence, double numTel) {
        this.idAgence = idAgence;
        this.numTel = numTel;
    }

    public Long getIdAgence() {
        return idAgence;
    }

    public void setIdAgence(Long idAgence) {
        this.idAgence = idAgence;
    }

    public String getNomAgence() {
        return nomAgence;
    }

    public void setNomAgence(String nomAgence) {
        this.nomAgence = nomAgence;
    }

    public Double getFax() {
        return fax;
    }

    public void setFax(Double fax) {
        this.fax = fax;
    }

    public String getTypeAgence() {
        return typeAgence;
    }

    public void setTypeAgence(String typeAgence) {
        this.typeAgence = typeAgence;
    }

    public double getNumTel() {
        return numTel;
    }

    public void setNumTel(double numTel) {
        this.numTel = numTel;
    }

    public Agbanque getIdBanque() {
        return idBanque;
    }

    public void setIdBanque(Agbanque idBanque) {
        this.idBanque = idBanque;
    }

    public List<Affecter> getAffecterList() {
        return affecterList;
    }

    public void setAffecterList(List<Affecter> affecterList) {
        this.affecterList = affecterList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAgence != null ? idAgence.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agence)) {
            return false;
        }
        Agence other = (Agence) object;
        if ((this.idAgence == null && other.idAgence != null) || (this.idAgence != null && !this.idAgence.equals(other.idAgence))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.sid.entities.Agence[ idAgence=" + idAgence + " ]";
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<Personnel> getPersonnelList() {
        return personnelList;
    }

    public void setPersonnelList(List<Personnel> personnelList) {
        this.personnelList = personnelList;
    }

}
