/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sid.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Brendev
 */
@Entity
@Table(name = "affecter")
@NamedQueries({
    @NamedQuery(name = "Affecter.findAll", query = "SELECT a FROM Affecter a")})
public class Affecter implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AffecterPK affecterPK;
    @Column(name = "dateAffectation")
    @Temporal(TemporalType.DATE)
    private Date dateAffectation;
    @Column(name = "dateRevocation")
    @Temporal(TemporalType.DATE)
    private Date dateRevocation;
    @JoinColumn(name = "id_agence", referencedColumnName = "id_agence", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Agence agence;
    @JoinColumn(name = "id_personnel", referencedColumnName = "id_personnel", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Personnel personnel;

    public Affecter() {
    }

    public Affecter(AffecterPK affecterPK) {
        this.affecterPK = affecterPK;
    }

    public Affecter(long idPersonnel, long idAgence) {
        this.affecterPK = new AffecterPK(idPersonnel, idAgence);
    }

    public AffecterPK getAffecterPK() {
        return affecterPK;
    }

    public void setAffecterPK(AffecterPK affecterPK) {
        this.affecterPK = affecterPK;
    }

    public Date getDateAffectation() {
        return dateAffectation;
    }

    public void setDateAffectation(Date dateAffectation) {
        this.dateAffectation = dateAffectation;
    }

    public Date getDateRevocation() {
        return dateRevocation;
    }

    public void setDateRevocation(Date dateRevocation) {
        this.dateRevocation = dateRevocation;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (affecterPK != null ? affecterPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Affecter)) {
            return false;
        }
        Affecter other = (Affecter) object;
        if ((this.affecterPK == null && other.affecterPK != null) || (this.affecterPK != null && !this.affecterPK.equals(other.affecterPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.sid.entities.Affecter[ affecterPK=" + affecterPK + " ]";
    }

}
