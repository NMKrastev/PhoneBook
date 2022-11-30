package phonebook.entity;

public class Contact {

    private String firstName;
    private String lastName;
    private String company;
    private String number;
    private String email;
    private String age;

    public Contact() {

    }

    public Contact(String firstName, String lastName, String company, String number, String email, String age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.number = number;
        this.email = email;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
