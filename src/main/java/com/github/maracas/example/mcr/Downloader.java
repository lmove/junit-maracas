package com.github.maracas.example.mcr;

import java.io.IOException;
import java.nio.file.Path;

import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.artifact.DefaultArtifact;

import nl.cwi.swat.aethereal.AetherDownloader;

/**
 * Maven Central Reporitory (MCR) artifact downloader based on Aethereal.
 */
public class Downloader {
    /**
     * MCR artifact downloader
     */
    private final AetherDownloader downloader;

    /**
     * Creates a {@link Downloader} instance.
     *
     * @param qps  queries per second
     */
    public Downloader(int qps) {
        this.downloader = new AetherDownloader(qps);
    }

    /**
     * Downloads the JAR file of an artifact from MCR and returns its path in
     * the file system.
     *
     * @param groupId     group ID of the artifact to download
     * @param artifactId  artifact ID of the artifact to download
     * @param version     version of the artifact to download
     * @return path to the downloaded JAR file
     * @throws IOException
     */
    public Path downloadJar(String groupId, String artifactId, String version) throws IOException {
        return downloadArtifact(groupId, artifactId, version, "jar");
    }

    /**
     * Downloads the source code of an artifact from MCR and returns its path in
     * the file system.
     *
     * @param groupId     group ID of the artifact to download
     * @param artifactId  artifact ID of the artifact to download
     * @param version     version of the artifact to download
     * @return path to the downloaded source code
     * @throws IOException
     */
    public Path downloadSources(String groupId, String artifactId, String version) throws IOException {
        return downloadArtifact(groupId, artifactId, version, "sources");
    }

    /**
     * Downloads an artifact from MCR and returns its path in the file system.
     *
     * @param groupId     group ID of the artifact to download
     * @param artifactId  artifact ID of the artifact to download
     * @param version     version of the artifact to download
     * @param extension   extension of the artifact (e.g. jar, sources)
     * @return path to the downloaded artifact
     * @throws IOException
     */
    public Path downloadArtifact(String groupId, String artifactId, String version, String extension) throws IOException {
        DefaultArtifact defArtifact = new DefaultArtifact(groupId, artifactId, extension, version);
        Artifact artifact = downloader.downloadArtifact(defArtifact);

        if (artifact == null)
            throw new IOException("The Maven artifact has not been found on MCR");
        else if (!artifact.getFile().exists())
            throw new IOException("The Maven artifact has not been downloaded");
        else
            return artifact.getFile().toPath();
    }
}
