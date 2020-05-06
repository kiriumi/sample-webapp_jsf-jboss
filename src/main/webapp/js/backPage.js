function backPage() {

    var backCount = sessionStorage.getItem('backCount');
    history.back(backCount);
    return false;
}

window.onload = (event) => {

    var backCount = sessionStorage.getItem('backCount');
    var thisLocation = location.href;
    var beforeLocation = sessionStorage.getItem('beforeLocation');

    if (beforeLocation == null) {
        alert("別画面に遷移したよ");
        backCount = -1;

    } else if (thisLocation == beforeLocation) {
        alert("自画面に遷移したよ");
        backCount--;

    } else {
        alert("別画面に遷移したよ");
        backCount = -1;
    }

    sessionStorage.setItem('beforeLocation', thisLocation);
    sessionStorage.setItem('backCount', backCount);
};
