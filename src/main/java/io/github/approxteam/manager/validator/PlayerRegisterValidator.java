/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.approxteam.manager.validator;

import java.util.Optional;
import java.util.Properties;
import io.github.approxteam.dto.PlayerDTO;
import io.github.approxteam.exception.KeyException;
import io.github.approxteam.exception.KeyExceptionEnum;
import io.github.approxteam.properties.PropertiesBuilder;
import io.github.approxteam.properties.PropertyComment;

/**
 *
 * @author razikus
 */
@PropertyComment(className = PlayerRegisterValidator.class)
public class PlayerRegisterValidator implements Validator {

    private String defaultEmailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    private static Properties prop = PropertiesBuilder.getProperties(PlayerRegisterValidator.class);
    
    
    
    
    @Override
    public Optional<KeyException> validate(Object object) {
        if(object instanceof PlayerDTO) {
            PlayerDTO conv = (PlayerDTO) object;
            if(conv.getEncodedPassword().length() < Integer.parseInt(prop.getProperty("minpasslength", "5"))) {
               
                return cE(KeyExceptionEnum.PASSWORDTOOSMALL);
            }
            
            if(conv.getName().length() < Integer.parseInt(prop.getProperty("minnicknamelength", "3"))) {
                return cE(KeyExceptionEnum.NICKNAMETOOSMALL);
            }
            
            if(!conv.getEmail().matches(prop.getProperty("emailmatcher", defaultEmailRegex))) {
                
                return cE(KeyExceptionEnum.BADEMAIL);
            }
            
            return Optional.empty();
            
            
            
        }
        else {
       
            return cE(KeyExceptionEnum.BADTYPE);
        }
    }
    
    private static Optional<KeyException> cE(KeyExceptionEnum e) {
        return Optional.of(e.getE());
    }
    
}
