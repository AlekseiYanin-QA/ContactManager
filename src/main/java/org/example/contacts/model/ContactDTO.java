package org.example.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContactDTO {

    @JsonProperty("id")
    private Long id; // Поле для идентификатора контакта

    @JsonProperty("firstName")
    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @JsonProperty("lastName")
    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @JsonProperty("phoneNumber")
    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;

    @JsonProperty("email")
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    public ContactDTO() {}

    public ContactDTO(Long id, String firstName, String lastName, String phoneNumber, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Override
    public String toString() {
        return "ContactDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}