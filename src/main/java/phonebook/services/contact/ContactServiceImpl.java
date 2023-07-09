package phonebook.services.contact;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phonebook.entities.Company;
import phonebook.entities.Contact;
import phonebook.entities.dtos.contact.ContactAddDTO;
import phonebook.repositories.CompanyRepository;
import phonebook.repositories.ContactRepository;

import java.util.Collections;
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
    public List<ContactAddDTO> findAllContacts() {

        final List<Contact> contacts = this.contactRepository.findAll();

        return contacts
                .stream()
                .map(contact -> this.mapper.map(contact, ContactAddDTO.class))
                .toList();
    }

    @Override
    public boolean addContact(ContactAddDTO contactDTO) {

        final Contact contact = this.mapper.map(contactDTO, Contact.class);

        if (contact.getCompany().getName() == null || contact.getCompany().getName().isEmpty()) {
            return false;
        }

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
    public ContactAddDTO findContact(String phoneNumber) {

        final Contact contact = this.contactRepository.findByPhoneNumber(phoneNumber);

        return this.mapper.map(contact, ContactAddDTO.class);
    }

    @Override
    @Transactional
    public boolean editContact(ContactAddDTO contactDTO, String phoneNumber) {

        final Contact contact = this.contactRepository.findByPhoneNumber(phoneNumber);

        this.mapper.map(contactDTO, contact);

        try {
            this.contactRepository.saveAndFlush(contact);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
