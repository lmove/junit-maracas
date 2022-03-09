package com.github.maracas.example.csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class in charge of writing the output CSV of the motivating example.
 */
public class CSVWriter {
    /**
     * Class logger
     */
    private static final Logger logger = LogManager.getLogger(CSVWriter.class);

    /**
     * Path to the output CSV file
     */
    private final Path csv;

    /**
     * Creates an CSVWriter instance. The constructor also creates the
     * output file in the file system and all parent directories.
     *
     * @param csv {@link Path} to the output CSV file
     * @throws IOException
     */
    public CSVWriter(Path csv) throws IOException {
        this.csv = csv;
        createNewFile();
    }

    /**
     * Creates the CSV output file in the file system if it does not exist, and
     * all its parent directories.
     *
     * @throws IOException
     */
    private void createNewFile() throws IOException {
        File file = csv.toFile();
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    /**
     * Erases the content of the output CSV file.
     *
     * @throws IOException
     */
    public void eraseFile() throws IOException {
        writeLine("", false);
    }

    /**
     * Writes the default header of the output file. This header includes the
     * following labels:
     *
     * @throws IOException
     */
    public void writeDefaultHeader() throws IOException {
        writeHeader(CSVConstants.OUTPUT_HEADER);
    }

    /**
     * Writes the header of the output CSV file given an array of labels.
     *
     * @param labels  array of labels to use as header in the output CSV file
     * @throws IOException
     */
    public void writeHeader(String[] labels) throws IOException {
        String header = String.join(",", labels) + System.lineSeparator();
        writeLine(header);
    }

    /**
     * Writes a new line in the output CSV file given a string. The new line is
     * appended to existing content.
     *
     * @param line  string to be appended to the output CSV file
     * @throws IOException
     */
    private synchronized void writeLine(String line) throws IOException {
        writeLine(line, true);
    }

    /**
     * Writes a new line in the output CSV file given a string.
     *
     * @param line    string to be written in the output CSV file
     * @param append  true if the content should be appended without erasing
     *                previous content, false otherwise
     * @throws IOException
     */
    private synchronized void writeLine(String line, boolean append) throws IOException {
        try (FileWriter fw = new FileWriter(csv.toFile(), append);
            BufferedWriter writer = new BufferedWriter(fw)) {
            writer.append(line);
        }
    }
}
