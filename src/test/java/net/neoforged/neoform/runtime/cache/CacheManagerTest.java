package net.neoforged.neoform.runtime.cache;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CacheManagerTest {
    @TempDir
    Path tempDir;

    @Test
    void testOnlyIntermediateResultsCanBeShared() throws Exception {
        var homeDir = tempDir.resolve("home");
        var workspacesDir = tempDir.resolve("workspaces");
        try (var cacheManager = new CacheManager(homeDir, null, workspacesDir)) {
            assertTrue(cacheManager.isIntermediateResult(homeDir.resolve("intermediate_results/recompile_abc_output.jar")));
            assertFalse(cacheManager.isIntermediateResult(homeDir.resolve("artifacts/minecraft_26.3_client.jar")));
            assertFalse(cacheManager.isIntermediateResult(workspacesDir.resolve("20261006-120000_recompile/output.jar")));
        }
    }
}
