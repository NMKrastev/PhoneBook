package phonebook.database;

import phonebook.entity.Contact;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class DBEntry {

    public static boolean addContact(Contact contact) {
        boolean isAdded = false;
        try {
            Connection connection = DBConnection.createConnection();
            String addQuery = "INSERT INTO contacts(firstName,lastName,company,phoneNumber,email,age) VALUE(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(addQuery);
            preparedStatement.setString(1, contact.getFirstName());
            preparedStatement.setString(2, contact.getLastName());
            preparedStatement.setString(3, contact.getCompany());
            preparedStatement.setString(4, contact.getNumber());
            preparedStatement.setString(5, contact.getEmail());
            preparedStatement.setInt(6, Integer.parseInt(contact.getAge()));
            preparedStatement.executeUpdate();
            isAdded = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isAdded;
    }

    public static boolean editContact(Contact contact, String number) {
        boolean isEdited = false;
        try {
            Connection connection = DBConnection.createConnection();
            String editQuery = "UPDATE contacts SET firstName=?,lastName=?,company=?,phoneNumber=?,email=?,age=? WHERE phoneNumber=?";
            PreparedStatement preparedStatement = connection.prepareStatement(editQuery);
            preparedStatement.setString(1, contact.getFirstName());
            preparedStatement.setString(2, contact.getLastName());
            preparedStatement.setString(3, contact.getCompany());
            preparedStatement.setString(4, contact.getNumber());
            preparedStatement.setString(5, contact.getEmail());
            preparedStatement.setInt(6, Integer.parseInt(contact.getAge()));
            preparedStatement.setString(7, number);
            preparedStatement.executeUpdate();
            isEdited = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isEdited;
    }

    public static Map<String, Contact> getAllCustomers() {
        Map<String, Contact> contacts = new LinkedHashMap<>();
        try {
            Connection connection = DBConnection.createConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM contacts ORDER BY firstName ASC";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Contact contact = new Contact(resultSet.getString("firstName"), resultSet.getString("lastName"),
                        resultSet.getString("company"), resultSet.getString("phoneNumber"),
                        resultSet.getString("email"), resultSet.getString("age"));
                contacts.put(contact.getNumber(), contact);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contacts;
    }
}
