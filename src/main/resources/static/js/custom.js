$(document).ready(function () {
    $("#btnCancel").click(function () {
        window.location = "/books/";
    })
});

function checkBookUnique(form) {
    alert('Check book');
    url = '/api/v1/books/check_unique';
    bookTitle = $("#title").val();
    params = {
        title: bookTitle
    };

    $.post(url, params, function (res) {
        if (res === 'OK') {
            form.submit();
            return;
        }
        if (res === 'Duplicated') {
            alert(`There is another book having the title ${bookTitle}`);
        }
    })
    return false;
}