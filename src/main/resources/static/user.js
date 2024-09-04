const userTableContainer = document.querySelector("#userTableContainer")
const userTemplateRow = document.querySelector("#userTemplateRow")
const baseURL = 'http://localhost:8080/api'

const userId = document.querySelector('#userId')
const userName = document.querySelector('#userName')
const userSurname = document.querySelector('#userSurname')
const userAge = document.querySelector('#userAge')
const userEmail = document.querySelector('#userEmail')
const userRole = document.querySelector('#userRole')

const navName = document.querySelector('#navName')
const navRole = document.querySelector('#navRole')


fetch(`${baseURL}/user`).then((res) => res.json()).then((data) => {
    console.log(data)

    navName.innerHTML = data.email
    navRole.innerHTML = data.roles[0].name
    userId.innerHTML = data.id
    userName.innerHTML = data.name
    userSurname.innerHTML = data.surname
    userAge.innerHTML = data.age
    userEmail.innerHTML = data.email
    userRole.innerHTML = data.roles[0].name

})
