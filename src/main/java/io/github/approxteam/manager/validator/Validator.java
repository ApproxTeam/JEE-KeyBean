/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.approxteam.manager.validator;

import java.util.Optional;
import javax.persistence.EntityManager;
import io.github.approxteam.keybean.ejb.KeyBeanRemote;
import io.github.approxteam.exception.KeyException;

/**
 *
 * @author razikus
 */
public interface Validator {
    public Optional<KeyException> validate(Object object);
    
}
