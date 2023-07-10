package phonebook.services.contact;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phonebook.entities.Company;
import phonebook.entities.Contact;
import phonebook.entities.dtos.contact.ContactInfoDTO;
import phonebook.repositories.CompanyRepository;
import phonebook.repositories.ContactRepository;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    private final ModelMapper mapper;
    private final ContactRepository contactRepository;
    private final CompanyRepository companyRepository;

    public ContactServiceImpl(ModelMapper mapper, ContactRepository contactRepository, CompanyRepository companyRepository) {
        this.mapper = mapper;
        this.contactRepository = contactRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public List<ContactInfoDTO> findAllContacts() {

        final List<Contact> contacts = this.contactRepository.findAll();

        return contacts
                .stream()
                .map(contact -> this.mapper.map(contact, ContactInfoDTO.class))
                .toList();
    }

    @Override
    public boolean addContact(ContactInfoDTO contactDTO) {

        final Contact contact = this.mapper.map(contactDTO, Contact.class);

        Company company = this.companyRepository
                .findByName(contact.getCompany().getName());

        if (company == null) {

            company = new Company(contact.getCompany().getName());

            try {
                this.companyRepository.saveAndFlush(company);
            } catch (Exception e) {
                return false;
            }
        }

        contact.setCompany(company);

        try {
            this.contactRepository.saveAndFlush(contact);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ContactInfoDTO findContact(String phoneNumber) {

        final Contact contact = this.contactRepository.findByPhoneNumber(phoneNumber);

        return this.mapper.map(contact, ContactInfoDTO.class);
    }

    @Override
    @Transactional
    public boolean editContact(ContactInfoDTO contactDTO, String phoneNumber) {

        final Contact contactToEdit = this.contactRepository.findByPhoneNumber(phoneNumber);

        if (!contactToEdit.getFirstName().equals(contactDTO.getFirstName())) {
            contactToEdit.setFirstName(contactDTO.getFirstName());
        }

        if (!contactToEdit.getLastName().equals(contactDTO.getLastName())) {
            contactToEdit.setLastName(contactDTO.getLastName());
        }

        if (!contactToEdit.getCompany().getName().equals(contactDTO.getCompany().getName())) {

            Company company = this.companyRepository.findByName(contactDTO.getCompany().getName());

            if (company == null) {

                company = new Company(contactDTO.getCompany().getName());
                this.companyRepository.saveAndFlush(company);
                contactToEdit.setCompany(company);

            } else {
                contactToEdit.setCompany(company);
            }
        }

        if (!contactToEdit.getPhoneNumber().equals(contactDTO.getPhoneNumber())) {
            contactToEdit.setPhoneNumber(contactDTO.getPhoneNumber());
        }

        if (!contactToEdit.getEmail().equals(contactDTO.getEmail())) {
            contactToEdit.setEmail(contactDTO.getEmail());
        }

        if (contactToEdit.getAge() != contactDTO.getAge()) {
            contactToEdit.setAge(contactDTO.getAge());
        }

        try {
            this.contactRepository.saveAndFlush(contactToEdit);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean deleteContact(String phoneNumber) {

        try {
            this.contactRepository.deleteByPhoneNumber(phoneNumber);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
