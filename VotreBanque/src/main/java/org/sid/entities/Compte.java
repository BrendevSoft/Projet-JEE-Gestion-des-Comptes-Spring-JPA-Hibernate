package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_CPTE", discriminatorType = DiscriminatorType.STRING)
public class Compte implements Serializable {

    @Id
    private String codeCompte;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCreation;
    private double solde;

    @ManyToOne
    @JoinColumn(name = "code")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_agence")
    private Agence agence;

    @ManyToOne
    @JoinColumn(name = "id_personnel")
    private Personnel personnel;

    @OneToMany(mappedBy = "compte")
    private Collection<Operation> operations;

    public Compte(String codeCompte, Date dateCreation, double solde, Client client, Agence agence, Personnel perspnnel) {
        super();
        this.codeCompte = codeCompte;
        this.dateCreation = dateCreation;
        this.solde = solde;
        this.client = client;
        this.agence = agence;
        this.personnel = perspnnel;
    }

    public Compte() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getCodeCompte() {
        return codeCompte;
    }

    public void setCodeCompte(String codeCompte) {
        this.codeCompte = codeCompte;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Collection<Operation> getOperations() {
        return operations;
    }

    public void setOperations(Collection<Operation> operations) {
        this.operations = operations;
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

}
