package domain;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.enterprise.context.RequestScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@RequestScoped
public class FileDownloader {

    public StreamedContent getDownloadFileAsStreamContent(final File tempDownloadFile, final String downloadFileName,
            final String contentType) throws IOException {

        byte[] byteFile = Files.readAllBytes(tempDownloadFile.toPath());
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteFile);

        StreamedContent streamedContent = DefaultStreamedContent.builder().name(downloadFileName)
                .contentType(contentType).stream(() -> byteArrayInputStream).build();

        tempDownloadFile.delete();

        return streamedContent;
    }

}
