# Practical Project: To Do List Application
 Coverage: 84.6%

## Description
A To Do List Application with a front-end written in HTML, CSS and JavaScript as well as a back-end written in Java making use of the SpringBoot framework.
This is an OOP-based web application, with utilisation of supporting tools, methodologies, and technologies, that encapsulates all fundamental and practical modules covered during training with QA.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

In order to use this application, you will need:

```

- Java Version 11 or higher.
- MySQL Version 8 or higher.
- Git
- Maven
- Visual Studio Code 
- Spring

```


### Installing

You need to have the above installed.

Once you have the java -jar ToDoList-Application-0.0.1-SNAPSHOT file downloaded onto your system, you can execute it through your command line (cmd) or any terminal that can run executables (like Windows PowerShell for example) by running the following command:
```
java -jar ToDoList-Application-0.0.1-SNAPSHOT.war
```

## Running the tests

In order to run the tests on your own system, you must either fork and clone the repository down to your own local space or clone it using the git clone command and the root URL for this repository: 

```
git clone https://github.com/petervw-qa/TDL-Project
```

Then you will be able to navigate to your local repository on your command line interface and run test with your installed maven build tool.
```
mvn test
```

### Unit Tests 

These are the tests that I created in order to test and figure out how the classes in the packages.domain package were interacting with one another, their getters and setters and how the create, update, read and delete functions worked in each data access object. Through the use of unit tests, I was able to test most lines written except.

### Examples of Unit Testing done for both Entity Service classes:

```

	@Test
	void createTEST() throws Exception {
		final ListName List_No_Id = new ListName(null, "Monday");
		final ListName List_With_Id = new ListName(1L, "Monday");
		when(this.mockRepo.save(List_No_Id)).thenReturn(List_With_Id);
		assertThat(this.service.create(List_No_Id))
		.isEqualTo(this.mapToDto(List_With_Id));
		verify(this.mockRepo, atLeastOnce()).save(List_No_Id);
	}

	@Test
	void readAllTEST() throws Exception {
		List<ListNameDto> nameDtos = listOfLists.stream().map(this::mapToDto).collect(Collectors.toList());
		when(this.mockRepo.findAll()).thenReturn(listOfLists);
		assertThat(this.service.readAll())
		.isEqualTo(nameDtos);
		verify(this.mockRepo, atLeastOnce()).findAll();
	}

	@Test
	void createTEST() throws Exception {
		final ListItem Item_No_Id = new ListItem(null, "Shopping","Groceries", false);
		final ListItem Item_With_Id = new ListItem(1L, "Shopping","Groceries", false);
		when(this.mockRepo.save(Item_No_Id)).thenReturn(Item_With_Id);
		assertThat(this.service.create(Item_No_Id))
		.isEqualTo(this.mapToDto(Item_With_Id));
		verify(this.mockRepo, atLeastOnce()).save(Item_No_Id);
	}
	
	@Test
	void readAllTEST() throws Exception {
		List<ListItemDto> itemDtos = listOfItems.stream().map(this::mapToDto).collect(Collectors.toList());
		when(this.mockRepo.findAll()).thenReturn(listOfItems);
		assertThat(this.service.readAll())
		.isEqualTo(itemDtos);
		verify(this.mockRepo, atLeastOnce()).findAll();
	}

```
### Integration Tests
The integration tests I created were to test the create, read, update and delete functionality in the entity controllers and their relationship with their respective servers.

### Examples of Integration Testing done for both Entity Controller classes:

