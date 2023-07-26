package phonebook.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
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
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne(optional = false)
    @Fetch(FetchMode.JOIN)
    private Company company;

    @Column(name = "phone_number", unique = true, nullable = false)
    @Pattern(regexp = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{3,4})(?: *x(\\d+))?\\s*$")
    private String phoneNumber;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private Integer age;

}
