function openChildWindow(pageName) {
    window.open(pageName + ".xhtml");
}
function closeWindow() {
    window.close();
    window.opener.location.reload();
}
