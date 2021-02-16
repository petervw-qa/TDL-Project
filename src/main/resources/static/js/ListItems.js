'use strict';

const createListItems = () => {
	const listItemName = document.querySelector('#listitems-name')
	const listItemDescription = document.querySelector('#listitems-description')
	const listItemCompleted = document.querySelector('#listitems-completed')
	const listItemListName = document.querySelector('#listitems-listname')
	const body = {
		"name": listItemName.value,
		"description": listItemDescription.value,
		"completed": listItemCompleted.value,
		"listname": {
			"id": listItemListName.value
		}
	}
	fetch("http://localhost:9092/listitems/create", {
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
						console.log("The item has been successfully added to the database!")
					})
		})
		.catch((error) => console.error(`There as been an error: ${error}`))
};

const readAllListItems = () => {
	fetch("http://localhost:9092/listitems/read")
		.then((response) => {
			(response.status !== 200) ? console.error(`There as been an error: ${response.status}`) :
				response.json()
					.then((data) => {
						console.log(data)
					})
		}).catch((error) => console.error(`There as been an error: ${error}`))
};

const readByIDListItems = () => {
	const listItemID = document.querySelector('#listitems-read-id').value
	fetch(`http://localhost:9092/listitems/read/${listItemID}`)
		.then((response) => {
			(response.status !== 200) ? console.error(`There as been an error: ${response.status}`) :
				response.json()
					.then((data) => {
						console.log(data)
					})
		}).catch((error) => console.error(`There as been an error: ${error}`))
};

const updateListItems = () => {
	const listItemID = document.querySelector('#listitems-update-id').value
	const listItemRename = document.querySelector('#listitems-rename')
	const listItemNewDescription = document.querySelector('#listitems-new-description')
	const listItemNewCompleted = document.querySelector('#listitems-new-completed')
	const body = {
		"name": listItemRename.value,
		"description": listItemNewDescription.value,
		"completed": listItemNewCompleted.value
	}
	fetch(`http://localhost:9092/listitems/update/${listItemID}`, {
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
	}).catch((error) => console.error(`There as been an error: ${error}`))
};

const deleteListItems = () => {
	const listItemID = document.querySelector('#listitems-delete')
	fetch(`http://localhost:9092/listitems/delete/${listItemID}`, {
		method: "DELETE"
	})
		.then((response) => {
			(response.status !== 204) ? console.error(`Status is ${response.status}`) :
				console.log("The item has been successfully deleted!")
		})
};