package crm.crmbackend.dto;

import crm.crmbackend.entity.City;
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

    private String firstName;

    private String lastName;

    private String companyName;

    private String oib;

    @Email
    @NotBlank(message = "Email must not be blank")
    private String email;

    @NotBlank(message = "Email must not be blank")
    @Length(message = "Phone number too long")
    private String phone1;

    private String phone2;

    @NotBlank(message = "Billing address must not be blank")
    @Length(message = "Billing address too long")
    private String billingAddress;

    @NotNull(message = "City must not be null")
    private CityDTO city;
}
