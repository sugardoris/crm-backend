package crm.crmbackend.controller;

import crm.crmbackend.dto.UserDTO;
import crm.crmbackend.enumeration.Role;
import crm.crmbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAllActiveUsersByRole(Role role) {
        return ResponseEntity.ok(userService.findUsersByRole(role));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> fetchUserDetails(@PathVariable Long id) {
        return ResponseEntity.ok(userService.fetchUserDetails(id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @PostMapping
    public ResponseEntity<Void> deactivateUser(@RequestBody UserDTO userDTO) {
        userService.deactivateUser(userDTO);
        return ResponseEntity.noContent().build();
    }
}
