/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.approxteam.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author razikus
 */
@Entity
@SequenceGenerator(name = "PLAYER_SEQ_GEN",
    allocationSize = 20,
    initialValue = 1,
    sequenceName = "PLAYER_SEQ")
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"name"}))
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "PLAYER_SEQ_GEN")
    private Long id;
    
    @Column(length=128, nullable=false) 
    private String name;
    private String encodedPassword;
    private Calendar lastLogin;
    private Calendar firstVisit;
    private String email;
    private boolean activated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (this.activated != other.activated) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.encodedPassword, other.encodedPassword)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.lastLogin, other.lastLogin)) {
            return false;
        }
        if (!Objects.equals(this.firstVisit, other.firstVisit)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Player{" + "id=" + id + ", name=" + name + ", SHA256=" + encodedPassword + ", lastLogin=" + lastLogin + ", firstVisit=" + firstVisit + ", email=" + email + ", activated=" + activated + '}';
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    public Calendar getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Calendar lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Calendar getFirstVisit() {
        return firstVisit;
    }

    public void setFirstVisit(Calendar firstVisit) {
        this.firstVisit = firstVisit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
    
    
    
}
