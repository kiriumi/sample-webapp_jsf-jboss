package domain;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;

import org.apache.commons.io.FileUtils;
import org.primefaces.model.file.UploadedFile;

@SessionScoped
public class UploadedFileHolder implements Serializable {

    private Map<String, File> uploadedFileMap = new HashMap<>();

    private final File dir;

    private final File tempDir;

    public UploadedFileHolder(File uploadDir, File tempUploadDir) throws IOException {

        if (uploadDir == null || tempUploadDir == null) {
            throw new IllegalArgumentException();
        }

        this.dir = uploadDir;
        this.tempDir = tempUploadDir;
        dir.mkdirs();
        tempDir.mkdirs();
    }

    public void put(UploadedFile uploadedFile) throws IOException {

        if (tempDir == null) {
            throw new IllegalStateException();
        }

        File file = new File(tempDir, uploadedFile.getFileName());
        Files.copy(uploadedFile.getInputStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        uploadedFileMap.put(file.getName(), file);
    }

    public File get(String filename) {
        return uploadedFileMap.get(filename);
    }

    public List<File> getFilesAsList() {
        return new ArrayList<>(uploadedFileMap.values());
    }

    public void remove(String filename) {
        File file = uploadedFileMap.get(filename);
        file.delete();
        uploadedFileMap.remove(filename);
    }

    public void move() throws IOException {

        if (dir == null) {
            throw new IllegalStateException();
        }

        for (File file : getFilesAsList()) {
            Files.move(file.toPath(), new File(dir, file.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public boolean isEmpty() {
        return uploadedFileMap.isEmpty();
    }

    public void clear() throws IOException {

        uploadedFileMap.clear();
        if (tempDir != null) {
            FileUtils.deleteDirectory(tempDir);
        }
    }

    @PreDestroy
    public void finalize() throws IOException {
        clear();
    }
}
