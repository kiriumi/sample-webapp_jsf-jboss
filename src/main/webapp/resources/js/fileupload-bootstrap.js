/**
 * BootStrapでのファイルアップロード機能の見栄えを良くする
 *
 * @param uploadFileId
 *            ファイルアップロード項目ID
 */
// アップロードファイル選択時に、ラベルにアップロードファイル名を表示する
bsCustomFileInput.init();

// 取消ボタン押下時に、アップロードファイル名を削除する
function deleteUploadFile(uploadFileId) {

    if (uploadFileId == null || uploadFileId == '') {
        return;
    }

    var elem = document.getElementById(uploadFileId);
    elem.value = '';
    elem.dispatchEvent(new Event('change'));
}
