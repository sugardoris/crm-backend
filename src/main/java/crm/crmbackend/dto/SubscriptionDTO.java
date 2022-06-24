package crm.crmbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDTO {

    private Long id;

    @NotNull(message = "Subscription type must not be empty")
    private SubscriptionTypeDTO subscriptionType;

    @NotNull(message = "Subscription must have a subscriber")
    private SubscriberDTO subscriber;

    @NotNull(message = "Subscription must have a publication")
    private PublicationDTO publication;

    @NotNull(message = "Subscription must have a starting date")
    private LocalDate dateStarted;

    private LocalDate dateEnded;

    @NotNull(message = "Subscription must have a price")
    private BigDecimal price;
}
