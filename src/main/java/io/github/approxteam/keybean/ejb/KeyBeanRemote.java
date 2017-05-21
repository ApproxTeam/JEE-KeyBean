/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.approxteam.keybean.ejb;

import java.util.List;
import javax.ejb.Remote;
import io.github.approxteam.dto.PlayerDTO;
import io.github.approxteam.exception.KeyException;

/**
 *
 * @author razikus
 */
@Remote
public interface KeyBeanRemote {
    public PlayerDTO getUserByID(long id) throws KeyException;
    public PlayerDTO addUser(PlayerDTO playerDTO) throws KeyException;
    public PlayerDTO updateUserCredentials(long id, PlayerDTO userDTO, boolean force) throws KeyException;
    public PlayerDTO updateUserCredentials(String nickname, PlayerDTO userDTO, boolean force) throws KeyException;
    public PlayerDTO getUserByNickname(String nickname) throws KeyException;
    public boolean isGoodLoginPassword(String login, String password, boolean update) throws KeyException;
    public List<PlayerDTO> getUsers() throws KeyException;
    
    
}
