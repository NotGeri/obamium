package dev.geri.obamium;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Obamium implements ModInitializer {

    private static final Logger logger = LoggerFactory.getLogger("Obamium");

    @Override
    public void onInitialize() {
        logger.info("your mom");
    }

    public static Logger getLogger() {
        return logger;
    }
}
