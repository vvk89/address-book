# AddressBook

The goal of this application is to manage an address book.

## Build command

To build the application run `./gradlew build`
To execute the built app use `java -jar build/libs/AddressBook.jar`

## Requirements

The application allows to:
 1) Display the list of contacts
 2) Add a contact
 3) Display a contact
 4) Remove a contact
 5) Edit a contact
 6) Save the address book in a file

The address book has mandatory fields:
• Name
• Surname
• Telephone number
• Email

It can have also optional fields:
• Age
• Hair color

Each contact can belong to 3 different categories:
• Friends
• Family
• Acquaintance

Each category has a name.
The “family” category has a description indicating the family’s relationship (parent, granparent, son/daughter, aunt/uncle).
The “friends” category has a field indicating the number of friendship’s years.

The categorie, its name, the family’s relationship and the number of friendship’s years are also optional.

The user is able, through the command line, to add, display, remove or edit the contacts and each one of their properties.
The contacts and the related information are saved in one file.

The application creates a new address book on the first execution.
The user has the possibility to save it in a file when he quits the application.
The application asks to give a folder name and a file name to save the address book.
This folder name and this file name are saved in the resource file “config.properties“.
So, the application reads the content of the file containing the address book at the beginning of the next executions.
It this file was deleted, the application creates again a new address book.

When all the contacts are displayed, they are sorted alphabetically by name and surname.
