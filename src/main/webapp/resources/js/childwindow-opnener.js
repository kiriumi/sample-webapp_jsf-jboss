function openChildWindow(pageName) {
    var parentTokenElem = document.getElementById("parent-token");
    window.open(pageName + ".xhtml?openedWindow&token=" + parentTokenElem.value);
}

function closeWindow() {

    const closedChildWindowParam = 'closedChildWindow'
    var url = window.opener.location.href;

    if (url.indexOf('?') == -1) {
        url += '?' + closedChildWindowParam;

    } else if (url.indexOf(closedChildWindowParam) == -1) {
        url += '&' + closedChildWindowParam;
    }

    window.opener.location.href = url;
    window.close();
}
