package phonebook.database;

import phonebook.entities.Contact;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBEntry {

    private static final String ADD_CONTACT_TO_DB =
            "INSERT INTO contacts(firstName,lastName,company,phoneNumber,email,age) VALUE(?1, ?2, ?3, ?4, ?5, ?6)";
    private static final String GET_ALL_CONTACTS =
            "SELECT c FROM Contact AS c ORDER BY c.firstName";
    private static final String SELECT_CONTACT_BY_PHONE_NUMBER =
            "SELECT c FROM Contact c WHERE c.phoneNumber = ?1";
/*    private static final EntityManager manager = DBConnection.createConnection();

    public static boolean addContact(Contact contact) {
        boolean isAdded = false;
        try {
            //Connection connection = DBConnection.createConnection();

            *//*String addQuery = "INSERT INTO contacts(firstName,lastName,company,phoneNumber,email,age) VALUE(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(addQuery);
            preparedStatement.setString(1, contact.getFirstName());
            preparedStatement.setString(2, contact.getLastName());
            preparedStatement.setString(3, contact.getCompany());
            preparedStatement.setString(4, contact.getNumber());
            preparedStatement.setString(5, contact.getEmail());
            preparedStatement.setInt(6, Integer.parseInt(contact.getAge()));
            preparedStatement.executeUpdate();*//*

            manager.persist(contact);

            isAdded = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isAdded;
    }

    public static boolean editContact(Contact contact, String phoneNumber) {

        boolean isEdited = false;

        try {

            Contact contactFromDB =
                    manager.createQuery(SELECT_CONTACT_BY_PHONE_NUMBER, Contact.class)
                            .setParameter(1, phoneNumber)
                            .getSingleResult();

            contactFromDB.setFirstName(contact.getFirstName());
            contactFromDB.setLastName(contact.getLastName());
            contactFromDB.setCompany_id(contact.getCompany_id());
            contactFromDB.setPhoneNumber(contact.getPhoneNumber());
            contactFromDB.setEmail(contact.getEmail());
            contactFromDB.setAge(contact.getAge());

            manager.persist(contactFromDB);

            *//*Connection connection = DBConnection.createConnection();
            String editQuery = "UPDATE contacts SET firstName=?,lastName=?,company=?,phoneNumber=?,email=?,age=? WHERE phoneNumber=?";
            PreparedStatement preparedStatement = connection.prepareStatement(editQuery);
            preparedStatement.setString(1, contact.getFirstName());
            preparedStatement.setString(2, contact.getLastName());
            preparedStatement.setString(3, contact.getCompany());
            preparedStatement.setString(4, contact.getPhoneNumber());
            preparedStatement.setString(5, contact.getEmail());
            preparedStatement.setInt(6, Integer.parseInt(contact.getAge()));
            preparedStatement.setString(7, phoneNumber);
            preparedStatement.executeUpdate();*//*
            isEdited = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isEdited;
    }

    public static boolean deleteContact(String phoneNumber) {

        boolean isDeleted = false;

        try {

            Contact contact =
                    manager.createQuery(SELECT_CONTACT_BY_PHONE_NUMBER, Contact.class)
                            .setParameter(1, phoneNumber)
                            .getSingleResult();

            manager.remove(contact);

            *//*Connection connection = DBConnection.createConnection();
            String deleteQuery = "DELETE FROM contacts WHERE phoneNumber=?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.executeUpdate();*//*
            isDeleted = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isDeleted;
    }


    public static Map<String, Contact> getAllContacts() {

        Map<String, Contact> contacts = new LinkedHashMap<>();

        try {

            List<Contact> resultList = manager.createQuery(GET_ALL_CONTACTS, Contact.class).getResultList();

            for (Contact contact : resultList) {
                contacts.put(contact.getPhoneNumber(), contact);
            }

            *//*Statement statement = connection.createStatement();
            String sql = "SELECT * FROM contacts ORDER BY firstName ASC";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Contact contact = new Contact(resultSet.getString("firstName"), resultSet.getString("lastName"),
                        resultSet.getString("company"), resultSet.getString("phoneNumber"),
                        resultSet.getString("email"), resultSet.getString("age"));
                contacts.put(contact.getPhoneNumber(), contact);
            }*//*
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contacts;
    }*/
}
