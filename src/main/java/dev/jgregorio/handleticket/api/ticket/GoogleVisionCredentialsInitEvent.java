package dev.jgregorio.handleticket.api.ticket;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class GoogleVisionCredentialsInitEvent {

    private static final Logger logger = LoggerFactory.getLogger(GoogleVisionCredentialsInitEvent.class);

    @EventListener(ApplicationReadyEvent.class)
    public void initGoogleCredentials() {
        String filename = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
        String filecontent = System.getenv("GOOGLE_CREDENTIALS");
        if (StringUtils.isNoneBlank(filename) && StringUtils.isNoneBlank(filecontent)) {
            try {
                Path path = Files.write(Paths.get(filename), filecontent.getBytes());
                logger.info("file created " + path);
            } catch (IOException e) {
                logger.error("Cannot write credentials into " + filename);
            }
        }
    }
}