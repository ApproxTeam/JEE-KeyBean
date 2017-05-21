/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.approxteam.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import io.github.approxteam.entity.Player;
import io.github.approxteam.dto.PlayerDTO;
import io.github.approxteam.exception.KeyException;
import io.github.approxteam.exception.KeyExceptionEnum;
import io.github.approxteam.manager.encoder.Encoder;
import io.github.approxteam.manager.transformer.PlayerTransformer;
import io.github.approxteam.properties.PropertiesBuilder;
import io.github.approxteam.properties.PropertyComment;
import io.github.approxteam.utils.EncryptionUtils;

/**
 *
 * @author razikus
 */
@PropertyComment(className = PlayerManager.class)
public class PlayerManager {
    
    private static Encoder encoder;
    private static Properties classProp = PropertiesBuilder.getProperties(PlayerManager.class);
    static {
        String className = classProp.getProperty("passwordEncoder", "pl.razikus.manager.encoder.SHA256Encoder");
        try {
            encoder = (Encoder) Class.forName(className).newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PlayerManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(PlayerManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PlayerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static PlayerDTO addUser(PlayerDTO newPlayer, EntityManager em) {
        Calendar now = Calendar.getInstance();
        newPlayer.setActivated(false);
        newPlayer.setFirstVisit(now);
        newPlayer.setLastLogin(now);
        newPlayer.setEncodedPassword(EncryptionUtils.sha256(newPlayer.getEncodedPassword()));
        Player converted = PlayerTransformer.convertToPlayer(newPlayer, em);
        try {
            em.persist(converted);
        } catch(javax.persistence.PersistenceException e) {
            return new PlayerDTO();
        }
        return PlayerTransformer.convertTOPlayerDTO(converted);
    }
    
    public static PlayerDTO getUser(String nickname, EntityManager em) throws KeyException {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Player> cq = cb.createQuery(Player.class);
        Root<Player> playerRoot = cq.from(Player.class);
        ParameterExpression<String> p = cb.parameter(String.class);
        cq.select(playerRoot).where(cb.equal(playerRoot.get("name"), p));
        TypedQuery<Player> query = em.createQuery(cq);
        query.setParameter(p, nickname);
        List<Player> players = query.getResultList();
        if(players.isEmpty()) {
            throw KeyExceptionEnum.RECORDNOTFOUND.getE();
        }
        else {
            return PlayerTransformer.convertTOPlayerDTO(players.get(0));
        }
    }
    
    public static PlayerDTO getUser(long id, EntityManager em) throws KeyException {
        Player p = em.find(Player.class, id);
        if(p == null) {
            throw KeyExceptionEnum.RECORDNOTFOUND.getE();
        }
        else {
            return PlayerTransformer.convertTOPlayerDTO(p);
        }
    }
    
    public static PlayerDTO updateLastLogin(PlayerDTO player, EntityManager em) throws KeyException{
        Player p = em.find(Player.class, player.getId());
        if(p == null) {
            throw KeyExceptionEnum.RECORDNOTFOUND.getE();
        }
        else {
            p.setLastLogin(Calendar.getInstance());
            em.merge(p);
            return PlayerTransformer.convertTOPlayerDTO(p);
        }
    }
    
    public static boolean isGoodLoginPassword(String name, boolean update, String password, EntityManager em) throws KeyException {
        PlayerDTO p = getUser(name, em);
        if(p.getEncodedPassword().equals(encoder.encode(password))) {
            if(update) {
                updateLastLogin(p, em);
            }
            return true;
        }
        return false;
    }
        
    
    public static List<PlayerDTO> getUsers(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Player> cq = cb.createQuery(Player.class);
        Root<Player> playerRoot = cq.from(Player.class);
        cq.select(playerRoot);
        TypedQuery<Player> query = em.createQuery(cq);
        List<Player> players = query.getResultList();
        return PlayerTransformer.convertToPlayerDTOList(players);
        
    }

    public static PlayerDTO updatePlayerCredentials(long id, PlayerDTO userDTO, boolean force, EntityManager em) throws KeyException {
        Player loaded = PlayerTransformer.convertToPlayer(getUser(id, em), em);
        loaded.setEncodedPassword(encoder.encode(userDTO.getEncodedPassword()));
        loaded.setEmail(userDTO.getEmail());
        if(force) {
            loaded.setActivated(userDTO.isActivated());
            if(userDTO.getFirstVisit() != null){
                loaded.setFirstVisit(userDTO.getFirstVisit());
            }
            else {
                loaded.setFirstVisit(Calendar.getInstance());
            }
            
            if(userDTO.getLastLogin() != null){
                loaded.setLastLogin(userDTO.getLastLogin());
            }
            else {
                loaded.setLastLogin(Calendar.getInstance());
            }
        }
        
        loaded = em.merge(loaded);
        return PlayerTransformer.convertTOPlayerDTO(loaded);
    }

    public static PlayerDTO updatePlayerCredentials(String nickname, PlayerDTO userDTO, boolean force, EntityManager em) throws KeyException {
        Player loaded = PlayerTransformer.convertToPlayer(getUser(nickname, em), em);
        loaded.setEncodedPassword(encoder.encode(userDTO.getEncodedPassword()));
        loaded.setEmail(userDTO.getEmail());
        if(force) {
            loaded.setActivated(userDTO.isActivated());
            if(userDTO.getFirstVisit() != null){
                loaded.setFirstVisit(userDTO.getFirstVisit());
            }
            else {
                loaded.setFirstVisit(Calendar.getInstance());
            }
            
            if(userDTO.getLastLogin() != null){
                loaded.setLastLogin(userDTO.getLastLogin());
            }
            else {
                loaded.setLastLogin(Calendar.getInstance());
            }
            
        }
        
        loaded = em.merge(loaded);
        return PlayerTransformer.convertTOPlayerDTO(loaded);
        
    }

}

