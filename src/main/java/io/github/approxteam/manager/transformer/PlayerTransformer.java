/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.approxteam.manager.transformer;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import io.github.approxteam.entity.Player;
import io.github.approxteam.dto.PlayerDTO;

/**
 *
 * @author razikus
 */
public class PlayerTransformer {
    
    
    
    public static PlayerDTO convertTOPlayerDTO(Player p) {
        PlayerDTO pDTO = new PlayerDTO();
        pDTO.setId(p.getId());
        pDTO.setActivated(p.isActivated());
        pDTO.setEmail(p.getEmail());
        pDTO.setFirstVisit(p.getFirstVisit());
        pDTO.setLastLogin(p.getLastLogin());
        pDTO.setName(p.getName());
        pDTO.setEncodedPassword(p.getEncodedPassword());
        return pDTO;
    }
    
    public static Player convertToPlayer(PlayerDTO p, EntityManager em) {
        Player pEntity = new Player();
        if(p.getId() > 0) { 
            pEntity = em.find(Player.class, p.getId());
        }
        pEntity.setActivated(p.isActivated());
        pEntity.setEmail(p.getEmail());
        pEntity.setFirstVisit(p.getFirstVisit());
        pEntity.setLastLogin(p.getLastLogin());
        pEntity.setName(p.getName());
        pEntity.setEncodedPassword(p.getEncodedPassword());
        return pEntity;
        
    }
    
    public static List<PlayerDTO> convertToPlayerDTOList(List<Player> players) {
        List<PlayerDTO> converted = new ArrayList<>(players.size());
        for(int j = 0; j < players.size(); j++) {
            converted.add(convertTOPlayerDTO(players.get(j)));
        }
        
        return converted;
        
    }
    
}
