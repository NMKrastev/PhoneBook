package phonebook.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import phonebook.database.DBEntry;
import phonebook.entity.Contact;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ContactController {

    private List<Contact> contacts;

    public ContactController() {
        this.contacts = new ArrayList<>();
    }

    @GetMapping("/")
    public ModelAndView index (ModelAndView modelAndView) throws SQLException, ClassNotFoundException {
        modelAndView.setViewName("index");
        if (contacts.isEmpty()) {
            contacts = DBEntry.getAllCustomers();
        }
        modelAndView.addObject("contacts", contacts);
        return modelAndView;
    }

    @PostMapping("/")
    public String addContact(Contact contact) {
        if (!contact.getFirstName().isEmpty() && !contact.getLastName().isEmpty() && !contact.getCompany().isEmpty() &&
                !contact.getNumber().isEmpty() && !contact.getEmail().isEmpty() && !contact.getAge().isEmpty()) {
            this.contacts.add(contact);
           boolean isAdded = DBEntry.addContact(contact);
           if (isAdded) {
               return "redirect:/";
           }
        }
        return "redirect:/error";
    }
}
