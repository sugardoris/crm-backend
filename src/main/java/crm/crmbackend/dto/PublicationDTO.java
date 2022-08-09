package crm.crmbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicationDTO {

    private Long id;

    @NotBlank(message = "Name must not be empty")
    private String name;

    @NotNull
    private Boolean active;

    private Long createdBy;

    private Long updatedBy;

    private LocalDateTime createDate;

    private LocalDateTime lastUpdate;

    @NotNull(message = "Publishing info must not be empty")
    private PublishingInfoDTO publishingInfo;
}
