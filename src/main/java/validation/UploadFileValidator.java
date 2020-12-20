package validation;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.model.file.UploadedFile;

@FacesValidator("uploadFileValidator")
public class UploadFileValidator implements Validator<UploadedFile> {

    @Override
    public void validate(FacesContext context, UIComponent component, UploadedFile file) throws ValidatorException {
        // TODO 自動生成されたメソッド・スタブ

//        FileUpload uploadFile =  (FileUpload) component;
//
//        PrimeApplicationContext appContext =      PrimeApplicationContext.getCurrentInstance(context)
//
//        	FileUploadUtils.isValidFile(context, fileUpload, files);
//        	FileUploadUtils.areValidFiles(context, fileUpload, files);
//        	FileUploadUtils.areValidFiles(context, fileUpload, files);
//        	FileUploadUtils.areValidFiles(context, fileUpload, files);
//
//        Map<String, Object> attrs = component.getAttributes();
//
//        if (file.getSize() < sizeLimit
//                && file.getFileName().matches(allowTypes)
//                && file.getContentType().matches(acceptContentTypes)) {
//            return true;
//        }

    }

}
