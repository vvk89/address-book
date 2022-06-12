package lu.jemmic.addressbook;

import lu.jemmic.addressbook.controller.AddressBookController;

/**
 * Contains the main method.
 */
public class AddressBookMain {

	/**
	 * The main method.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		AddressBookController controller = new AddressBookController();
		controller.mainProcess();
	}

}
