package lu.jemmic.addressbook.utils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

import lu.jemmic.addressbook.model.AddressBook;

/**
 * Contains all methods used for the save data in a file and to load data from a
 * file.
 */
public class FileUtils {

	/**
	 * Loads data from the file config.properties.
	 *
	 * @return the properties contained if the file config.properties.
	 * @throws java.io.IOException
	 */
	public Properties loadFileProperties() throws java.io.IOException {
		InputStream input = FileUtils.class.getClassLoader().getResourceAsStream("config.properties");
		Properties prop = new Properties();

		// load a properties file
		prop.load(input);

		input.close();

		return prop;
	}

	/**
	 * Saves the properties in the file config.properties.
	 *
	 * @param prop
	 * @throws java.io.IOException
	 */
	public void saveFileProperties(Properties prop) throws java.io.IOException {
		String path = FileUtils.class.getResource("/main/resources/config.properties").getPath();
		OutputStream output = new FileOutputStream(path);

		// save properties to project root folder
		prop.store(output, null);

		output.close();
	}

	/**
	 * Loads the contacts from the file whose the filename is given as parameter.
	 * Returns an empty address book if the file is not found.
	 *
	 * @param fileName
	 * @return an address book containing these contacts.
	 * @throws java.io.IOException
	 */
	public AddressBook loadAddressBook(String fileName) throws java.io.IOException {
		AddressBook addressBook = null;
		ObjectInputStream ois = null;
		FileInputStream fichier = null;

		try {
			fichier = new FileInputStream(fileName);
			ois = new ObjectInputStream(fichier);
			addressBook = (AddressBook) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			addressBook = new AddressBook();
		} finally {
			if (ois != null) {
				ois.close();
			}
			if (fichier != null) {
				fichier.close();
			}
		}

		return addressBook;
	}

	/**
	 * Saves the contacts in the file whose the filename is given as parameter.
	 * An address book containing these contacts is given as parameter.
	 *
	 * @param addressBook
	 * @param fileName
	 * @throws java.io.IOException
	 */
	public void saveAddressBook(AddressBook addressBook, String fileName) throws java.io.IOException {
		ObjectOutputStream oos = null;
		FileOutputStream fichier = null;

		try {
			fichier = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(fichier);
			oos.writeObject(addressBook);
			oos.flush();
		} finally {
			if (oos != null) {
				oos.flush();
				oos.close();
			}
			if (fichier != null) {
				fichier.close();
			}
		}
	}

}
