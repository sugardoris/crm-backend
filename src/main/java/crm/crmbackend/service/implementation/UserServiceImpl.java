package crm.crmbackend.service.implementation;

import crm.crmbackend.dto.UserDTO;
import crm.crmbackend.entity.User;
import crm.crmbackend.enumeration.Role;
import crm.crmbackend.repository.UserRepository;
import crm.crmbackend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public List<UserDTO> findUsersByRole(Role role) {
        return userRepository
                .findAllByRoleAndActiveTrue(role)
                .stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO fetchUserDetails(Long id) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User savedUser = userRepository.save(mapper.map(userDTO, User.class));
        return mapper.map(savedUser, UserDTO.class);
    }

    @Override
    public void deactivateUser(UserDTO userDTO) {
        userDTO.setActive(false);
        userRepository.save(mapper.map(userDTO, User.class));
    }
}
