package phonebook.services.contact;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phonebook.models.entities.Company;
import phonebook.models.entities.Contact;
import phonebook.models.dtos.contact.ContactInfoDTO;
import phonebook.repositories.CompanyRepository;
import phonebook.repositories.ContactRepository;
import phonebook.utils.ValidationUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    private final ModelMapper mapper;
    private final ContactRepository contactRepository;
    private final CompanyRepository companyRepository;
    private final ValidationUtils validationUtils;

    public ContactServiceImpl(ModelMapper mapper, ContactRepository contactRepository,
                              CompanyRepository companyRepository, ValidationUtils validationUtils) {
        this.mapper = mapper;
        this.contactRepository = contactRepository;
        this.companyRepository = companyRepository;
        this.validationUtils = validationUtils;
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

        if (!this.validationUtils.isValid(contactDTO)) {
            return false;
        }

        if (contactDTO.getCompany().getName().isEmpty()) {
            return false;
        }

        Optional<Company> optionalCompany = this.companyRepository
                .findByName(contactDTO.getCompany().getName());

        if (optionalCompany.isEmpty()) {

            optionalCompany = Optional.of(new Company(contactDTO.getCompany().getName()));

            try {
                optionalCompany.ifPresent(this.companyRepository::saveAndFlush);
            } catch (Exception e) {
                return false;
            }
        }

        final Contact contact = this.mapper.map(contactDTO, Contact.class);

        contact.setCompany(optionalCompany.get());

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

        if (!this.validationUtils.isValid(contactDTO)) {
            return false;
        }

        final Contact contactToEdit = this.contactRepository.findByPhoneNumber(phoneNumber);

        if (!contactToEdit.getFirstName().equals(contactDTO.getFirstName())) {
            contactToEdit.setFirstName(contactDTO.getFirstName());
        }

        if (!contactToEdit.getLastName().equals(contactDTO.getLastName())) {
            contactToEdit.setLastName(contactDTO.getLastName());
        }

        if (!contactToEdit.getCompany().getName().equals(contactDTO.getCompany().getName())) {

            Optional<Company> optionalCompany = this.companyRepository.findByName(contactDTO.getCompany().getName());

            if (optionalCompany.isEmpty()) {

                optionalCompany = Optional.of(new Company(contactDTO.getCompany().getName()));
                this.companyRepository.saveAndFlush(optionalCompany.get());
                contactToEdit.setCompany(optionalCompany.get());

            } else {
                contactToEdit.setCompany(optionalCompany.get());
            }
        }

        if (!contactToEdit.getPhoneNumber().equals(contactDTO.getPhoneNumber())) {
            contactToEdit.setPhoneNumber(contactDTO.getPhoneNumber());
        }

        if (!contactToEdit.getEmail().equals(contactDTO.getEmail())) {
            contactToEdit.setEmail(contactDTO.getEmail());
        }

        if (!Objects.equals(contactToEdit.getAge(), contactDTO.getAge())) {
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
