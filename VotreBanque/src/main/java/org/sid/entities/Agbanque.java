/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sid.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Brendev
 */
@Entity
@Table(name = "agbanque")
@NamedQueries({
    @NamedQuery(name = "Agbanque.findAll", query = "SELECT a FROM Agbanque a")})
public class Agbanque implements Serializable {

    @Size(max = 255)
    @Column(name = "siege_social")
    private String siegeSocial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "capital")
    private double capital;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_banque")
    private Long idBanque;
    @Column(name = "date_creation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBanque")
    private List<Agence> agenceList;

    public Agbanque() {
    }

    public Agbanque(Long idBanque) {
        this.idBanque = idBanque;
    }

    public Agbanque(Long idBanque, double capital) {
        this.idBanque = idBanque;
        this.capital = capital;
    }

    public Long getIdBanque() {
        return idBanque;
    }

    public void setIdBanque(Long idBanque) {
        this.idBanque = idBanque;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getSiegeSocial() {
        return siegeSocial;
    }

    public void setSiegeSocial(String siegeSocial) {
        this.siegeSocial = siegeSocial;
    }

    public List<Agence> getAgenceList() {
        return agenceList;
    }

    public void setAgenceList(List<Agence> agenceList) {
        this.agenceList = agenceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBanque != null ? idBanque.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agbanque)) {
            return false;
        }
        Agbanque other = (Agbanque) object;
        if ((this.idBanque == null && other.idBanque != null) || (this.idBanque != null && !this.idBanque.equals(other.idBanque))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.sid.entities.Agbanque[ idBanque=" + idBanque + " ]";
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

}
