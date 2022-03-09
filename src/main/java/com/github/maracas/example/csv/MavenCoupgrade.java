package com.github.maracas.example.csv;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Maven co-upgrade representation
 */
public record MavenCoupgrade(
    String libGroupId,
    String libArtifactId,
    String libVersion1,
    String libVersion2,
    String clientGroupId,
    String clientArtifactId,
    String clientVersion1,
    String clientVersion2) {
    /**
     * Class logger
     */
    private static final Logger logger = LogManager.getLogger(MavenCoupgrade.class);
}
