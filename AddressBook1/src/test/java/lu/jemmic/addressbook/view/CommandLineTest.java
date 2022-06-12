package lu.jemmic.addressbook.view;

import junit.framework.TestCase;
import lu.jemmic.addressbook.controller.AddressBookController;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@PrepareForTest({CommandLine.class})
public class CommandLineTest {

    @InjectMocks
    CommandLine commandLine;

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
    public void testCreateContact() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String name = "Shiva";
        InputStream n = new ByteArrayInputStream(name.getBytes());
        System.setIn(n);
        String surname = "S";
        InputStream s = new ByteArrayInputStream(surname.getBytes());
        System.setIn(s);
        String phone = "068767667";
        InputStream p = new ByteArrayInputStream(phone.getBytes());
        System.setIn(s);
        String mail = "S@hm.com";
        InputStream m = new ByteArrayInputStream(mail.getBytes());
        System.setIn(m);
        String age = "39";
        InputStream a = new ByteArrayInputStream(age.getBytes());
        System.setIn(a);
        System.setIn(a);
        System.setIn(a);
        //when(commandLine.getStringField();
        commandLine.createContact();
    }

}