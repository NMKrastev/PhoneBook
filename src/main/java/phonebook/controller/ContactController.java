package phonebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import phonebook.entities.dtos.company.CompanyAddDTO;
import phonebook.entities.dtos.contact.ContactAddDTO;
import phonebook.services.contact.ContactService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ContactController {

    private List<ContactAddDTO> contactsDTO;
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
        this.contactsDTO = new ArrayList<>();
    }

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {

        modelAndView.setViewName("index");
        //this.contacts = DBEntry.getAllContacts();
        this.contactsDTO = this.contactService.findAllContacts();

        modelAndView.addObject("contacts", contactsDTO);

        return modelAndView;
    }

    @PostMapping("/")
    public ModelAndView addContact(ModelAndView modelAndView, ContactAddDTO contactDTO) {

        if (this.contactService.addContact(contactDTO)) {
            modelAndView.setViewName("redirect:/");
        } else {
            modelAndView.setViewName("redirect:/error");
        }

        return modelAndView;
    }

    @GetMapping("/edit{phoneNumber}")
    public ModelAndView edit(ModelAndView modelAndView, @PathVariable String phoneNumber) {

        modelAndView.setViewName("edit");

        final ContactAddDTO contactDTO = this.contactService.findContact(phoneNumber);

        modelAndView.addObject(contactDTO);

        return modelAndView;
    }

    @PostMapping("/edit{phoneNumber}")
    public ModelAndView editContact(ModelAndView modelAndView, ContactAddDTO contactDTO, @PathVariable String phoneNumber) {

        final ContactAddDTO currentContact = this.contactService.findContact(phoneNumber);
        currentContact.setFirstName(contactDTO.getFirstName());
        currentContact.setLastName(contactDTO.getLastName());
        currentContact.setCompany(new CompanyAddDTO(contactDTO.getCompany().getName()));
        currentContact.setPhoneNumber(contactDTO.getPhoneNumber());
        currentContact.setEmail(contactDTO.getEmail());
        currentContact.setAge(contactDTO.getAge());

        if (this.contactService.editContact(contactDTO, phoneNumber)) {
            modelAndView.setViewName("redirect:/");
        } else {
            modelAndView.setViewName("redirect:/error");
        }

        return modelAndView;
    }

    @GetMapping("/delete{number}")
    public ModelAndView delete(@PathVariable String number, ModelAndView modelAndView) {
//        modelAndView.setViewName("delete");
//        modelAndView.addObject(this.contacts.get(number));
        return modelAndView;
    }

    @PostMapping("/delete{number}")
    public String deleteContact(@PathVariable String number) {
//        boolean isDeleted = DBEntry.deleteContact(number);
//        if (isDeleted) {
//            return "redirect:/";
//        }
        return "redirect:/error";
    }

    @GetMapping("/error")
    public ModelAndView error(ModelAndView modelAndView) {
        modelAndView.setViewName("error");
        modelAndView.addObject("error");
        return modelAndView;
    }

    @GetMapping("/allcontacts")
    public ModelAndView showAllContacts(ModelAndView modelAndView) {
        modelAndView.setViewName("allcontacts");
        modelAndView.addObject("contacts", this.contactsDTO);
        return modelAndView;
    }
}
