package phonebook.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contacts")
public class Contact extends BaseEntity {

    @Column(name = "first_name")
    @Size(min = 2, max = 25)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    private Company company;

    @Column(name = "phone_number", unique = true, nullable = false)
    @Pattern(regexp = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{3,4})(?: *x(\\d+))?\\s*$")
    private String phoneNumber;

    @Column(unique = true)
    @Email
    private String email;

    @Column
    @Min(0)
    private int age;

}
