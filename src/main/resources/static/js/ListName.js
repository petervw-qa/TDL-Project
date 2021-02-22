'use strict';

const listNameName = document.querySelector('#listname-name')
const listNameID = document.querySelector('#listname-read-id')
const listNameRename = document.querySelector('#listname-rename')
const listNameUpdateId = document.querySelector('#listname-update-id')
const listNameDeleteId = document.querySelector('#listname-delete-id')

const successList = document.querySelector(".listTitle")

const createListSuccess = () => {
	const popUp = document.createElement("p");
	const text = document.createTextNode("List Successfully Created!")
	popUp.appendChild(text);
	successList.appendChild(popUp);
}

const newList = () => {
	const list = document.createElement("li");
	const e = document.getElementById("listNameList").value;
	const text = document.createTextNode(e);
	list.appendChild(text);
	if (e === ``) {
		alert("You can't have an empty list!");
	} else {
		document.getElementById("listName-unlisted").appendChild(list);
	} document.getElementById("listNameList").value = "";
}

const postListName = () => {

	const lName = listNameName.value;

	console.log(lName);

	const body = {
		"name": lName
	}
	console.log(body)
	fetch("http://localhost:9092/listname/create", {
		method: "POST",
		headers: {
			"Content-type": "application/json"
		},
		body: JSON.stringify(body)
	})
		.then((response) => {
			(response.status !== 201) ? console.error(`There as been an error: ${response.status}`) :
				response.json()
				.then(createListSuccess())
					.then((data) => {
						console.log(data)
						console.log(`The item has been successfully added to the database!`)
					})
		})
		.catch((error) => console.error(`There has been an error. It is: ${error}`))
};

const getAllListName = () => {
	fetch(`http://localhost:9092/listname/read`)
		.then((response) => {
			if (response.status !== 200) {
				console.log(`There as been an error: ${response.status}`);
				return;
			}
			response.json()
				.then((data) => console.log(data));
		}).catch((err) => console.error(`There has been an error. It is: ${err}`));
}

const getByIDListName = () => {

	let id = listNameID.value;
	
	const params = new URLSearchParams(window.location.search);

	console.log(params);
	for (const param of params) {
		console.log(param);
	}
	console.log(`This list's ID is: ${id}`);
	fetch(`http://localhost:9092/listname/read/${id}`)
		.then((response) => {
			if (response.status !== 200) {
				console.log(`There has been an error. It is: ${error}`);
				return;
			}
			response.json()
				.then((data) => console.log(data));
		}).catch((err) => console.error(`There has been an error. It is: ${err}`));
}


const putListName = () => {

	const nID = listNameUpdateId.value;
	const nName = listNameRename.value

	const body = {
		"name": nName
	}
	fetch(`http://localhost:9092/listname/update/${nID}`, {
		method: "PUT",
		headers: {
			"Content-type": "application/json"
		},
		body: JSON.stringify(body)
	}).then((response) => {
		(response.status !== 202) ? console.error(`There as been an error: ${response.status}`) :
			response.json()
				.then((data) => {
					console.log(data)
				})
	}).catch((error) => console.error(`There has been an error. It is: ${error}`))
};

const deleteListName = () => {
	const deleteId = listNameDeleteId.value
	fetch(`http://localhost:9092/listname/delete/${deleteId}`, {
		method: "DELETE"
	})
		.then((response) => {
			(response.status !== 410) ? console.error(`There as been an error: ${response.status}`) :
				console.log("Successfully deleted!")
		}).catch((err) => console.log(err));
};