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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")})
public class Users implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private List<UsersRoles> usersRolesList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "username")
    private String username;
    @ManyToMany(mappedBy = "usersList")
    private List<Roles> rolesList;
    @JoinColumn(name = "id_personnel")
    @ManyToOne
    private Personnel idPersonnel;

    public Users() {
    }

    public Users(String username) {
        this.username = username;
    }

    public Users(String username, String password, boolean active) {
        this.username = username;
        this.password = password;
        this.active = active;
    }

    public Users(String username, String password, boolean active, Personnel idPersonnel) {
        this.password = password;
        this.active = active;
        this.username = username;
        this.idPersonnel = idPersonnel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList) {
        this.rolesList = rolesList;
    }

    public Personnel getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(Personnel idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.sid.entities.Users[ username=" + username + " ]";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<UsersRoles> getUsersRolesList() {
        return usersRolesList;
    }

    public void setUsersRolesList(List<UsersRoles> usersRolesList) {
        this.usersRolesList = usersRolesList;
    }

}
