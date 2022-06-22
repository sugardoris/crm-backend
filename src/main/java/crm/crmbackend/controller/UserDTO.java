package crm.crmbackend.controller;

import crm.crmbackend.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @Length(max = 100, message = "Name too long")
    private String name;

    @NotNull(message = "Role must not be empty")
    private Role role;

    @NotNull
    private Boolean active;
}
