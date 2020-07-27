package domain;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.file.UploadedFile;

import context.EnvContext;

@RequestScoped
public class UploadFileService {

    @Inject
    private EnvContext envContext;

    @Inject
    private MessageService messageService;

    private final long maxFileSize = 1000000000L;

    private final List<String> allowedExtentions = Arrays.asList("jpg", "txt");

    public void save(final Part uploadedFile, final String dirname) throws FileUploadException {

        if (uploadedFile == null) {
            messageService.addMessage(FacesMessage.SEVERITY_ERROR, "ファイルを指定してちょ");
            return;
        }

        if (isSizeOver(uploadedFile)) {
            return;
        }

        if (forbiddenExtention(uploadedFile)) {
            return;
        }

        File saveDir = new File(envContext.getUploadFileDir(), dirname);
        File saveFile = new File(saveDir, uploadedFile.getSubmittedFileName());

        try {
            saveUploadFile(uploadedFile.getInputStream(), saveFile);

        } catch (IOException e) {
            throw new FileUploadException("アップロードファイルの取得に失敗したよ");
        }
    }

    private boolean isSizeOver(final Part uploadedFile) {

        if (uploadedFile.getSize() > maxFileSize) {
            messageService.addMessage(FacesMessage.SEVERITY_ERROR, "ファイルサイズを" + maxFileSize + "より小そうしてね");
            return true;
        }

        return false;
    }

    private boolean forbiddenExtention(final Part uploadedFile) {

        String extention = FilenameUtils.getExtension(uploadedFile.getSubmittedFileName());

        if (!allowedExtentions.contains(extention.toLowerCase())) {
            messageService.addMessage(FacesMessage.SEVERITY_ERROR,
                    "アップロードできるファイルは、" + String.join(", ", allowedExtentions) + "だよ");
            return true;
        }

        return false;
    }

    public void save(final UploadedFile uploadedFile, final String dirname) throws FileUploadException {

        File saveDir = new File(envContext.getUploadFileDir(), dirname);
        File saveFile = new File(saveDir, uploadedFile.getFileName());

        try {
            saveUploadFile(uploadedFile.getInputStream(), saveFile);

        } catch (IOException e) {
            throw new FileUploadException("アップロードファイルの取得に失敗したよ");
        }
    }

    private void saveUploadFile(final InputStream uploadFileInputStream, final File saveFile)
            throws FileUploadException {

        if (saveFile.exists()) {
            messageService.addMessage(FacesMessage.SEVERITY_ERROR, "そのファイルはすでに存在するよ");
            return;
        }

        saveFile.getParentFile().mkdirs();

        try {
            Files.copy(uploadFileInputStream, saveFile.toPath());

        } catch (IOException e) {
            throw new FileUploadException("アップロードファイルの保存に失敗したよ");
        }

        messageService.addMessage(FacesMessage.SEVERITY_INFO, "ファイルアップロードしたよ：" + saveFile.getName());
    }

}
