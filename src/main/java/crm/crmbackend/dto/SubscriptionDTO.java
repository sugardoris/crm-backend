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

    @NotNull(message = "Subscription type must not be empty")
    private SubscriptionTypeDTO subscriptionType;

    @NotNull(message = "Subscription must have a subscriber")
    private Long subscriberId;

    @NotNull(message = "Subscription must have a publication")
    private Long publicationId;

    @NotNull(message = "Subscription must have a starting date")
    private LocalDate dateStarted;

    private LocalDate dateEnded;

    @NotNull(message = "Subscription must have a price")
    private BigDecimal price;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createDate;

    private LocalDateTime lastUpdate;
}
