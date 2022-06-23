package crm.crmbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicationDTO {

    private Long id;

    @NotBlank(message = "Name must not be empty")
    private String name;

    @NotBlank(message = "Description must not be empty")
    private String description;

    @NotNull(message = "Price must not be empty")
    @Positive(message = "Price must be greater than zero")
    private BigDecimal price;

    @NotNull
    private Boolean active;

    @NotNull(message = "Publishing info must not be empty")
    private PublishingInfoDTO publishingInfo;
}
