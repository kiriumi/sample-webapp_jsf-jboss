/**
 * 二重送信防止のため、要素のクリックイベントを無効化し、ローディング中を示す画像を表示する
 */
document.addEventListener("submit", function() {
    $('form').css('pointer-events', 'none');
    $('#loading').show();
});
