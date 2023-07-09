package phonebook.services.contact;

import phonebook.entities.Contact;
import phonebook.entities.dtos.contact.ContactAddDTO;

import java.util.List;

public interface ContactService {

    List<ContactAddDTO> findAllContacts();

    boolean addContact(ContactAddDTO contactDTO);

    ContactAddDTO findContact(String phoneNumber);

    boolean editContact(ContactAddDTO contactDTO, String phoneNumber);
}
