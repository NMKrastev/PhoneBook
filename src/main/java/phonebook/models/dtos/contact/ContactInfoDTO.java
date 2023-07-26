package phonebook.models.dtos.contact;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import phonebook.models.dtos.company.CompanyAddDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfoDTO {

    private String firstName;

    @NotNull
    @Size(min = 2)
    @NotBlank
    @NotEmpty
    private String lastName;

    private CompanyAddDTO company;

    @NotNull
    @NotBlank
    @NotEmpty
    private String phoneNumber;

    @NotNull
    @NotBlank
    @NotEmpty
    @Email
    private String email;

    @Positive
    private Integer age;
}
