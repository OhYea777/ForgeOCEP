package examplemod.examplemod2.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExampleMod2API {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static void init() {
        // Some example code
        LOGGER.info("HELLO FROM API INIT");
    }

}
