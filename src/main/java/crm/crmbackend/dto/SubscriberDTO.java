package crm.crmbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriberDTO {

    private Long id;

    @NotNull
    private Boolean active;

    private Long createdBy;

    private Long updatedBy;

    private LocalDateTime createDate;

    private LocalDateTime lastUpdate;

    @NotNull(message = "Contact info must not be null")
    private ContactInfoDTO contactInfo;
}
