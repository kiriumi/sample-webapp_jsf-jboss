package application;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.fileupload.FileUploadException;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFileWrapper;
import org.primefaces.model.file.UploadedFiles;

import context.EnvContext;
import domain.DialogMessage;
import domain.DialogMessageService;
import domain.UploadFileService;
import domain.UploadedFileHolder;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class PrimeFacesSamplesBean extends BaseBackingBean implements Serializable {

    @Inject
    private DialogMessageService dialogMessage;

    @Getter
    @Setter
    private String inputValue;

    @Inject
    private UploadFileService uploadFileService;

    @Getter
    @Setter
    private List<UploadedFileWrapper> uploadedFiles;

    @Getter
    @Setter
    private UploadedFiles uploadedMultipleFiles;

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

    public void uploadFileByEvent(final FileUploadEvent event) throws FileUploadException {

        UploadedFile file = event.getFile();
        if (file == null) {
            return;
        }

        uploadFileService.save(file, "primefaces");
        messageService().addMessage(FacesMessage.SEVERITY_INFO,
                "ファイルアップロードしたよ" + file.getFileName().toString());
    }

    @Inject
    EnvContext envContext;

    private UploadedFileHolder holder;

    public void addUploadFiles(final FileUploadEvent event) throws FileUploadException, IOException {

        File rootDir = envContext.getUploadFileDir();

        if (holder == null) {
            this.holder = new UploadedFileHolder(new File(rootDir, "parent"), new File(rootDir, "temp"));
        }

        UploadedFile file = event.getFile();
        if (file == null) {
            return;
        }
        holder.put(file);
    }

    public String saveUploadFiles() throws FileUploadException, IOException {

        if (holder.isEmpty()) {
            messageService().addMessage(FacesMessage.SEVERITY_ERROR, "アップロードするファイルがないよ");
            return null;
        }
        holder.move();
        return null;
    }

    public String clearUploadFiles() throws FileUploadException, IOException {
        holder.clear();
        return null;
    }

    public void uploadMultipleFiles() throws FileUploadException {

        if (uploadedMultipleFiles == null) {
            messageService().addMessage(FacesMessage.SEVERITY_ERROR, "アップロードするファイルがないよ");
            return;
        }

        for (UploadedFile file : uploadedMultipleFiles.getFiles()) {
            logger().debug(file.getFileName());
            uploadFileService.save(file, "primefaces");
        }
        messageService().addMessage(FacesMessage.SEVERITY_INFO,
                "ファイルアップロードしたよ" + uploadedMultipleFiles.getFiles().toString());
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
