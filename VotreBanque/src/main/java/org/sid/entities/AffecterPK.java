/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sid.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Brendev
 */
@Embeddable
public class AffecterPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_personnel")
    private long idPersonnel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_agence")
    private long idAgence;

    public AffecterPK() {
    }

    public AffecterPK(long idPersonnel, long idAgence) {
        this.idPersonnel = idPersonnel;
        this.idAgence = idAgence;
    }

    public long getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(long idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public long getIdAgence() {
        return idAgence;
    }

    public void setIdAgence(long idAgence) {
        this.idAgence = idAgence;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPersonnel;
        hash += (int) idAgence;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AffecterPK)) {
            return false;
        }
        AffecterPK other = (AffecterPK) object;
        if (this.idPersonnel != other.idPersonnel) {
            return false;
        }
        if (this.idAgence != other.idAgence) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.sid.entities.AffecterPK[ idPersonnel=" + idPersonnel + ", idAgence=" + idAgence + " ]";
    }

}
