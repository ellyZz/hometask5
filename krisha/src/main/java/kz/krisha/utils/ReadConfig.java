package kz.krisha.utils;

import kz.krisha.config.Config;
import kz.krisha.exception.ReadFromFileException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

    private ReadConfig() {
    }

    private static Properties properties = new Properties();

    public static String getProperty(String propertyKey) {

        String propertyFilePath = "src/test/resources/config.properties";

        try {
            FileInputStream inStream = new FileInputStream(propertyFilePath);
            properties.load(inStream);
            inStream.close();
            return properties.getProperty(propertyKey);
        } catch (IOException e) {
            throw new ReadFromFileException(e);
        }
    }

    public static Config getConfig() {
        Config config = new Config();
        config.setStartUrl(ReadConfig.getProperty("startUrl"));
        config.setPhoneNumber(ReadConfig.getProperty("phoneNumber"));
        config.setPassword(ReadConfig.getProperty("password"));
        return config;
    }
}
