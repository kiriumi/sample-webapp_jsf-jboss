package application;

import java.time.LocalDate;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import domain.UploadFileService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Model
@Data
@EqualsAndHashCode(callSuper = false)
public class PrimeFacesSamplesBean extends BaseBackingBean {

    @Getter
    @Setter
    private String inputValue;

    @Inject
    private UploadFileService uploadFileService;

    @Getter
    @Setter
    private UploadedFile uploadedFile;

    @Getter
    @Setter
    private LocalDate calendar;

    public String actionAfterConfirm() {
        return null;
    }

    public void uploadFile(final FileUploadEvent event) throws Exception {

        UploadedFile uploadedFile = event.getFile();
        if (uploadedFile != null) {
            uploadFileService.save(uploadedFile, "primefaces");
            getMessageService().setMessage(FacesMessage.SEVERITY_INFO, "ファイルアップロードしたよ：" + uploadedFile.getFileName());
        }
    }

    public void uploadFile() throws Exception {

        if (uploadedFile != null) {
            uploadFileService.save(uploadedFile, "primefaces");
            getMessageService().setMessage(FacesMessage.SEVERITY_INFO, "ファイルアップロードしたよ：" + uploadedFile.getFileName());
        }
    }

}
