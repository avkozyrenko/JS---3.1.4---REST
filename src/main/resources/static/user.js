const url = 'http://localhost:8080/api/user';


$(function () {
    getUser();
    getHeader();
})

function getUser() {
    fetch(url, {
        method: "GET",
        headers: {
            "Content-type": "application/json"
        }
    })
        .then(res => res.json())
        .then(user => {
            $('#user').append($('<tr>')).append(
                $('<td>').text(user.id),
                $('<td>').text(user.firstName),
                $('<td>').text(user.lastName),
                $('<td>').text(user.age),
                $('<td>').text(user.email),
                $('<td>').text(user.roleSet.map(role => role.role.substring(5))))
        })
}

function getHeader() {
    fetch(url)
        .then(resp => resp.json())
        .then(data => {
            $('#headUser').append(data.email)
            $('#headRole').append(data.roleSet.map(role => role.role.substring(5)))
        })
}

