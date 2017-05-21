/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.approxteam.manager.encoder;

import io.github.approxteam.utils.EncryptionUtils;

/**
 *
 * @author razikus
 */
public class SHA256Encoder implements Encoder {

    @Override
    public String encode(String from) {
        return EncryptionUtils.sha256(from);
    }
    
}
