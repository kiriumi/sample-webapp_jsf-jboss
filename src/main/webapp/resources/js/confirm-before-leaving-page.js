/**
 * ページを離れる際に、入力項目に変更があったら確認する 使用するには、以下項目のclass属性に、以下クラス名を指定する
 * ・入力項目：confirm-changed ・送信項目：confirm-before-leaving
 */
$(function() {

    const inputClass = 'confirm-changed';
    const clickedClass = '.confirm-before-leaving';

    // フラグの初期化
    var changed = false;
    var clickedItem = false;

    // ページの移動・更新を検知した時の処理
    $(window).on('beforeunload', function() {
        if (changed)
            return 'このページを離れます。\nよろしいですか？'; // フラグが TRUE の場合に確認メッセージを出力
    });

    // 文字入力項目に変更があった時の処理
    // ※changeだと、文字入力が未確定でも変更されたと判断するため、keydownとkeyupにする
    $('input, textarea').on('keydown keyup', function() {
        if ($(this).hasClass(inputClass)) {
            changed = true;
        }
    });

    // その他入力項目に変更があった時の処理
    $('input, select').change(function() {
        if ($(this).hasClass(inputClass)) {
            changed = true;
        }
    });

    // フォーム送信（送信項目クリック以外）時の処理
    $('form').on('submit', function() {
        if (!clickedItem) {
            changed = false;
        }
    });

    // 送信項目クリック時の処理
    $(clickedClass).on('click', function() {
        clickedItem = true;
    });

});
