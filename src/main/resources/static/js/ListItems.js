'use strict';
const itemName = document.querySelector('#items-name')
const itemDescription = document.querySelector('#items-description')
const itemCompleted = document.querySelector('#items-completed')
const itemListNameID = document.querySelector('#items-listname-Id')
const listItemID = document.querySelector('#items-id')
const newIName = document.querySelector('#item-new-name')
const newIId = document.querySelector('#item-new-id')
const newIDesc = document.querySelector('#item-new-desc')
const newIComp = document.querySelector('#item-new-comp')
const listItemDeleteID = document.querySelector('#listitems-delete')

const postListItems = () => {
	const iName = itemName.value;
	const iDescription = itemDescription.value;
	const iCompleted = itemCompleted.value;
	const itemToList = itemListNameID.value;

	const body = {
		"name": iName,
		"description": iDescription,
		"completed": iCompleted,
		"listname": {
			"id": itemToList
		}
	}
	fetch("http://localhost:9092/listitem/create", {
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

const getAllListItems = () => {
	fetch(`http://localhost:9092/listitem/read`)
		.then((response) => {
			if (response.status !== 200) {
				console.log(`There as been an error: ${response.status}`);
				return;
			}
			response.json()
				.then((data) => console.log(data));
		}).catch((error) => console.error(`There as been an error: ${error}`));
}

const getByIDListItems = () => {
	const iId = listItemID.value;
	const params = new URLSearchParams(window.location.search);
	console.log(params);
	for (const param of params) {
		console.log(param);
	}
	console.log(`The ID for this item is: ${iId}`);
	fetch(`http://localhost:9092/listitem/read/${iId}`)
		.then((response) => {
			if (response.status !== 200) {
				console.log(`There as been an error: ${response.status}`);
				return;
			}
			response.json()
				.then((data) => console.log(data));
		}).catch((error) => console.error(`There as been an error: ${error}`));
}

const putListItems = () => {
	const updateItemName = newIName.value
    const updateItemId = newIId.value;
    const updateItemDescription = newIDesc.value;
    const updateItemCompleted = newIComp.value;
   
    const body = { 
		"name" : updateItemName,
        "description" : updateItemDescription,
        "completed" : updateItemCompleted,
    }
    console.log(body);

    fetch(`http://localhost:9092/listitem/update/${updateItemId}`, {
        method : `PUT`,
        headers : {
            "Content-Type" : "application/json"
        },
        body : JSON.stringify(body)
    })
    .then((response) => response.json)
    .then((data) => console.log(data))
    .catch((err) => console.error(err));

}

const deleteListItems = () => {
	const itemDeleteID = listItemDeleteID.value;
	fetch(`http://localhost:9092/listitem/delete/${itemDeleteID}`, {
		method: "DELETE"
	})
		.then((response) => {
			(response.status !== 410) ? console.error(`Status is ${response.status}`) :
				console.log("The item has been successfully deleted!")
		})
};