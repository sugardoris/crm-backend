package crm.crmbackend.dto;

import crm.crmbackend.enumeration.Period;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublishingInfoDTO {

    private Long id;

    @NotNull(message = "Starting date must not be empty")
    private LocalDate startDate;

    @NotNull(message = "Period must not be empty")
    private Period period;

    private String month;

    private String dateOfMonth;

    private String dayOfWeek;

    private LocalDate nextIssue;
}
