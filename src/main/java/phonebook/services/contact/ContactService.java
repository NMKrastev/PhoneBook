package phonebook.services.contact;

import phonebook.entities.dtos.contact.ContactInfoDTO;

import java.util.List;

public interface ContactService {

    List<ContactInfoDTO> findAllContacts();

    boolean addContact(ContactInfoDTO contactDTO);

    ContactInfoDTO findContact(String phoneNumber);

    boolean editContact(ContactInfoDTO contactDTO, String phoneNumber);

    boolean deleteContact(String phoneNumber);
}
