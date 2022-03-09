package com.github.maracas.example;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.maracas.example.csv.CSVReader;
import com.github.maracas.example.csv.CSVWriter;
import com.github.maracas.example.csv.MavenCoupgrade;
import com.github.maracas.example.mcr.Downloader;

/**
 * Motivating example pipeline
 */
public class Pipeline {
    /**
     * Class logger
     */
    private static final Logger logger = LogManager.getLogger(Pipeline.class);

    /**
     * Relative path to the input CSV file
     */
    public static final String INPUT_CSV = "src/main/resources/clean-slf4j-coupgrades.csv";

    /**
     * Relative path to the output CSV file
     */
    public static final String OUTPUT_CSV = "src/main/resources/output.csv";

    /**
     * Main method of the pipeline.
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            // Write header
            CSVWriter writer = new CSVWriter(Paths.get(OUTPUT_CSV));
            writer.writeDefaultHeader();

            // Gather coupgrades
            CSVReader reader = new CSVReader(Paths.get(INPUT_CSV));
            Set<MavenCoupgrade> coupgrades = reader.readCoupgrades();

            // Download artifacts from MCR
            Downloader downloader = new Downloader(15);
            for (MavenCoupgrade coupgrade : coupgrades) {
                try {
                    Path lib1 = downloader.downloadJar(
                        coupgrade.libGroupId(),
                        coupgrade.libArtifactId(),
                        coupgrade.libVersion1());
                    Path lib2 = downloader.downloadJar(
                        coupgrade.libGroupId(),
                        coupgrade.libArtifactId(),
                        coupgrade.libVersion2());
                    Path client1 = downloader.downloadSources(
                        coupgrade.clientGroupId(),
                        coupgrade.clientArtifactId(),
                        coupgrade.clientVersion1());
                    Path client2 = downloader.downloadSources(
                        coupgrade.clientGroupId(),
                        coupgrade.clientArtifactId(),
                        coupgrade.clientVersion2());
                } catch (Exception e) {
                    continue;
                }
                logger.info("Downloaded");
            }
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
