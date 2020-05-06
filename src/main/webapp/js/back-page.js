function backPage() {

    var backCount = sessionStorage.getItem('backCount');
    history.go(backCount * -1);
    return false;
}

$('window').on('load', function() {

    var backCount = sessionStorage.getItem('backCount');
    var thisLocation = location.href;
    var beforeLocation = sessionStorage.getItem('beforeLocation');

    if (beforeLocation == null) {
        backCount = 1;

    } else if (thisLocation == beforeLocation) {
        backCount++;

    } else {
        backCount = 1;
    }

    sessionStorage.setItem('beforeLocation', thisLocation);
    sessionStorage.setItem('backCount', backCount);
});
