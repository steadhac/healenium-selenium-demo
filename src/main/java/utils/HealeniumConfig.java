package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Healenium configuration utility class.
 * Provides programmatic control over Healenium self-healing features.
 * Allows dynamic toggling of healing capabilities during test execution.
 * 
 * @author Carolina Steadham
 * @version 1.0
 */
public class HealeniumConfig {
    
    /** Path to Healenium configuration file */
    private static final String PROPERTIES_FILE = "src/test/resources/healenium.properties";
    
    /**
     * Enables Healenium self-healing capability.
     * Updates healenium.properties with heal-enabled=true.
     */
    public static void enableHealing() {
        setHealingEnabled(true);
    }
    
    /**
     * Disables Healenium self-healing capability.
     * Updates healenium.properties with heal-enabled=false.
     */
    public static void disableHealing() {
        setHealingEnabled(false);
    }
    
    /**
     * Checks if self-healing is currently enabled.
     * 
     * @return true if healing is enabled, false otherwise
     */
    public static boolean isHealingEnabled() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
            props.load(fis);
            return Boolean.parseBoolean(props.getProperty("heal-enabled", "true"));
        } catch (IOException e) {
            System.err.println("Error reading healenium.properties: " + e.getMessage());
            return true; // Default to enabled
        }
    }
    
    /**
     * Sets healing enabled or disabled state.
     * Updates healenium.properties file with new value.
     * 
     * @param enabled true to enable healing, false to disable
     */
    private static void setHealingEnabled(boolean enabled) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
            props.load(fis);
            props.setProperty("heal-enabled", String.valueOf(enabled));
            
            try (FileOutputStream fos = new FileOutputStream(PROPERTIES_FILE)) {
                props.store(fos, "Healenium Configuration");
                System.out.println("Self-healing " + (enabled ? "ENABLED" : "DISABLED"));
            }
        } catch (IOException e) {
            System.err.println("Error updating healenium.properties: " + e.getMessage());
        }
    }
}
