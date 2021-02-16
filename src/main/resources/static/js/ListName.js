'use strict';

const createListName = () => {
	const listName = document.querySelector('#listname')
	const body = {
		"name": listName.value
	}
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
					.then((data) => {
						console.log(data)
						console.log(`The item has been successfully added to the database!`)
					})
		})
		.catch((error) => console.error(`There has been an error. It is: ${error}`))
};

const readAllListName = () => {
	fetch("http://localhost:9092/listname/read")
		.then((response) => {
			(response.status !== 200) ? console.error(`There as been an error: ${response.status}`) :
				response.json()
					.then((data) => {
						console.log(data)
					})
		}).catch((error) => console.error(`There has been an error. It is: ${error}`))
};

const readByIDListName = () => {
	const listNameID = document.querySelector('#listname-read-by-id').value
	fetch(`http://localhost:9092/listname/read/${listNameID}`)
		.then((response) => {
			(response.status !== 200) ? console.error(`There as been an error: ${response.status}`) :
				response.json()
					.then((data) => {
						console.log(data)
					})
		}).catch((error) => console.error(`There has been an error. It is: ${error}`))
};


const updateListName = () => {
	const listNameID = document.querySelector('#listname-update').value
	const listNameRename = document.querySelector('#listname-rename')
	const body = {
		"name": listNameRename.value
	}
	fetch(`http://localhost:9092/listname/update/${listNameID}`, {
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
	const listNameID = document.querySelector('#listname-delete').value
	fetch(`http://localhost:9092/listname/delete/${listNameID}`, {
		method: "DELETE"
	})
		.then((response) => {
			(response.status !== 204) ? console.error(`There as been an error: ${response.status}`) :
				console.log("Successfully deleted!")
		})
};