function disableEnter(event) {
    return disableKeyPush(event, 13);
}

function disableKey(event, ...keyCodes) {
    var isMatch = false;
    keyCodes.some(function (keyCode) {
        return isMatch = (event.which && event.which === keyCode) || (event.keyCode && event.keyCode === keyCode);
    });
    return !isMatch;
}

function replaceText(element, regex, replacement) {
    var replaced = $(element).val().replace(regex, replacement);
    if ($(element).val() !== replaced) {
        $(element).val(replaced);
    }
}

function removeLineFeed(element) {
    replaceText(element, /\n/g , '')
}

function removeHyphen(element) {
    replaceText(element, /[-]/g , '')
}
