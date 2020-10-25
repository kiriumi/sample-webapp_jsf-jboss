package application;

import java.time.LocalDate;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import org.apache.commons.fileupload.FileUploadException;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import domain.DialogMessage;
import domain.DialogMessageService;
import domain.UploadFileService;
import lombok.Getter;
import lombok.Setter;

@Model
public class PrimeFacesSamplesBean extends BaseBackingBean {

    @Inject
    private DialogMessageService dialogMessage;

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

    public String ajaxButton() {
        messageService().addMessage(FacesMessage.SEVERITY_INFO, "Ajaxボタンだよ");
        return null;
    }

    public String nonAjaxButton() {
        messageService().addMessage(FacesMessage.SEVERITY_INFO, "普通のボタンだよ");
        return null;
    }

    public String doException() {
        throw new NullPointerException("意図的に例外を発生");
    }

    public void uploadFile(final FileUploadEvent event) throws FileUploadException {

        UploadedFile uploadedFile = event.getFile();
        if (uploadedFile != null) {
            uploadFileService.save(uploadedFile, "primefaces");
            messageService().addMessage(FacesMessage.SEVERITY_INFO, "ファイルアップロードしたよ：" + uploadedFile.getFileName());
        }
    }

    public void uploadFile() throws FileUploadException {

        if (uploadedFile != null) {
            uploadFileService.save(uploadedFile, "primefaces");
            messageService().addMessage(FacesMessage.SEVERITY_INFO, "ファイルアップロードしたよ：" + uploadedFile.getFileName());
        }
    }

    public String showMessageDialog() {

        // メッセージは１つしか表示できない
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "メッセージ", "おためしメッセージ");
        PrimeFaces.current().dialog().showMessageDynamic(message);
        return null;
    }

    public String showMessageAsDialog() {

        dialogMessage.addMessage(DialogMessage.Level.INFO, "情報メッセージ");
        dialogMessage.addMessage(DialogMessage.Level.WARN, "警告メッセージ");
        dialogMessage.addMessage(DialogMessage.Level.ERROR, "エラーメッセージ");

        dialogMessage.addMessage(DialogMessage.Level.INFO, "ほげ");
        dialogMessage.addMessage(DialogMessage.Level.INFO, "ふー");
        dialogMessage.addMessage(DialogMessage.Level.INFO, "ばー");

        return null;
    }

}
