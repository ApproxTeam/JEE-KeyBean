/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.approxteam.keybean.ejb;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import io.github.approxteam.dto.PlayerDTO;
import io.github.approxteam.exception.KeyException;
import io.github.approxteam.exception.KeyExceptionEnum;
import io.github.approxteam.manager.PlayerManager;
import io.github.approxteam.manager.validator.PlayerRegisterValidator;
import io.github.approxteam.manager.validator.Validator;

/**
 *
 * @author razikus
 */
@Stateless
public class KeyBean implements KeyBeanRemote {
    
    @PersistenceContext
    private EntityManager em;
    
    private Validator playerValidator = new PlayerRegisterValidator();
    
    @Override
    public PlayerDTO getUserByID(long id) throws KeyException{
        if(id > 0) {
            return PlayerManager.getUser(id, em).codePassword();
        }
        else {
            throw KeyExceptionEnum.BADPARAMETER.getE();
        }
    }

    @Override
    public PlayerDTO addUser(PlayerDTO playerDTO) throws KeyException{
        Optional<KeyException> key = playerValidator.validate(playerDTO);
        if(key.isPresent()) {
            throw key.get();
        }
        else {
            try {
                PlayerDTO dto = getUserByNickname(playerDTO.getName());
            } catch(KeyException e) {
                return PlayerManager.addUser(playerDTO, em).codePassword();
            }
            
            throw KeyExceptionEnum.UNIQUE.setAdditional("NICKNAME");
        }
        
    }

    @Override
    public PlayerDTO updateUserCredentials(long id, PlayerDTO userDTO, boolean force) throws KeyException{
        return PlayerManager.updatePlayerCredentials(id, userDTO, force, em);
    }

    @Override
    public PlayerDTO updateUserCredentials(String nickname, PlayerDTO userDTO, boolean force) throws KeyException{
        
        return PlayerManager.updatePlayerCredentials(nickname, userDTO, force, em);
    }   

    @Override
    public PlayerDTO getUserByNickname(String nickname) throws KeyException{
        if(!nickname.isEmpty()) {
            return PlayerManager.getUser(nickname, em).codePassword();
        }
        else {
            throw KeyExceptionEnum.BADPARAMETER.getE();
        }
    }

    @Override
    public boolean isGoodLoginPassword(String login, String password, boolean update) throws KeyException{
        return PlayerManager.isGoodLoginPassword(login, update, password, em);
    }

    @Override
    public List<PlayerDTO> getUsers() throws KeyException{
        return code(PlayerManager.getUsers(em));
        
    }
    
    private List<PlayerDTO> code(List<PlayerDTO> list) {
        for(int j = 0; j < list.size(); j++) {
            list.get(j).codePassword();
        }
        return list;
    }
    

    
}
