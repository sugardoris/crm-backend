package crm.crmbackend.dto;

import crm.crmbackend.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "Username must not be empty")
    @Length(max = 15, message = "Username too long")
    private String username;

    @NotBlank(message = "Password must not be empty")
    private String password;

    @NotBlank(message = "Name must not be empty")
    @Length(max = 100, message = "User name too long")
    private String name;

    private Role role;

    private Boolean active;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createDate;

    private LocalDateTime lastUpdate;
}
