/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.approxteam.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author razikus
 */
public final class PropertiesBuilder {
    
    public static Properties getProperties(Class forClass) {
        
        Properties toLoad = new Properties();
        
        try {
            toLoad.load(new FileInputStream(getClassPropertiesPath(forClass)));
        } catch (IOException ex) {
            writeProperties(toLoad, forClass);
            return toLoad;
            
        }
        
        return toLoad;
    }
    
    public static void writeProperties(Properties prop, Class forClass){
        OutputStream out = null;
        try {
            out = new FileOutputStream(getClassPropertiesPath(forClass));
            PropertyComment annotation = ((PropertyComment)forClass.getAnnotationsByType(PropertyComment.class)[0]);
            String adnotations = annotation.className().getCanonicalName() + "\n" + annotation.desc();
            
            prop.store(out, adnotations);
        } catch (FileNotFoundException ex) {
            System.exit(1);
        } catch (IOException ex) {
            System.exit(1);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(PropertiesBuilder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static String getClassPropertiesPath(Class forClass) {
        return forClass.getName().replaceAll("\\.", "-") + ".properties";
    }
}
