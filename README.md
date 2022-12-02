# Phonebook

<p>

Basic phonebook web application given as a task from SoftUni in Java Fundamentals Module.</br>


1. The application runs on Java 18.
2. In order to start it, first you need to set the version of the SDK to 18 in the project settings of your IDE.</br>
3. The version of the SDK is also specified in the pom.xml file.</br>

</p>
<p>

### UPDATE:</br>

I have made changes to the "skeleton" of the application 

**The changes are as follows:**
1. I made some changes to the application that includes:
   * Changed the "``Name``" field to "``First Name``".
   * Added new field "``Last Name``".
   * Added new field "``Company``".
   * Added new field "``Email``".
   * Added new field "``Age``".
2. Added the new values to the constructor and created new java classes used for DB connection and adding DB entries.
3. Created method for adding new contact to the DB, if all the fields are filled.
4. I used the same DB as of my previous project: [ContactsBook-DB](https://github.com/NMKrastev/ContactsBook-DB). You can use this project as reference on how to configure the table in your DB.
5. Starting the application loads the data from the DB table ``contacts`` and shows it on screen. If you have data in your table it will be visualized from the start.
</p>

### UPDATE:
I have added the simple ``Edit`` option and functionality behind it.
1. Click on the ``Edit`` button of the contact you wish to change.
2. Change any or multiple values and click Save.
3. You will be redirected to the ``home`` page, where your changes will be visible.
4. You can click on ``Cancel`` button to return to ``home`` page.

### UPDATE:
I have added another simple ``Delete`` option and functionality behind it.
1. Click on the ``Delete`` button of the contact you wish to remove.
2. You will be redirected to the delete page of the specific contact you selected.
3. Click on the ``Delete`` button to permanently remove that contact from your phonebook.
4. You can click on ``Cancel`` button to return to ``home`` page.

* Note: Feel free to make any changes to the application!

**Screenshots:**
![phoneBookScreenshot](https://github.com/NMKrastev/phonebook/blob/main/screenshots/Phonebook.png?raw=true "phonebook")
![editContactScreenshot](https://github.com/NMKrastev/phonebook/blob/main/screenshots/editContact.png?raw=true "editContact")
![deleteContactScreenshot](https://github.com/NMKrastev/phonebook/blob/main/screenshots/deleteContact.png?raw=true "deleteContact")
![DBEntryScreenshot](https://github.com/NMKrastev/phonebook/blob/main/screenshots/DBEntries.PNG?raw=true "DBEntries")