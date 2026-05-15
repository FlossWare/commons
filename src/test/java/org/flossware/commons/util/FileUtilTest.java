package org.flossware.commons.util;

import org.flossware.commons.io.FileException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilTest {

    @TempDir
    Path tempDir;

    @Test
    void testGetFileInputStream_withValidFile() throws IOException {
        Path testFile = tempDir.resolve("test.txt");
        Files.writeString(testFile, "test content");

        FileInputStream fis = FileUtil.getFileInputStream(testFile.toFile());
        assertNotNull(fis);
        fis.close();
    }

    @Test
    void testGetFileInputStream_withNonExistentFile() {
        File nonExistent = new File(tempDir.toFile(), "nonexistent.txt");
        assertThrows(FileException.class, () ->
            FileUtil.getFileInputStream(nonExistent));
    }

    @Test
    void testGetFileInputStream_withNullFile() {
        assertThrows(NullPointerException.class, () ->
            FileUtil.getFileInputStream((File) null));
    }

    @Test
    void testGetFileInputStream_withFileName() throws IOException {
        Path testFile = tempDir.resolve("test2.txt");
        Files.writeString(testFile, "test content");

        FileInputStream fis = FileUtil.getFileInputStream(testFile.toString());
        assertNotNull(fis);
        fis.close();
    }

    @Test
    void testGetFileInputStream_withNullFileName() {
        assertThrows(IllegalArgumentException.class, () ->
            FileUtil.getFileInputStream((String) null));
    }

    @Test
    void testGetFileInputStream_withEmptyFileName() {
        assertThrows(IllegalArgumentException.class, () ->
            FileUtil.getFileInputStream(""));
    }

    @Test
    void testEnsureFileExists_withValidFile() throws IOException {
        Path testFile = tempDir.resolve("exists.txt");
        Files.writeString(testFile, "exists");

        File result = FileUtil.ensureFileExists(testFile.toFile());
        assertEquals(testFile.toFile(), result);
    }

    @Test
    void testEnsureFileExists_withNonExistentFile() {
        File nonExistent = new File(tempDir.toFile(), "nonexistent.txt");
        assertThrows(IllegalArgumentException.class, () ->
            FileUtil.ensureFileExists(nonExistent));
    }

    @Test
    void testEnsureFileExists_withNullFile() {
        assertThrows(NullPointerException.class, () ->
            FileUtil.ensureFileExists((File) null));
    }

    @Test
    void testEnsureFileExists_withFileName() throws IOException {
        Path testFile = tempDir.resolve("exists2.txt");
        Files.writeString(testFile, "exists");

        File result = FileUtil.ensureFileExists(testFile.toString());
        assertEquals(testFile.toFile(), result);
    }

    @Test
    void testEnsureFileExists_withNullFileName() {
        assertThrows(IllegalArgumentException.class, () ->
            FileUtil.ensureFileExists((String) null));
    }

    @Test
    void testEnsureFileExists_withEmptyFileName() {
        assertThrows(IllegalArgumentException.class, () ->
            FileUtil.ensureFileExists(""));
    }
}
