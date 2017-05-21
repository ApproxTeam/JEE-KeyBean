/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.approxteam.dto;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author razikus
 */
public class PlayerDTO implements Serializable{
    private long id;
    private String name;
    private String encodedPassword;
    private Calendar lastLogin;
    private Calendar firstVisit;
    private String email;
    private boolean activated;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public static PlayerDTO setMe(PlayerDTO f, PlayerDTO t) {
        f.setActivated(t.isActivated());
        f.setEmail(t.getEmail());
        f.setFirstVisit(t.getFirstVisit());
        f.setLastLogin(t.getLastLogin());
        f.setName(t.getName());
        f.setEncodedPassword(t.getEncodedPassword());
        return f;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "id=" + id + ", name=" + name + ", SHA256=" + encodedPassword + ", lastLogin=" + lastLogin + ", firstVisit=" + firstVisit + ", email=" + email + ", activated=" + activated + '}';
    }
    
    public PlayerDTO codePassword() {
        this.encodedPassword = "********";
        return this;
    }
    
}
