package phonebook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import phonebook.entities.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    Contact findByPhoneNumber(String phoneNumber);
}
