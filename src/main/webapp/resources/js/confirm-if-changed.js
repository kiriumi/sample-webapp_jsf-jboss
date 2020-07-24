function confirmIfChanged(ids) {

    // フラグの初期化
    var changeForm = false;

    // ページの移動・更新を検知した時の処理
    $(window).on('beforeunload', function() {
        if (changeForm)
            return 'このページを離れます。\nよろしいですか？';
    });

    // フォームの内容に変更があった時の処理
    $(ids).change(function() {
        changeForm = true;
    });

    // フォームの送信処理
    $('form').on('submit', function() {
        changeForm = false;
    });

};
