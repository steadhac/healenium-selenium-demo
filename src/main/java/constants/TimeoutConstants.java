package constants;

/**
 * Timeout constants for WebDriver operations.
 * Provides centralized timeout values used throughout the test framework.
 * Ensures consistent wait times across all test cases.
 * 
 * @author Carolina Steadham
 * @version 1.0
 */
public class TimeoutConstants {
    
    /** Private constructor to prevent instantiation */
    private TimeoutConstants() {
        throw new IllegalStateException("Constants class");
    }
    
    /** Short wait timeout in seconds - for quick operations */
    public static final int SHORT_WAIT = 5;
    
    /** Medium wait timeout in seconds - standard wait operations */
    public static final int MEDIUM_WAIT = 10;
    
    /** Long wait timeout in seconds - for slow-loading operations */
    public static final int LONG_WAIT = 20;
    
    /** Page load timeout in seconds - maximum time to wait for page loads */
    public static final int PAGE_LOAD_TIMEOUT = 30;
    
    /** Implicit wait in seconds - default wait for all element searches */
    public static final int IMPLICIT_WAIT = 10;
}
