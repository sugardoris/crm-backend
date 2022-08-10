package crm.crmbackend.dto;

import crm.crmbackend.enumeration.TicketType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {

    private Long id;

    @NotNull(message = "A ticket must have a subscriber")
    private Long subscriberId;

    @NotNull(message = "A ticket must have a type")
    private TicketType type;

    @NotBlank(message = "Ticket description must not be empty")
    private String description;

    private Boolean resolved;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createDate;

    private LocalDateTime lastUpdate;
}
