bsCustomFileInput.init();

function deleteUploadFile(uploadFileId) {

    if (uploadFileId == null || uploadFileId == '') {
        return;
    }

    var elem = document.getElementById(uploadFileId);
    elem.value = '';
    //elem.dispatchEvent(new Event('change'));
}
