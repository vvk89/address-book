package lu.jemmic.addressbook.controller;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import lu.jemmic.addressbook.controller.AddressBookController.ContactData;
import lu.jemmic.addressbook.view.CommandLine;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CommandLine.class, AddressBookController.class})

public class AddressBookControllerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testDisplayListContacts() {
		
		try {			
			
			System.out.println("Started testDisplayListContacts");
			
			AddressBookController c = new AddressBookController();
			c.mainProcess();
			
			//invoke the private method "displayListContacts"
			Method methodDisp = c.getClass().getDeclaredMethod("displayListContacts", null);
			methodDisp.setAccessible(true);//private method can be accessed only if we set this to true
			
			List<String> list = (List<String>) methodDisp.invoke(c, (Class[]) null); //this will invoke the displayListContacts method
			
			
			if(list != null) {
				System.out.println("list size=" + list.size());	
			} else {
				System.out.println("list is null or empty");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception occured in fetching contacts");
		}

		System.out.println("Completed testDisplayListContacts");
	}

//	//mockito is having some issues.. need to check
	@Test
	public void testDeleteContacts() {
		System.out.println("test del");
		
		CommandLine classToTest = new CommandLine();
		
		CommandLine c = new CommandLine();
		
		//Mockito
		//getStringField("Name", true)
		
		classToTest = PowerMockito.spy(classToTest);
		
		Map<ContactData, String> map1 = new HashMap<ContactData, String>();
		
		try {
			PowerMockito.doReturn(map1).when(classToTest, "getStringField", "Name", true);
			
			//PowerMockito.when(classToTest, "getStringField", "Name", true).thenReturn(map1);			
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		classToTest.createContact();
		
	}

}