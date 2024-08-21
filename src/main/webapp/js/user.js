$(document).ready(function() {
    var dataValue = sessionStorage.getItem("key");
    var id;
    if (dataValue != null) {
        var parsedData = JSON.parse(dataValue);
        id = parsedData.id;
        document.getElementById("firstName").value = parsedData.firstName;
        document.getElementById("lastName").value = parsedData.lastName;
        document.getElementById("username").value = parsedData.username;
        document.getElementById("email").value = parsedData.email;
        document.getElementById("address").value = parsedData.address;
        document.getElementById("contact").value = parsedData.contact;
        document.getElementById("countryCode").value = parsedData.countryCode;
        sessionStorage.removeItem("key");
    }
    var input = document.querySelector("#contact");
    var countryCodeInput = document.querySelector("#countryCode");
    var initialCountry = "";
    if (dataValue != null) {
        initialCountry = parsedData.countryCode;
    } else {
        initialCountry = "auto";
    }
    var iti = window.intlTelInput(input, {
        initialCountry: initialCountry,
        geoIpLookup: function(success) {
            fetch('https://ipinfo.io/json', {
                headers: {
                    'Accept': 'application/json'
                }
            }).then((resp) => resp.json()).then((resp) => {
                let countryCode = (resp && resp.country) ? resp.country : "us";
                success(countryCode);
            }).catch(() => {
                success("us");
            });
        },
        utilsScript: "https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/utils.js"
    });

    input.addEventListener('countrychange', function() {
        const countryData = iti.getSelectedCountryData();
        countryCodeInput.value = countryData.iso2;
    });

    document.querySelector("#registrationForm").addEventListener("submit", function() {
        const countryData = iti.getSelectedCountryData();
        countryCodeInput.value = countryData.iso2;
    });
    if (id != null) {
        document.getElementById("registrationForm").addEventListener("submit", function(event) {
            event.preventDefault();
            var data = {
                firstName: document.getElementById("firstName").value,
                lastName: document.getElementById("lastName").value,
                username: document.getElementById("username").value,
                email: document.getElementById("email").value,
                address: document.getElementById("address").value,
                contact: document.getElementById("contact").value,
                countryCode: document.getElementById("countryCode").value,
            };
            var payLoad = {
                updateId: id,
                userData: JSON.stringify(data)
            };
            var firstName = $("#firstName").val();
            var lastName = $("#lastName").val();
            var namePattern = /^[A-Za-z\s]+$/;

            if (firstName.length < 3) {
                alert("First name must be at least 3 characters long.");
                event.preventDefault();
            } else if (!namePattern.test(firstName)) {
                alert("First name must contain only letters.");
                event.preventDefault();
            }

            if (lastName.length < 3) {
                alert("Last name must be at least 3 characters long.");
                event.preventDefault();
            } else if (!namePattern.test(lastName)) {
                alert("Last name must contain only letters.");
                event.preventDefault();
            }
            if (!iti.isValidNumber()) {
                alert("Please enter a valid phone number.");

            }
            $.ajax({
                url: "UserServlet",
                type: "POST",
                data: payLoad,
                success: function() {
                    if (iti.isValidNumber()) {
                        window.location.href = 'index.jsp';
                    }
                }
            });
        });
    }
});