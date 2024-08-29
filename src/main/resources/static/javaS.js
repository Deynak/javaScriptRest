const tableContainer = document.querySelector("#tableContainer");
const templateRow = document.querySelector("#templateRow");
const modalDelete = document.querySelector('#modalDelete')
const deleteId = document.querySelector('#deleteId')
const deleteName = document.querySelector('#deleteName')
const deleteLastName = document.querySelector('#deleteLastName')
const deleteAge = document.querySelector('#deleteAge')
const deleteEmail = document.querySelector('#deleteEmail')
const rolesSelect = document.querySelector('#rolesSelect')

const  editId= document.querySelector('#editId')
const editName = document.querySelector('#editName')
const editSurname = document.querySelector('#editSurname')
const editAge = document.querySelector('#editAge')
const editEmail = document.querySelector('#editEmail')
const editPassword = document.querySelector('#editPassword')
const editRolesSelect = document.querySelector('#editRolesSelect')
const modalBtnEdit = document.querySelector('#modalBtnEdit')

const addForm = document.querySelector('#addForm')
const addName = document.querySelector('#addName')
const addSurname = document.querySelector('#addSurname')
const addAge = document.querySelector('#addAge')
const addEmail = document.querySelector('#addEmail')
const addPassword = document.querySelector('#addPassword')
const addRoles = document.querySelector('#addRoles')

const userId = document.querySelector('#userId')
const userName = document.querySelector('#userName')
const userSurname = document.querySelector('#userSurname')
const userAge = document.querySelector('#userAge')
const userEmail = document.querySelector('#userEmail')
const userRole = document.querySelector('#userRole')
const hiddenId = document.querySelector('#hidden-id')

console.log(hiddenId.value)

const baseURL = 'http://localhost:8080/api'
fetch(`${baseURL}/users/${hiddenId.value}`).then((res) => res.json()).then((data) => {
  console.log(data)

    userId.innerHTML = data.id
    userName.innerHTML = data.name
    userSurname.innerHTML = data.surname
    userAge.innerHTML = data.age
    userEmail.innerHTML = data.email
    userRole.innerHTML = data.roles[0].name

})

addForm.onsubmit = addUser

function addUser(event) {
  event.preventDefault()
  const data = {
    name : addName.value,
    surname : addSurname.value,
    age : addAge.value,
    email : addEmail.value,
    password : addPassword.value,

  }
  if (addRoles.value == 1) {
    data.roles = [{id : addRoles.value, name: "ADMIN", userSet : null, authority: "ADMIN"}]
  } else if (addRoles.value == 2){
    data.roles = [{id : addRoles.value, name: "USER", userSet : null, authority: "USER"}]
  }
  fetch(`${baseURL}/users`, {
    method : 'POST',
    headers : {Accept: "application/json",
      "Content-Type": "application/json", },
    body : JSON.stringify(data)
  }).then((res) => res.json() ).then((data) => {
    console.log(data)
    getUsers()

  })
}
function getRoles() {
  fetch(`${baseURL}/roles`).then((res) => res.json()).then((data) => {
    console.log(data)
    data.forEach((el) => {
      let option = document.createElement('option')
      option.value = el.id
      option.innerHTML = el.name
      rolesSelect.append(option)
    })
  })
}
getRoles()

function getUsers() {
  fetch(`${baseURL}/users/`).then((res) => res.json()).then((data) => {
    console.log(data)
    tableContainer.innerHTML = null
    data.forEach((el) => {
      let copy = templateRow.content.cloneNode(true)
      let id = copy.querySelector('#id')
      let firstName = copy.querySelector('#firstName')
      let lastName = copy.querySelector('#lastName')
      let age = copy.querySelector('#age')
      let email = copy.querySelector('#email')
      let roles = copy.querySelector('#roles')
      let btnEdit = copy.querySelector('#edit')
      let btnDelete = copy.querySelector('#delete')

      id.innerHTML = el.id
      firstName.innerHTML = el.name
      lastName.innerHTML = el.surname
      age.innerHTML = el.age
      email.innerHTML = el.email
      roles.innerHTML = el.roles[0].name

      btnEdit.onclick = () => editUser(el)
      btnDelete.onclick = () => deleteUser(el)
      tableContainer.append(copy)

    })
  })
}
getUsers()
function editUser(el) {
  editId.value = el.id
  editName.value = el.name
  editSurname.value = el.surname
  editAge.value = el.age
  editEmail.value = el.email
  editPassword.value = el.password

  editRolesSelect.value = el.roles[0].id
}
modalBtnEdit.onclick = submitEditUser
function submitEditUser() {
  const data = {
    id: editId.value,
    name : editName.value,
    surname : editSurname.value,
    age : editAge.value,
    email : editEmail.value,
    password : editPassword.value,

  }
  if (editRolesSelect.value == 1) {
    data.roles = [{id : editRolesSelect.value, name: "ADMIN", userSet : null, authority: "ADMIN"}]
  } else if (editRolesSelect.value == 2){
    data.roles = [{id : editRolesSelect.value, name: "USER", userSet : null, authority: "USER"}]

  }
  fetch(`${baseURL}/users`, {
    method : 'PUT',
    headers : {Accept: "application/json",
      "Content-Type": "application/json", },
    body : JSON.stringify(data)
  }).then((res) => res.json() ).then((data) => {
    console.log(data)
    getUsers()
    $("#modalEdit").modal("hide");

  })
}

function deleteUser(el) {
  deleteId.value = el.id
  deleteName.value = el.name
  deleteLastName.value = el.surname
  deleteAge.value = el.age
  deleteEmail.value = el.email
  let deleteSelect = modalDelete.querySelector('#rolesSelect')
  deleteSelect.value = el.roles[0].id
  let btnDelete = modalDelete.querySelector('#modalDeleteBtn')
  btnDelete.onclick = () => {
    fetch(`${baseURL}/users/${el.id}`, {method : 'DELETE'}).then((res) => res.json() ).then((data) => {
      console.log(data)
      getUsers()
    })
  }
}
