const url = 'http://localhost:8080/api/admin';
const urlUser = 'http://localhost:8080/api/user';
const userEdit = document.getElementById("editForm");
const userDelete = document.getElementById("deleteForm");
const userAdd = document.getElementById("newUserForm");


$(document).ready(function () {
    getAllUsersTable();
    getHeader();
})


function getAllUsersTable() {
    $("#users").empty();
    fetch(url, {
        method: "GET",
        headers: {
            "Content-type": "application/json"
        },
    })
        .then(res => res.json())
        .then(data => {
            data.forEach(user => {
                $('#users').append($('<tr id="usersRows">')).append(
                    $('<td>').text(user.id),
                    $('<td>').text(user.firstName),
                    $('<td>').text(user.lastName),
                    $('<td>').text(user.age),
                    $('<td>').text(user.email),
                    $('<td>').text(user.roleSet.map(role => role.role.substring(5))),
                    $('<td>').append($('<button>').text("Edit").attr({
                        "type": "button",
                        "class": "btn btn-primary edit",
                        "data-toggle": "modal",
                        "data-target": "#editModal",
                        "id": "editUser` + user.id + `"
                    }).data("user", user)),
                    $('<td>').append($('<button>').text("Delete").attr({
                        "type": "button",
                        "class": "btn btn-danger delete",
                        "data-toggle": "modal",
                        "data-target": "#deleteModal",
                    }).data("user", user)),
                )
            })
        })
}

function getHeader() {
    fetch(urlUser)
        .then(resp => resp.json())
        .then(data => {
            $('#headUser1').append(data.email)
            $('#headRole1').append(data.roleSet.map(role => role.role.substring(5)))
        })
}

$(document).on("click", ".edit", function () {
    let editUser = $(this).data('user');
    $("#idEdit").val(editUser.id);
    $("#firstNameEdit").val(editUser.firstName);
    $("#lastNameEdit").val(editUser.lastName);
    $("#emailEdit").val(editUser.email);
    $("#ageEdit").val(editUser.age);
    $("#passwordEdit").hidden

    if (editUser.roleSet[0].role === 'ROLE_ADMIN') {
        $('#roleSetEditList option[value="ROLE_ADMIN"]').prop('selected', true)
    }
    if (editUser.roleSet[0].role === 'ROLE_USER') {
        $('#roleSetEditList option[value="ROLE_USER"]').prop('selected', true)
    }
    if (editUser.roleSet.length > 1) {
        $('#roleSetEditList option').prop('selected', true)
    }
})

userEdit.addEventListener("submit", e => {
    e.preventDefault();

    let selectedRoles = [];
    const values = $('#roleSetEditList').val();
    for (let i = 0; i < values.length; i++) {
        selectedRoles.push({
            id: values[i] === "ROLE_ADMIN" ? 1 : 2,
            role: values[i]
        })
    }

    let editUrl = url + '/' + document.getElementById('idEdit').value
    fetch(editUrl, {
        method: "PUT",
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            id: document.getElementById('idEdit').value,
            firstName: document.getElementById('firstNameEdit').value,
            lastName: document.getElementById('lastNameEdit').value,
            password: document.getElementById('passwordEdit').value,
            email: document.getElementById('emailEdit').value,
            age: document.getElementById('ageEdit').value,
            roleSet: selectedRoles
        })
    })
        .then(() => {
            getAllUsersTable()
            $("#editModal").modal("hide")
        })

})

$(document).on("click", ".delete", function () {
    let deleteUser = $(this).data('user');
    $("#idDelete").val(deleteUser.id);
    $("#firstNameDelete").val(deleteUser.firstName);
    $("#lastNameDelete").val(deleteUser.lastName);
    $("#emailDelete").val(deleteUser.email);
    $("#ageDelete").val(deleteUser.age);
    $("#passwordDelete").val('');

    if (deleteUser.roleSet[0].role === 'ROLE_ADMIN') {
        $('#rolesDeleteSelect option[value="ROLE_ADMIN"]').prop('selected', true)
    }
    if (deleteUser.roleSet[0].role === 'ROLE_USER') {
        $('#rolesDeleteSelect option[value="ROLE_USER"]').prop('selected', true)
    }
    if (deleteUser.roleSet.length > 1) {
        $('#rolesDeleteSelect option').prop('selected', true)
    }
})

userDelete.addEventListener("submit", r => {
    r.preventDefault();

    let deleteUrl =  url + '/' + document.getElementById('idDelete').value
    fetch(deleteUrl, {
        method: "DELETE",
        headers: {
            "Content-type": "application/json"
        },
    }).then(() => {
        getAllUsersTable()
        $("#deleteModal").modal("hide")
    })
})


userAdd.addEventListener("submit", (e)=>{
    e.preventDefault();

    let selectedRoles = []
    const values = $('#roleSetAdd').val();
    for (let i = 0; i < values.length; i++) {
        selectedRoles.push({
            id: values[i]==="ROLE_ADMIN" ? 1 : 2 ,
            role: values[i]
        })
    }
    console.log(selectedRoles)

    fetch(url, {
        method:"POST",
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            firstName: document.getElementById('firstNameAdd').value,
            lastName: document.getElementById('lastNameAdd').value,
            email: document.getElementById('emailAdd').value,
            age: document.getElementById('ageAdd').value,
            password: document.getElementById('passwordAdd').value,
            roleSet: selectedRoles
        })
    })
        .then(()=>{
            document.getElementById("usersTable-tab").click()
            getAllUsersTable()
        })

})





