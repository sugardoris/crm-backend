package crm.crmbackend.security.web;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class LoginDTO {

    @NotNull
    private String username;
    @NotNull
    private String password;
}
