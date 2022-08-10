package crm.crmbackend.service.implementation;

import crm.crmbackend.dto.UserDTO;
import crm.crmbackend.entity.User;
import crm.crmbackend.enumeration.Role;
import crm.crmbackend.repository.UserRepository;
import crm.crmbackend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
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
    public List<UserDTO> findAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO fetchUserDetails(Long id) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapper.map(user, UserDTO.class);
    }

    @Transactional
    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        checkIfUserAlreadyExists(userDTO);

        User user = mapper.map(userDTO, User.class);

        if (userDTO.getActive() == null) {
            user.setActive(true);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(userDTO.getPassword()));

        User savedUser = userRepository.save(user);
        return mapper.map(savedUser, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {

        User user = userRepository.findById(userDTO.getId()).orElseThrow(EntityNotFoundException::new);

        user.setUsername(userDTO.getUsername());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(userDTO.getPassword()));

        user.setName(userDTO.getName());

        User savedUser = userRepository.save(user);
        return mapper.map(savedUser, UserDTO.class);
    }

    @Override
    public void deactivateUser(UserDTO userDTO) {
        userDTO.setActive(false);
        userRepository.save(mapper.map(userDTO, User.class));
    }

    private void checkIfUserAlreadyExists(UserDTO userDTO) throws EntityExistsException {
        Optional<User> optionalUser = userRepository.findByUsernameAndActiveTrue(userDTO.getUsername());
        if (optionalUser.isPresent()) {
            throw new EntityExistsException("User with username " + userDTO.getUsername() + " already exists.");
        }
    }
}
