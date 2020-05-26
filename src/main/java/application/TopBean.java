package application;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;

import org.primefaces.model.StreamedContent;

import domain.FileDownloader;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * ログインページクラス
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

    @Getter
    private StreamedContent downloadFile;

    public void viewAction() {

        String loginSuccessMessage = (String) getFlash().get("loginSccessMessage");
        getMessageService().setMessage(FacesMessage.SEVERITY_INFO, loginSuccessMessage);

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

    public String goSignupPage() {
        return redirect("signup");
    }

    public String goRestClientPage() {
        return redirect("rest-client");
    }

    public void makeDownloadFile() throws IOException {

        File newFile = new File("C:/Users/kengo/git/sample-webapp_jsf-jboss/misc/sample.txt");
        newFile.createNewFile();
        downloadFile = fileDownloader.getDownloadFileAsStreamContent(newFile, "hoge.txt", "text/plain");
    }

}
