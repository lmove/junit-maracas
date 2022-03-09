package com.github.maracas.example.csv;

/**
 * Set of CSV-related constants used in the project
 */
public class CSVConstants {
    /**
     * CSV file separator
     */
    public static final String CSV_SEPARATOR = ",";

    /**
     * Array of the input CSV file headers
     */
    public static final String[] INPUT_HEADER = new String[] {
        "l_group_id",
        "l_artifact_id",
        "l_version1",
        "l_version2",
        "c_group_id",
        "c_artifact_id",
        "c_version1",
        "c_version2"
    };

    /**
     * Array of the output CSV file headers
     */
    public static final String[] OUTPUT_HEADER = new String[] {
        "l_group_id",
        "l_artifact_id",
        "l_version1",
        "l_version2",
        "c_group_id",
        "c_artifact_id",
        "c_version1",
        "c_version2",
        "bcs",
        "broken_uses"
    };

    /**
     * This class is not supposed to be instantiated.
     */
    private CSVConstants() {}
}
