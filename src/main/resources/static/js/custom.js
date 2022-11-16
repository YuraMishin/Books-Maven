$(document).ready(function () {
    $("#btnCancel").click(function () {
        window.location = "/books/";
    })
});

function checkBookUnique(form) {
    url = '/api/v1/books/check_unique';
    bookTitle = $("#title").val();
    params = {
        title: bookTitle
    };

    $.post(url, params, function (res) {
        if (res === 'OK') {
            form.submit();
            return;
        } else if (res === 'Duplicated') {
            // alert(`There is another book having the title ${bookTitle}`);
            showModalDialog('Warning',
                `There is another book having the title ${bookTitle}`
            );
        } else {
            showModalDialog("Error", "Unknown response");
        }
    }).fail(function () {
        showModalDialog("Error", "Couldn't connect to the server");
    });
    return false;
}

function showModalDialog(title, message) {
    $('#modalTitle').text(title);
    $('#modalBody').text(message);
    $('#modalDialog').modal('show');
}
