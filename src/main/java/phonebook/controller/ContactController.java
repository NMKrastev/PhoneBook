package phonebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import phonebook.database.DBEntry;
import phonebook.entity.Contact;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class ContactController {

    private Map<String, Contact> contacts;

    public ContactController() {
        this.contacts = new LinkedHashMap<>();
    }

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        contacts = DBEntry.getAllContacts();
        modelAndView.addObject("contacts", contacts);
        return modelAndView;
    }

    @PostMapping("/")
    public String addContact(Contact contact) {
        if (!contact.getFirstName().isEmpty() && !contact.getLastName().isEmpty() && !contact.getCompany().isEmpty() &&
                !contact.getNumber().isEmpty() && !contact.getEmail().isEmpty() && !contact.getAge().isEmpty()) {
            this.contacts.put(contact.getNumber(), contact);
            boolean isAdded = DBEntry.addContact(contact);
            if (isAdded) {
                return "redirect:/";
            }
        }
        return "redirect:/error";
    }

    @GetMapping("/edit{number}")
    public ModelAndView edit(@PathVariable String number, ModelAndView modelAndView) {
        modelAndView.setViewName("edit");
        modelAndView.addObject(this.contacts.get(number));
        return modelAndView;
    }

    @PostMapping("/edit{number}")
    public String editContact(@PathVariable String number, Contact contact) {
        Contact currentContact = this.contacts.get(number);
        currentContact.setFirstName(contact.getFirstName());
        currentContact.setLastName(contact.getLastName());
        currentContact.setCompany(contact.getCompany());
        currentContact.setNumber(contact.getNumber());
        currentContact.setEmail(contact.getEmail());
        currentContact.setAge(contact.getAge());
        boolean isEdited = DBEntry.editContact(currentContact, number);
        if (isEdited) {
            return "redirect:/";
        }
        return "redirect:/error";
    }

    @GetMapping("/delete{number}")
    public ModelAndView delete(@PathVariable String number, ModelAndView modelAndView) {
        modelAndView.setViewName("delete");
        modelAndView.addObject(this.contacts.get(number));
        return modelAndView;
    }

    @PostMapping("/delete{number}")
    public String deleteContact(@PathVariable String number) {
        boolean isDeleted = DBEntry.deleteContact(number);
        if (isDeleted) {
            return "redirect:/";
        }
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
        modelAndView.addObject("contacts", contacts);
        return modelAndView;
    }
}
