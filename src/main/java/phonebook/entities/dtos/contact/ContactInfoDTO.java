package phonebook.entities.dtos.contact;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import phonebook.entities.dtos.company.CompanyAddDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfoDTO {

    private String firstName;

    private String lastName;

    private CompanyAddDTO company;

    private String phoneNumber;

    private String email;

    private int age;
}
