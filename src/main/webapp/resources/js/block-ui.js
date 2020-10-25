function showBlockUi() {
    $.blockUI({
        message: "",
        css: {
            "padding": "10px 30px",
            "font-size": "20px",
            "font-weight": "bold"
        },
        overlayCSS: {
            backgroundColor: '#FFFFFF',
            opacity: 0.6,
            cursor: 'wait'
        }
    });
}

function hideBlockUi() {
    $.unblockUI();
}
