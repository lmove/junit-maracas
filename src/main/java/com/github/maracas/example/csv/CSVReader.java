package com.github.maracas.example.csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CSVReader {
    /**
     * Class logger
     */
    private static final Logger logger = LogManager.getLogger(CSVReader.class);

    /**
     * Path to the output CSV file
     */
    private final Path csv;

    /**
     * Creates an CSVReader instance.
     */
    public CSVReader(Path csv) {
        assert csv.toFile().exists() : "The input CSV file does not exist";
        this.csv = csv;
    }

    /**
     * Reads a line of the input CSV file and creates a {@link MavenCoupgrade}
     * instance.
     *
     * @param line string representing a CSV line
     * @return {@link MavenCoupgrade} instance
     */
    public MavenCoupgrade readCoupgrade(String line) {
        String[] values = line.split(CSVConstants.CSV_SEPARATOR);
        assert values.length == CSVConstants.INPUT_HEADER.length
            : "The number of values in the CSV line differs from the expected value";

        String libGroupId       = values[0];
        String libArtifactId    = values[1];
        String libVersion1      = values[2];
        String libVersion2      = values[3];
        String clientGroupId    = values[4];
        String clientArtifactId = values[5];
        String clientVersion1   = values[6];
        String clientVersion2   = values[7];

        MavenCoupgrade coupgrade = new MavenCoupgrade(libGroupId, libArtifactId,
            libVersion1, libVersion2, clientGroupId, clientArtifactId,
            clientVersion1, clientVersion2);
        return coupgrade;
    }

    /**
     * Returns a set of {@link MavenCoupgrade} instances gathered from the input
     * CSV file.
     *
     * @return set of {@link MavenCoupgrade} instances
     * @throws FileNotFoundException
     * @throws IOException
     */
    public synchronized Set<MavenCoupgrade> readCoupgrades() throws FileNotFoundException, IOException {
        Set<MavenCoupgrade> coupgrades = new HashSet<MavenCoupgrade>();

        try (FileReader rf = new FileReader(csv.toFile());
            BufferedReader reader = new BufferedReader(rf)) {
            reader.readLine(); // Skip header line
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                MavenCoupgrade coupgrade = readCoupgrade(currentLine);
                coupgrades.add(coupgrade);
            }
        }
        return coupgrades;
    }
}
