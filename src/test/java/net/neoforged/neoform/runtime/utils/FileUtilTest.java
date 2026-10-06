package net.neoforged.neoform.runtime.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileUtilTest {
    @TempDir
    Path tempDir;

    @Test
    void testSafeLinkOrCopyReplacesDestinationWithLink() throws IOException {
        var source = tempDir.resolve("source.jar");
        var destination = tempDir.resolve("destination.jar");
        Files.writeString(source, "new");
        Files.writeString(destination, "old");

        FileUtil.safeLinkOrCopy(source, destination);

        assertEquals("new", Files.readString(destination));
        assertTrue(Files.isSameFile(source, destination));
    }
}
