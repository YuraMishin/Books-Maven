$(document).ready(function () {
    $("#btnCancel").click(function () {
        window.location = "/books/";
    })

    onClickLinkDelete();
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

function onClickLinkDelete() {
    $(".link-delete").click(function (e) {
        e.preventDefault();
        let link = $(this);
        let bookId = link.attr("bookId");
        $('#confirmText').text(`Are you sure you want to delete the book with ID ${bookId}?`);
        $('#deleteForm').attr('action', `/books/${bookId}`);
        $('#confirmModal').modal('show');
    });
}
