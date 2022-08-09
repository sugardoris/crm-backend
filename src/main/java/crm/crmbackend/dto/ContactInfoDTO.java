package crm.crmbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfoDTO {

    private Long id;

    @Length(max = 50, message = "User name too long")
    private String firstName;

    @Length(max = 50, message = "User last name too long")
    private String lastName;

    @Length(max = 70, message = "Company name too long")
    private String companyName;

    @Length(max = 11, message = "OIB too long")
    private String oib;

    @Email
    @NotBlank(message = "Email must not be blank")
    private String email;

    @NotBlank(message = "Email must not be blank")
    @Length(max = 50, message = "Phone number too long")
    private String phone1;

    @Length(max = 50, message = "Phone number too long")
    private String phone2;

    @NotBlank(message = "Billing address must not be blank")
    @Length(max = 100, message = "Billing address too long")
    private String billingAddress;

    @NotNull
    private Boolean legalEntity;

    @NotNull(message = "City must not be null")
    private CityDTO city;
}
