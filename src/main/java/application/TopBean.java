package application;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.servlet.http.Part;

import org.primefaces.model.StreamedContent;

import domain.FileDownloader;
import domain.UploadFileService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * トップ画面クラス
 *
 * @author Kengo
 *
 */
@Model
@Data
@EqualsAndHashCode(callSuper = false)
public class TopBean extends BaseBackingBean {

    @Inject
    SecurityContext securityContext;

    private String name;

    private boolean adminRole;

    private boolean userRole;

    private int value1 = 0;

    private int value2 = 0;

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

        if (principal == null) {
            return;
        }

        this.name = principal.getName();
        this.adminRole = securityContext.isCallerInRole("admin");
        this.userRole = securityContext.isCallerInRole("user");
    }

    public String sum() {
        this.sumedValue = value1 + value2;
        return null;
    }

    public void makeDownloadFile() throws IOException {

        File newFile = new File("C:/Users/kengo/git/sample-webapp_jsf-jboss/misc/sample.txt");
        newFile.createNewFile();
        downloadFile = fileDownloader.getDownloadFileAsStreamContent(newFile, "hoge.txt", "text/plain");
    }

    public String uploadFile() throws IOException {
        uploadFileService.save(uploadedFile, "jsf");
        return null;
    }

}
