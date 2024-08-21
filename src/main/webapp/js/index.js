$(document).ready(function() {
    $.ajax({
        url: "UserServlet",
        type: "GET",
        success: function(data) {
            $("#userData").html(data);
        }
    });

    $("#userData").on("click", ".edit", function() {
        var id = $(this).attr("id");
        console.log(id);
        $.ajax({
            url: "UserServlet",
            type: "GET",
            data: {
                editId: id
            },
            success: function(data) {
                console.log(data);
                var userData = JSON.stringify(data);
                console.log(userData);
                sessionStorage.setItem("key", userData);
                window.location.href = 'userregister.jsp';
            }
        });
    });

    $('#searchForm').on('submit', function(event) {
        event.preventDefault();
        var query = $("#searchInput").val();
        $.ajax({
            url: "UserServlet",
            type: "GET",
            data: {
                keyword: query
            },
            success: function(data) {
                $("#userData").html(data);
            }
        });
    });

    $("#userData").on("click", ".delete", function() {
        var id = $(this).attr("id");
        $.ajax({
            url: "UserServlet",
            type: "GET",
            data: {
                deleteId: id
            },
            success: function() {
                window.location.reload();
            }
        });
    });
});