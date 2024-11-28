package org.FremeWork_Playwright.Config;

import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static Properties properties = new Properties();

    static {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Arquivo config.properties não encontrado no classpath!");
            }
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar o arquivo config.properties: " + e.getMessage(), e);
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url", "https://default.url/");
    }

    public static int getTimeout() {
        return Integer.parseInt(properties.getProperty("timeout", "3000"));
    }
}
