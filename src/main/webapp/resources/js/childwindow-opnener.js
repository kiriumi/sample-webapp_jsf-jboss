function openChildWindow(url, windowName, windowFeature) {

    var namespace = Math.random().toString(32).substring(2);
    var childWindow =  window.open(url +  '?childTokenNamespace=' + namespace ,windowName , windowFeature);
    childWindow.focus();

    return childWindow;
}
