package org.kx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaRequest {
    private static final Logger logger = LoggerFactory.getLogger(KafkaRequest.class);

    private int apiKey;
    private int apiVersion;

    private int correlationId;

    private String clientId;

    public KafkaRequest(int apiKey, int apiVersion, int correlationId, String clientId) {
        logger.atDebug().setMessage("Creating new KafkaRequest")
                .addKeyValue("apiKey", apiKey)
                .addKeyValue("apiVesion", apiVersion)
                .addKeyValue("correlationID", correlationId)
                .addKeyValue("clientId", clientId)
                .log();

        this.apiKey = apiKey;
        this.apiVersion = apiVersion;
        this.correlationId = correlationId;
        this.clientId = clientId;
    }
    public KafkaRequest(String apiKey, String apiVersion) {
        logger.atDebug().setMessage("Creating new KafkaRequest")
                .addKeyValue("apiKey", apiKey)
                .addKeyValue("apiVesion", apiVersion)
                .log();

        this.apiKey = Integer.parseInt(apiKey);
        this.apiVersion = Integer.parseInt(apiVersion);
    }
}
