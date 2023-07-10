package phonebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import phonebook.entities.dtos.contact.ContactInfoDTO;
import phonebook.services.contact.ContactService;

import java.util.List;

@Controller
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {

        modelAndView.setViewName("index");
        //this.contacts = DBEntry.getAllContacts();
        final List<ContactInfoDTO> contactsDTO = this.contactService.findAllContacts();

        modelAndView.addObject("contacts", contactsDTO);

        return modelAndView;
    }

    @PostMapping("/")
    public ModelAndView addContact(ModelAndView modelAndView, ContactInfoDTO contactDTO) {

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

        final ContactInfoDTO contactDTO = this.contactService.findContact(phoneNumber);

        modelAndView.addObject(contactDTO);

        return modelAndView;
    }

    @PostMapping("/edit{phoneNumber}")
    public ModelAndView editContact(ModelAndView modelAndView, ContactInfoDTO contactDTO, @PathVariable String phoneNumber) {

        if (this.contactService.editContact(contactDTO, phoneNumber)) {
            modelAndView.setViewName("redirect:/");
        } else {
            modelAndView.setViewName("redirect:/error");
        }

        return modelAndView;
    }

    @GetMapping("/delete{phoneNumber}")
    public ModelAndView delete(ModelAndView modelAndView, @PathVariable String phoneNumber) {

        modelAndView.setViewName("delete");

        final ContactInfoDTO contactDTO = this.contactService.findContact(phoneNumber);

        modelAndView.addObject(contactDTO);

        return modelAndView;
    }

    @PostMapping("/delete{phoneNumber}")
    public ModelAndView deleteContact(ModelAndView modelAndView, @PathVariable String phoneNumber) {

        if (this.contactService.deleteContact(phoneNumber)) {
            modelAndView.setViewName("redirect:/");
        } else {
            modelAndView.setViewName("redirect:/error");
        }

        return modelAndView;
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

        final List<ContactInfoDTO> contactsDTO = this.contactService.findAllContacts();

        modelAndView.addObject("contacts", contactsDTO);

        return modelAndView;
    }
}
