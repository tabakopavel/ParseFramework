package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;


public class Propertiess {
    private static FileInputStream fis;
    private static final Properties properties = new Properties();

    private Propertiess() {
    }

    public static void init() {
        try {
            File file = new File(new Propertiess().getClass().getClassLoader().getResource("config.properties").getFile());
            fis = new FileInputStream(file);
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Propertiess.getNamesProp();
    }

    private static void getNamesProp() {
        Enumeration<String> enumeration = (Enumeration<String>) properties.propertyNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            System.setProperty(key, properties.getProperty(key));
        }
    }

}
