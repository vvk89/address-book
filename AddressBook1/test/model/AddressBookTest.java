package lu.jemmic.addressbook.model;

import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
//import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import lu.jemmic.addressbook.model.AddressBook;
import lu.jemmic.addressbook.model.Contact;

//import junit.framework.Assert;

public class AddressBookTest {

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
	public void testGetAllContacts() {
		
		try {
			System.out.println("Started testGetAllContacts");
			
			AddressBook b = new AddressBook();
			Map<String, Contact> contactMap = b.getAllContacts();

			//expected result: contactMap should be empty, because we can calling for first time
			//else deleted all the contacts and then test this method
			Assert.assertEquals("Actual contact size should be zero", 0, contactMap.size());
			
			System.out.println("completed testGetAllContacts");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testAddContact() {
		
		try {
			System.out.println("Started testAddContact");
			
			AddressBook b = new AddressBook();
			
			Map<String, Contact> m = b.getAllContacts();
            System.out.println(m);
			
			
			//add contact
			Contact contact = new Contact();
			contact.setName("aa");
			contact.setAge(20);
			contact.setSurname("aa");
			contact.setTelephoneNumber("12345");
			contact.setEmail("aaa");
			
			//Map<String, Contact> contactMap = b.addContact(contact);
			boolean contactAdded = b.addContact(contact);
			System.out.println("contactAdded=" + contactAdded);

			if(contactAdded == true) {
				System.out.println("Contact added successfully");
			}  else {
				System.out.println("Contact already exists. Hence not adding");
			}
			
			Contact c = b.getContact(contact.getName() + " " + contact.getSurname());
			Assert.assertNotNull("Contact not added. Please check why contact was not added", c);
			
			System.out.println("completed testAddContact");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testDeleteContactPositiveCase() {
		
		try {
			System.out.println("Started testDeleteContact");
			
			//To test deleteContact, lets first add a contact, and then delete it
			AddressBook b = new AddressBook();
			
			Map<String, Contact> m = b.getAllContacts();
            System.out.println(m);
			
			
			//add contact
			Contact contact = new Contact();
			contact.setName("aa");
			contact.setAge(20);
			contact.setSurname("aa");
			contact.setTelephoneNumber("12345");
			contact.setEmail("aaa");
			
			//Map<String, Contact> contactMap = b.addContact(contact);
			b.addContact(contact);

			Contact c = b.getContact(contact.getName() + " " + contact.getSurname());
			if(c != null) {
				System.out.println("Contact exists");
			}
			
			//now lets try to delete the contact
			b.deleteContact(c);
			
			c = b.getContact(contact.getName() + " " + contact.getSurname());
			
			Assert.assertNull("Delete contact failed", c);
			
			
			System.out.println("completed testDeleteContact");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteContactNotExistingContact() {
		
		try {
			System.out.println("Started testDeleteContactNotExistingContact");
			
			//To test deleteContact, lets first add a contact, and then delete it
			AddressBook b = new AddressBook();
			
			Map<String, Contact> m = b.getAllContacts();
            System.out.println(m);
			
			
			//add contact
            //create a dummy contact object, that doesn't exist in the application
			Contact contact = new Contact();
			contact.setName("sdfsdfdsafwersdfsdf");
			contact.setSurname("sfdwerwefdsfdsdfsdfs");
			
			Contact c = b.getContact(contact.getName() + " " + contact.getSurname());
			if(c == null) {
				System.out.println("Contact doesnot exists");
				System.out.println("Now try to delete the non existing contact");
			} else {
				System.out.println("Contact exists");
			}
			
			//now lets try to delete the non existing contact
			boolean result = b.deleteContact(contact);
			Assert.assertEquals("Error in Main code. Not contact existed, still deleted returned true", result, false);
			
			
			System.out.println("completed testDeleteContact");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

