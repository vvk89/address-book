package lu.jemmic.addressbook.view;

import lu.jemmic.addressbook.controller.AddressBookController;
import lu.jemmic.addressbook.model.Category;
import lu.jemmic.addressbook.model.Contact;
import lu.jemmic.addressbook.model.Hair;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@PrepareForTest({CommandLine.class})
public class CommandLineTest {

    @InjectMocks
    CommandLine commandLine;

    @Mock
    Contact contact;

    @BeforeClass
    public static void setUpBeforeClass() {
    }

    @AfterClass
    public static void tearDownAfterClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown(){
    }

    @Test
    public void testCreateContact() {
        String userInput = String.format("Dan%sVega%s0687687989%sdanvega@gmail.com%sy%sBlack%sCategoryFamily%s3%sy%sy",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);
        commandLine.createContact();
    }

    @Test
    public void testDisplayContactDetail() {
        when(contact.getHair()).thenReturn(new Hair("black"));
        when(contact.getCategory()).thenReturn(new Category());
        commandLine.displayContactDetail(new Contact());
    }

    @Test
    public void testEditContact() {
        System.setIn(new ByteArrayInputStream("2\n".getBytes()));
        String userInput = String.format("Dan%sVega%s0687687989%sdanvega@gmail.com%s32%sBlack%sCategoryFamily%s3%sy%sy",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);
        System.setIn(new ByteArrayInputStream("8\n".getBytes()));
        Map<AddressBookController.ContactData, String> contactDataStringMap = new HashMap<>();
        contactDataStringMap.put(AddressBookController.ContactData.NAME, "Vivek");
        contactDataStringMap.put(AddressBookController.ContactData.SURNAME, "V");
        contactDataStringMap.put(AddressBookController.ContactData.TELEPHONE_NUMBER, "0685554341");
        contactDataStringMap.put(AddressBookController.ContactData.EMAIL, "B@GMAIL.COM");
        contactDataStringMap.put(AddressBookController.ContactData.AGE, "35");
        contactDataStringMap.put(AddressBookController.ContactData.HAIR_COLOR, "BLACK");
        contactDataStringMap.put(AddressBookController.ContactData.CATEGORY_TYPE, "CategoryFamily");
        PowerMockito.when(contact.getCategory()).thenReturn(new Category());
        commandLine.editContact(new Contact(), contactDataStringMap);
    }
}