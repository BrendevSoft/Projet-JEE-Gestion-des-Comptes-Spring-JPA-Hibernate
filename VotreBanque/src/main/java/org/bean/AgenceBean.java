/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bean;

/**
 *
 * @author Brendev
 */
public class AgenceBean {

    private String nom_agence;

    private String adresse;

    private String type_agence;

    private String fax;

    private String num_tel;

    public AgenceBean() {
    }

    public AgenceBean(String nom_agence, String adresse, String type_agence, String fax, String num_tel) {
        this.nom_agence = nom_agence;
        this.adresse = adresse;
        this.type_agence = type_agence;
        this.fax = fax;
        this.num_tel = num_tel;
    }

    public String getNom_agence() {
        return nom_agence;
    }

    public void setNom_agence(String nom_agence) {
        this.nom_agence = nom_agence;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getType_agence() {
        return type_agence;
    }

    public void setType_agence(String type_agence) {
        this.type_agence = type_agence;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

}
