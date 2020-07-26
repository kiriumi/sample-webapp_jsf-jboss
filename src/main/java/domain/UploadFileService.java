package domain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.servlet.http.Part;

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

    public void save(final Part uploadedFile, final String dirname) throws IOException {

        if (uploadedFile == null) {
            messageService.setMessage(FacesMessage.SEVERITY_ERROR, "ファイルを指定してちょ");
            return;
        }

        if (isSizeOver(uploadedFile)) {
            return;
        }

        if (forbiddenExtention(uploadedFile)) {
            return;
        }

        File savaDir = new File(envContext.getUploadFileDir(), dirname);
        File saveFile = new File(savaDir, uploadedFile.getSubmittedFileName());

        if (saveFile.exists()) {
            messageService.setMessage(FacesMessage.SEVERITY_ERROR, "そのファイルはすでに存在するよ");
            return;
        }

        saveFile.getParentFile().mkdirs();
        Files.copy(uploadedFile.getInputStream(), saveFile.toPath());
        messageService.setMessage(FacesMessage.SEVERITY_INFO,
                "ファイルアップロードしたよ：" + uploadedFile.getSubmittedFileName());
    }

    private boolean isSizeOver(final Part uploadedFile) {

        if (uploadedFile.getSize() > maxFileSize) {
            messageService.setMessage(FacesMessage.SEVERITY_ERROR, "ファイルサイズを" + maxFileSize + "より小そうしてね");
            return true;
        }

        return false;
    }

    private boolean forbiddenExtention(final Part uploadedFile) {

        String extention = FilenameUtils.getExtension(uploadedFile.getSubmittedFileName());

        if (!allowedExtentions.contains(extention.toLowerCase())) {
            messageService.setMessage(FacesMessage.SEVERITY_ERROR,
                    "アップロードできるファイルは、" + String.join(", ", allowedExtentions) + "だよ");
            return true;
        }

        return false;
    }

    public void save(final UploadedFile uploadedFile, final String dirname) throws Exception {

        File savaDir = new File(envContext.getUploadFileDir(), dirname);
        Files.copy(uploadedFile.getInputStream(), new File(savaDir, uploadedFile.getFileName()).toPath());
    }

}
