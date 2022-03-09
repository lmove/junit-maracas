package com.github.maracas.example;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.maracas.example.csv.OutputCSVWriter;

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
    public static final String INPUT_CSV = "src/main/resources/input.csv";

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
            OutputCSVWriter outputHandler = new OutputCSVWriter(Paths.get(OUTPUT_CSV));
            outputHandler.writeDefaultHeader();
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
