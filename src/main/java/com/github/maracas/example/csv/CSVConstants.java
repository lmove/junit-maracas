package com.github.maracas.example.csv;

public class CSVConstants {
    public static final String CSV_SEPARATOR = ",";

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

    private CSVConstants() {}
}
