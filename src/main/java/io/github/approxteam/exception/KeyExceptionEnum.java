/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.approxteam.exception;

/**
 *
 * @author razikus
 */
public enum KeyExceptionEnum {
    UNIQUE(100, "UNIQUE", "UNIQUEEXCEPTION"),
    RECORDNOTFOUND(101, "RECORDNOTFOUND", "RECORDNOTFOUNDEXCEPTION"),
    PASSWORDTOOSMALL(200, "PASSWORDTOOSMALL", "PASSWORDEXCEPTION"),
    NICKNAMETOOSMALL(201, "NICKNAMETOOSMALL", "NICKNAMEEXCEPTION"),
    BADEMAIL(202, "BADEMAIL", "BADEMAILEXCEPTION"),
    BADTYPE(300, "BADTYPE", "BADTYPEEXCEPTION"),
    BADPARAMETER(301, "BADPARAMETER", "BADPARAMETEREXCEPTION");
    
    
    private KeyException e;

    private KeyExceptionEnum(int code, String name, String message) {
        this.e = new KeyException(code, name, message);
        
    }

    public KeyException getE() {
        return e;
    }

    public String getAdditional() {
        return e.getAdditional();
    }

    public KeyException setAdditional(String additional) {
        e.setAdditional(additional);
        return e;
    }
    
    
    
    
    
    
}
