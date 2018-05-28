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
@Table(name = "personnel")
@NamedQueries({
    @NamedQuery(name = "Personnel.findAll", query = "SELECT p FROM Personnel p")})
public class Personnel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_personnel")
    private Long idPersonnel;
    @Size(max = 255)
    @Column(name = "nom")
    private String nom;
    @Size(max = 255)
    @Column(name = "prenom")
    private String prenom;
    @Size(max = 255)
    @Column(name = "adresse")
    private String adresse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_tel")
    private double numTel;
    @JoinColumn(name = "id_agence", referencedColumnName = "id_agence")
    @ManyToOne(optional = false)
    private Agence idAgence;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersonnel")
    private List<Users> usersList;

    public Personnel() {
    }

    public Personnel(Long idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public Personnel(Long idPersonnel, double numTel) {
        this.idPersonnel = idPersonnel;
        this.numTel = numTel;
    }

    public Personnel(String nom, String prenom, String adresse, double numTel, Agence idAgence) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numTel = numTel;
        this.idAgence = idAgence;
    }

    public Long getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(Long idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public double getNumTel() {
        return numTel;
    }

    public void setNumTel(double numTel) {
        this.numTel = numTel;
    }

    public Agence getIdAgence() {
        return idAgence;
    }

    public void setIdAgence(Agence idAgence) {
        this.idAgence = idAgence;
    }

    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersonnel != null ? idPersonnel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personnel)) {
            return false;
        }
        Personnel other = (Personnel) object;
        if ((this.idPersonnel == null && other.idPersonnel != null) || (this.idPersonnel != null && !this.idPersonnel.equals(other.idPersonnel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.sid.entities.Personnel[ idPersonnel=" + idPersonnel + " ]";
    }

}
