function openChildWindow(pageName) {
    var parentTokenElem = document.getElementById("parent-token");
    window.open(pageName + ".xhtml?openedWindow&token=" + parentTokenElem.value);
}

function closeWindow() {

    var parentTokenElem = document.getElementById("parent-token");
    var url = window.opener.location.href;

    // リクエストパラメータの付与
    url = url.indexOf('?') == -1 ? url : url.substring(0, url.indexOf('?')); // もともと付いているパラメータは削除する
    url += '?closedChildWindow&token=' + parentTokenElem.value

    window.opener.location.href = url;
    window.close();
}
