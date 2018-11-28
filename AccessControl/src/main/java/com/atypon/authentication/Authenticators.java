package com.atypon.authentication;


import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class Authenticators {
    private static final ClassLoader classLoader = Authenticators.class.getClassLoader();
    private static final File file = new File(classLoader.getResource("config/licencesAuthenticators.properties").getFile());
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(new FileReader(file));
        } catch (Exception e) {
            throw new Error("Can not load settings file: " + e.getMessage());
        }
    }

    private static Class<? extends Authenticator> getAuthenticatorClass(String licenceName) throws Exception {
        String className = properties.getProperty(licenceName);
        return (Class<? extends Authenticator>) Class.forName(className);

    }

    // Suppresses default constructor, ensuring non-instantiability
    public static Authenticator getAuthenticator(String licenceName) {
        try {
            return getAuthenticatorClass(licenceName).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
