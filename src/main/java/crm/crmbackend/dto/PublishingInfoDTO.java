package crm.crmbackend.dto;

import crm.crmbackend.enumeration.PublicationPeriod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublishingInfoDTO {

    private Long id;

    @NotNull(message = "First issue date must not be empty")
    private LocalDate firstIssueDate;

    @NotNull(message = "Period must not be empty")
    private PublicationPeriod issuePeriod;

    private String comesOut;

    @NotNull(message = "Price must not be empty")
    @Positive(message = "Price must be greater than zero")
    private BigDecimal price;
}
