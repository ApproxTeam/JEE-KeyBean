/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.approxteam.exception;

import java.io.Serializable;

/**
 *
 * @author razikus
 */
public class KeyException extends Exception implements Serializable{
    private int code;
    private String name;
    private String additional;

    public KeyException(int code, String name, String message) {
        super(message);
        this.code = code;
        this.name = name;
        this.additional = "NONE";
    }

    public KeyException(int code, String name, String additional, String message) {
        super(message);
        this.code = code;
        this.name = name;
        this.additional = additional;
    }
    
    

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }
    
    
    
    
    
}
