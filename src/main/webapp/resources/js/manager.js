// script for admin management

$(document).ready(function () {
    var url;
    var action;

    $("input[name=operation]").click(function () {
        action = this.value;
        if (action === "Delete") {
            url = "/admin/delete";
        } else if (action === "Update") {
            url = "/admin/update";
        }
    });

    $(".adminManageForm").submit(function (e) {

        // prevent form from submitting
        e.preventDefault();

        var data = new Object();
//        data["ssn"] = this.ssn.value;
        data["id"] = this.id.value;

        // only use these for edit operation
        if (action === "Update") {
            data["firstName"] = this.firstName.value;
            data["lastName"] = this.lastName.value;
            data["startDate"] = this.startDate.value;
            data["hourlyRate"] = this.hourlyRate.value;
            data["phone"] = this.phone.value;
            data["street"] = this.street.value;
            data["city"] = this.city.value;
            data["state"] = this.state.value;
            data["zipCode"] = this.zipCode.value;
        }

        console.log(data);
        //TODO: ask for confirmation to send request

        $.ajax({
            url: url,
            type: "GET",
            contentType: "application/json",
            data: data,
            dataType: "json",
            success: function (response, status, xhr) {
                // reponse is a boolean
                if (response) {
                    if (action === "Delete") {
                        // remove row
                        $('#' + data["id"]).remove();
                        $("#msg").html("<p class=\"green\">Deleted successfully</p>");
                    } else if (action === "Update") {
                        $("#msg").html("<p class=\"green\">Updated successfully</p>");
                    } else {
                        //DEBUG
                        console.log("Response: " + response + ". Something went astray");
                    }

                } else {
                    console.log("response: " + JSON.stringify(response));
                }
            },
            error: function (xhr, textStatus, errorThrown) {
                $("#msg").html("<p style=\"color:red;\">ERROR</p>");
            }
        });


    });
    
    /************************************************************************/
    $("input[name=empOperation]").click(function () {
        
        action = this.value;
        if (action === "Delete") {
            url2 = "/employee/delete";
        } else if (action === "Update") {
            url2 = "/employee/update";
        }
    });

    $(".empManageForm").submit(function (e) {

        // prevent form from submitting
        e.preventDefault();

        var data = new Object();
        data["id"] = this.id.value;

        // only use these for edit operation
        if (action === "Update") {
            data["firstName"] = this.firstName.value;
            data["lastName"] = this.lastName.value;
            data["email"] = this.email.value;
            data["creditCard"] = this.creditCard.value;
            data["phone"] = this.phone.value;
            data["street"] = this.street.value;
            data["city"] = this.city.value;
            data["state"] = this.state.value;
            data["zipCode"] = this.zipCode.value;
        }

        console.log(data);
        
        $.ajax({
            url: url2,
            type: "GET",
            contentType: "application/json",
            data: data,
            dataType: "json",
            success: function (response, status, xhr) {
                // reponse is a boolean
                if (response) {
                    if (action === "Delete") {
                        // remove row
                        $('#' + data["id"]).remove();
                        $("#msg").html("<p class=\"green\">Deleted successfully</p>");
                    } else if (action === "Update") {
                        $("#msg").html("<p class=\"green\">Updated successfully</p>");
                    } else {
                        //DEBUG
                        console.log("Response: " + response + ". Something went astray");
                    }

                } else {
                    console.log("response: " + JSON.stringify(response));
                }
            },
            error: function (xhr, textStatus, errorThrown) {
                $("#msg").html("<p style=\"color:red;\">ERROR</p>");
            }
        });


    });

});
