package crm.crmbackend.service;

import crm.crmbackend.dto.UserDTO;
import crm.crmbackend.enumeration.Role;

import java.util.List;

public interface UserService {

    List<UserDTO> findUsersByRole(Role role);

    UserDTO fetchUserDetails(Long id);

    UserDTO saveUser(UserDTO userDTO);

    void deactivateUser(UserDTO userDTO);

}
