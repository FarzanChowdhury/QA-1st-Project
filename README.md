Coverage: 80.9% 

# Inventory Management System

This is the first project done at QA Academy between week 5 and 6. It is a Java program and it uses mySQL for the databaseas as backend program.

### Prerequisites

- You will need a local installation of git, an example of this would be git bash which can be downloaded at https://git-scm.com/downloads
- You will need an IDE which can run JAVA code, an example of this would be Eclipse which can be downloaded at https://www.eclipse.org/downloads/
- You will need a software that can interact with the database outside of the application, an example would be mySQL wich can be downloaded at https://dev.mysql.com/downloads/windows/installer/8.0.html
- You will need a software to package and run tests on the application, an example would be Maven which can be downlaoded at https://maven.apache.org/download.cgi

### Installing/Getting Started

Step 1. To setup a development environment, you will fork my git respository and copy the code generated on your newly-created git respository
Step 2. Open up your local installation of git and use the command "git clone" after choosing the desired file location
Step 3. Once the directory is cloned, import it in the IDE of choice via "existing maven projects"
Step 4. Inside src/main/java/com/qa/ims/utils/db.properties,  you will need to change the db.user and db.pass to you own on your local host
Step 5. Go to src/main/java/com/qa/ims/runner.java and run the application, it should start if you connected your database correctly

```
In Console Example :
Type "customer"
Type "create"
Type any first name
Type any surname

To see if it has been added to the database, type "read" in "customer"
```

## Running the tests

All test can be located on src/test/java directory

- To run the tests within the application you can use maven or JUnit both return the same result
- If you are using eclipse then you can right click the project and then run as JUnit test this will return the results which is broken down into pass, fail and errors
- Alternatively, you can right-click the project folder and select the "Coverage As..." option

### Unit Tests 

- These tests cover some basics, such as ensuring that classes like Order or Item have correct equals() and hashCode() methods

- My unit tests cover the objects for each system and the controllers:

- Objects : It tests that the objects returns the correct type.
- Controllers : The Mockito tests simulates user-input to check that objects are created and handled properly

Example - 

```
@Test
	public void testDelete() {
		long id = 1L;

		Mockito.when(utils.getLong()).thenReturn(id);
		Mockito.when(orderDAO.delete(id)).thenReturn(1);
		Mockito.when(orderItemDAO.deleteOrderItemViaOrderId(id)).thenReturn(1);

		assertEquals(id, this.orderController.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(orderDAO, Mockito.times(1)).delete(id);
		Mockito.verify(orderItemDAO, Mockito.times(1)).deleteOrderItemViaOrderId(id);
	}
```
  -  This above test in located in src/test/java/com/qa/ims/controllers/OrderController.java and it uses JUnit and Mockito 

- It tests the delete function in the order controller
- It passes the ID = 1L when asked for an id
- It returns 1 for total orders deleted using the delete function in the DAO
- Then it checks that the delete function does what i have defined above using assertEquals to make sure everything works as expected

### Integration Tests 

- My integration tests cover Data-Access Objects (DAOs)

- Daos : They are a bit more complex, passing in objects, ensuring they are processed correctly and that the correct information is passed back

Example - 

```
@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1L, "football", 20.00F));
		expected.add(new Item(2L, "basketball", 15.00F));
		expected.add(new Item(3L, "perfume", 40.00F));
		assertEquals(expected, itemDAO.readAll());
	}
```

- This above test in located in src/test/java/com/qa/ims/persistance.dao/ItemDAOTest.java and it uses JUnit

- This test will check if the console will output all the available item in the database and what their names and values are
- Then it checks that the readall function does what i have defined it as, using assertEquals to make sure everything works as expected

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)

* **Farzan Chowdhury** - *Completed the application*

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
