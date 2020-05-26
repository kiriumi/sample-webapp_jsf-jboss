package domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@RequestScoped
public class FileDownloader {

    private File tempDownloadFile;

    public StreamedContent getDownloadFileAsStreamedContent(final File tempDownloadFile, final String downloadFileName,
            final String contentType) throws FileNotFoundException {

        this.tempDownloadFile = tempDownloadFile;

        InputStream inputStream = new FileInputStream(tempDownloadFile.getAbsolutePath());
        StreamedContent streamedContent = new DefaultStreamedContent(inputStream, contentType, downloadFileName);

        return streamedContent;
    }

    @PreDestroy
    public void deleteTempDownloadFiles() {
        tempDownloadFile.delete();
    }
}
