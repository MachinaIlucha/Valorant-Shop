$("#login-form").submit(function (e) {
    e.preventDefault();

    $.ajax({
        url: "login",
        type: "POST",
        data: $("#login-form").serialize(),
    });
});