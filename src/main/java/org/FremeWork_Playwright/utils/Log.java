package org.FremeWork_Playwright.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

    private static final String LOG_FILE = "log-report.txt";

    public static void info(String message) {
        String logMessage = "[INFO] " + getTimeStamp() + " - " + message;
        System.out.println(logMessage);
        writeToFile(logMessage);
    }

    public static void error(String message) {
        String logMessage = "[ERROR] " + getTimeStamp() + " - " + message;
        System.err.println(logMessage);
        writeToFile(logMessage);
    }

    private static String getTimeStamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private static void writeToFile(String message) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(message + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("[ERROR] Falha ao escrever no arquivo de log: " + e.getMessage());
        }
    }
}
