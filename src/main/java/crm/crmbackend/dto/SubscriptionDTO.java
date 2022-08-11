package crm.crmbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDTO {

    private Long id;

    private Long subscriptionTypeId;

    private Long subscriberId;

    private Long publicationId;

    @NotNull(message = "Subscription must have a starting date")
    private LocalDate dateStarted;

    private LocalDate dateEnded;

    private BigDecimal price;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createDate;

    private LocalDateTime lastUpdate;
}
