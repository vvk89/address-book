//package lu.jemmic.addressbook.view;
//
//import lu.jemmic.addressbook.controller.AddressBookController;
//import lu.jemmic.addressbook.model.Category;
//import lu.jemmic.addressbook.model.Contact;
//import lu.jemmic.addressbook.model.Hair;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
//import java.security.SecureRandom;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//
//import static org.mockito.Matchers.anyInt;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class CommandLineMockItToTest {
//
//    @InjectMocks
//    CommandLine commandLine;
//
//    @Mock
//    Contact contact;
//
//    @Mock
//    Scanner scanner;
//
//    private final InputStream systemIn = System.in;
//
//    @BeforeClass public static void setUpBeforeClass() {
//
//    }
//
//    @AfterClass public static void tearDownAfterClass() {
//    }
//
//    @Before public void setUp() {
//    }
//
//    @After public void tearDown() {
//    }
//
//    @Test public void testCreateContact() {
//        String userInput = String.format("Dan%sVega%s0687687989%sdanvega@gmail.com%sy%sBlack%sCategoryFamily%s3%sy%sy",
//                System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator(),
//                System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator(),
//                System.lineSeparator());
//        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
//        System.setIn(bais);
//        commandLine.createContact();
//    }
//
//    @Test public void testDisplayContactDetail() {
//        when(contact.getHair()).thenReturn(new Hair("black"));
//        when(contact.getCategory()).thenReturn(new Category());
//        commandLine.displayContactDetail(new Contact());
//    }
//
//    // ToDo powermockito for secure random need to be added.
//    @Test public void testEditContact() throws Exception {
////        SecureRandom mockRandom = PowerMockito.mock(SecureRandom.class);
////        PowerMockito.whenNew(SecureRandom.class).withNoArguments().thenReturn(mockRandom);
////        PowerMockito.when(mockRandom.nextInt(anyInt())).thenReturn(3, 0);
//
////        Scanner scanner = new Scanner(testInput);
////        assertThat(scanner.nextLine()).isEqualTo("line1");
//        System.setIn(systemIn);
//        String userInput = String.format("Dan%sVega%s0687687989%sdanvega@gmail.com%s32%sBlack%sCategoryFamily%s3%sy%sy",
//                System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator(),
//                System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator(),
//                System.lineSeparator());
//        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
//        System.setIn(bais);
//        Map<AddressBookController.ContactData, String> contactDataStringMap = new HashMap<>();
//        contactDataStringMap.put(AddressBookController.ContactData.NAME, "Vivek");
//        contactDataStringMap.put(AddressBookController.ContactData.SURNAME, "V");
//        contactDataStringMap.put(AddressBookController.ContactData.TELEPHONE_NUMBER, "0685554341");
//        contactDataStringMap.put(AddressBookController.ContactData.EMAIL, "B@GMAIL.COM");
//        contactDataStringMap.put(AddressBookController.ContactData.AGE, "35");
//        contactDataStringMap.put(AddressBookController.ContactData.HAIR_COLOR, "BLACK");
//        contactDataStringMap.put(AddressBookController.ContactData.CATEGORY_TYPE, "CategoryFamily");
//        when(contact.getCategory()).thenReturn(new Category());
//        commandLine.editContact(new Contact(), contactDataStringMap);
//    }
//}