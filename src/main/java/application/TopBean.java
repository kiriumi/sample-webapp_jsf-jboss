package application;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileUploadException;
import org.primefaces.model.StreamedContent;

import context.EnvContext;
import domain.FileDownloader;
import domain.UploadFileService;
import lombok.Getter;
import lombok.Setter;

/**
 * トップ画面クラス
 *
 * @author Kengo
 *
 */
@Model
public class TopBean extends BaseBackingBean {

    @Inject
    SecurityContext securityContext;

    @Inject
    EnvContext envContext;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private boolean adminRole;

    @Getter
    @Setter
    private boolean userRole;

    @Getter
    @Setter
    private int value1 = 0;

    @Getter
    @Setter
    private int value2 = 0;

    @Getter
    @Setter
    private int sumedValue = 0;

    @Inject
    private FileDownloader fileDownloader;

    @Inject
    private UploadFileService uploadFileService;

    @Getter
    private StreamedContent downloadFile;

    @Getter
    @Setter
    private Part uploadedFile;

    public void viewAction() {

        Principal principal = securityContext.getCallerPrincipal();
        this.name = principal.getName();
        this.adminRole = securityContext.isCallerInRole("admin");
        this.userRole = securityContext.isCallerInRole("user");
    }

    public String sum() {
        this.sumedValue = value1 + value2;
        return null;
    }

    public void makeDownloadFile() throws IOException {

        File newFile = new File(envContext.getMiscDir(), "sample.txt");

        try {
            newFile.createNewFile();
            downloadFile = fileDownloader.getDownloadFileAsStreamContent(newFile, "hoge.txt", "text/plain");

        } catch (IOException e) {
            throw new IOException("ダウンロードファイルの作成に失敗したよ", e);
        }
    }

    public String uploadFile() throws FileUploadException {
        uploadFileService.save(uploadedFile, "jsf");
        return null;
    }

}
