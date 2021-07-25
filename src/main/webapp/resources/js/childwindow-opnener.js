function openChildWindow(url, windowName, windowFeature) {

    var parentToken = document.getElementById("token").value;
    var namespace = Math.random().toString(32).substring(2);

    var childWindow =  window.open(url + '?token=' + parentToken　+  '&childTokenNamespace=' + namespace ,windowName , windowFeature);
    childWindow.focus(); // モダンブラウザはfocusが動作しないので留意すること

    return childWindow;
}
