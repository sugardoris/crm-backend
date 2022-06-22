package crm.crmbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO {

    @NotNull(message = "Postode must not be empty")
    @Min(value = 10000, message = "There are no postcodes before 10 000")
    @Max(value = 53296, message = "There are no postcodes after 53 296")
    private Integer postcode;

    @NotBlank(message = "Name must not be empty")
    @Length(max = 100, message = "City name too long")
    private String name;
}
