package lu.jemmic.addressbook.view;

import junit.framework.TestCase;
import lu.jemmic.addressbook.controller.AddressBookController;
import lu.jemmic.addressbook.model.Category;
import lu.jemmic.addressbook.model.Contact;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.powermock.api.mockito.PowerMockito.when;


@RunWith(PowerMockRunner.class)
@PrepareForTest({Scanner.class, CommandLine.class})
public class CommandLineTest{

    CommandLine commandLine = new CommandLine();

    //@Mock
    //Contact contact;
    Contact contact = PowerMockito.mock(Contact.class);

    @BeforeClass public static void setUpBeforeClass() {
    }
    public void testDisplayMainMenu() {
    }

    public void testDisplayMenu() {
    }

    public void testGetMenuChoice() {
    }

    public void testTestGetMenuChoice() {
    }

    public void testGetFilename() {
    }

    public void testDisplayContactDetail() {
    }

    public void testCreateContact() {
    }

    @Test
    public void testEditContact() throws Exception {
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        //%s0687687989%sdanvega@gmail.com%s32%sBlack%sCategoryFamily%s3%sy%sy
        String userInput = String.format("Dan%sVega",
        System.lineSeparator()
        );
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));

        Map<AddressBookController.ContactData, String> contactDataStringMap = new HashMap<>();
        contactDataStringMap.put(AddressBookController.ContactData.NAME, "Vivek");
        contactDataStringMap.put(AddressBookController.ContactData.SURNAME, "V");
        contactDataStringMap.put(AddressBookController.ContactData.TELEPHONE_NUMBER, "0685554341");
        contactDataStringMap.put(AddressBookController.ContactData.EMAIL, "B@GMAIL.COM");
        contactDataStringMap.put(AddressBookController.ContactData.AGE, "35");
        contactDataStringMap.put(AddressBookController.ContactData.HAIR_COLOR, "BLACK");
        contactDataStringMap.put(AddressBookController.ContactData.CATEGORY_TYPE, "CategoryFamily");
        when(contact.getCategory()).thenReturn(new Category());
        commandLine.editContact(new Contact(), contactDataStringMap);
    }

    public void testDisplayMessage() {
    }
}