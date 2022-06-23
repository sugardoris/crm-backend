package crm.crmbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriberDTO {

    private Long id;

    @NotNull
    private Boolean legalEntity;

    @NotNull
    private Boolean active;

    @NotNull(message = "Contact info must not be null")
    private ContactInfoDTO contactInfo;
}