```
	@Test
	void createTEST() throws Exception {
		ListNameDto ListName_Test = mapToDto(new ListName("Peter"));
		ListNameDto ListName_Test_Saved = mapToDto(new ListName("Peter"));
		String testListNameToJSON = this.jsonifier.writeValueAsString(ListName_Test);
		ListName_Test_Saved.setId(4L);
		String testListNameSavedtoJSON = this.jsonifier.writeValueAsString(ListName_Test_Saved);
		RequestBuilder rB = post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testListNameToJSON);
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testListNameSavedtoJSON);
		this.mvc.perform(rB).andExpect(checkStatus).andExpect(checkBody);

	}
	
	@Test
	void createTEST() throws Exception {
		ListItemDto ListItem_Test = mapToDto(new ListItem("Shopping", "Groceries", true));
		ListItemDto ListItem_Test_Saved = mapToDto(new ListItem("Shopping", "Groceries", true));
		String testListItemToJSON = this.jsonifier.writeValueAsString(ListItem_Test);
		ListItem_Test_Saved.setId(4L);
		String testListItemSavedToJSON = this.jsonifier.writeValueAsString(ListItem_Test_Saved);
		RequestBuilder rB = post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testListItemToJSON);
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testListItemSavedToJSON);
		this.mvc.perform(rB).andExpect(checkStatus).andExpect(checkBody);
	}

```
### Acceptance Tests
The acceptance tests I had written were used to test the User Story requirements I made with my Jira (Scrum) project management board that is linked to this remote repository. These acceptance tests test all CRUD functionality on the webpage as well as navigation between buttons and modals on the page.

### Examples of Acceptance Testing using Selenium for the frontend html webpage:

```

	@Test
	public void testCreateList() throws InterruptedException {
		test = report.startTest("Test for Create List");
		test.log(LogStatus.INFO, "List Created");
		
		driver.get("http://127.0.0.1:5500/src/main/resources/static/index.html");
		targ = driver.findElement(By.xpath("/html/body/main/button[1]"));
		targ.click();
		targ = driver.findElement(By.xpath("//*[@id=\"listname-name\"]"));
		Thread.sleep(5000);
		targ.sendKeys("Monday");
		targ = driver.findElement(By.xpath("//*[@id=\"createListModal\"]/div/div/button[2]"));
		targ.click();
		Thread.sleep(5000);
	}

	@Test
	public void testCreateItem() throws InterruptedException {
		test = report.startTest("Test for Create Item");
		test.log(LogStatus.INFO, "Item Created");
		
		driver.get("http://127.0.0.1:5500/src/main/resources/static/index.html");
		targ = driver.findElement(By.xpath("/html/body/main/button[5]")); // target modal
		targ.click(); // open modal
		targ = driver.findElement(By.xpath("//*[@id=\"items-name\"]")); // target name 
		Thread.sleep(5000); // wait
		targ.sendKeys("Exercise"); // send name
		targ = driver.findElement(By.xpath("//*[@id=\"items-description\"]")); // target desc
		Thread.sleep(5000); // wait
		targ.sendKeys("Chest"); // send desc
		targ = driver.findElement(By.xpath("//*[@id=\"items-completed\"]")); // target bool
		Thread.sleep(5000); // wait
		targ.sendKeys("true"); // send bool
		targ = driver.findElement(By.xpath("//*[@id=\"items-listname-Id\"]")); // target ID
		Thread.sleep(5000); // wait
		targ.sendKeys("1"); // send ID
		targ = driver.findElement(By.xpath("//*[@id=\"createItemModal\"]/div/div/button[2]")); // target button
		targ.click();
		Thread.sleep(5000);
	}


```
  

## Deployment

If you want to deploy this project with an actual database, you will need to have your MySQL database working and currently running, however, you will not have to manually create your local SQL database. 

If you would like to do it manually: When you have then logged in, run mySQL on your cmd and set up your databases and tables using the following commands tailored to this project (if you have changed your username and password from admin and root respectively, you will either need to use your bespoke details or reinstall to reset the username and password):

```

drop table if exists `list_name` CASCADE;
drop table if exists `list_item` CASCADE;
create table list_name (id bigint PRIMARY KEY AUTO_INCREMENT, name varchar(255) not null);
create table list_item (id bigint PRIMARY KEY AUTO_INCREMENT, completed boolean not null, description varchar(255) not null, name varchar(255) not null, listname_id bigint);
alter table list_item add constraint FK6ikpg1bdaaa05dflt1e84kt63 foreign key (listname_id) references list_name on delete cascade;

```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

- Peter Vaughan-Williams - _Project_ - [Peter V-W](https://github.com/petervw-qa/)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

## Acknowledgments

* Thank you to Vin (https://github.com/vineshghela) for all the help I had received during the production of this project.
* A large thank you to Gie, Peprah, and Arsalan for their help throughout the project for all of the motivational and technical help during the development of this project.
* Finally, a large thank you to the creators of https://httpstatusdogs.com/ for making HTTP Status Codes fun to read and follow along during the project. 
