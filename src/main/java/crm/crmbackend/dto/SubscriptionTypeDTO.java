package crm.crmbackend.dto;

import crm.crmbackend.enumeration.SubscriptionPeriod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionTypeDTO {

    private Long id;

    @NotBlank(message = "Subscription type name must not be blank")
    @Length(message = "Subscription type name too long")
    private String name;

    @PositiveOrZero
    private BigDecimal discount;

    @NotNull(message = "Period must not be empty")
    private SubscriptionPeriod subscriptionPeriod;

    private Boolean active;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createDate;

    private LocalDateTime lastUpdate;
}
