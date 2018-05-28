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
@Table(name = "users_roles")
@NamedQueries({
    @NamedQuery(name = "UsersRoles.findAll", query = "SELECT u FROM UsersRoles u")})
public class UsersRoles implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsersRolesPK usersRolesPK;
    @Column(name = "dateAffectation")
    @Temporal(TemporalType.DATE)
    private Date dateAffectation;
    @JoinColumn(name = "role", referencedColumnName = "role", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Roles roles;
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public UsersRoles() {
    }

    public UsersRoles(UsersRolesPK usersRolesPK) {
        this.usersRolesPK = usersRolesPK;
    }

    public UsersRoles(String username, String role) {
        this.usersRolesPK = new UsersRolesPK(username, role);
    }

    public UsersRolesPK getUsersRolesPK() {
        return usersRolesPK;
    }

    public void setUsersRolesPK(UsersRolesPK usersRolesPK) {
        this.usersRolesPK = usersRolesPK;
    }

    public Date getDateAffectation() {
        return dateAffectation;
    }

    public void setDateAffectation(Date dateAffectation) {
        this.dateAffectation = dateAffectation;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usersRolesPK != null ? usersRolesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersRoles)) {
            return false;
        }
        UsersRoles other = (UsersRoles) object;
        if ((this.usersRolesPK == null && other.usersRolesPK != null) || (this.usersRolesPK != null && !this.usersRolesPK.equals(other.usersRolesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.sid.entities.UsersRoles[ usersRolesPK=" + usersRolesPK + " ]";
    }

}
