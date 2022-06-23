package crm.crmbackend.service.implementation;

import crm.crmbackend.dto.SubscriptionTypeDTO;
import crm.crmbackend.entity.SubscriptionType;
import crm.crmbackend.repository.SubscriptionTypeRepository;
import crm.crmbackend.service.SubscriptionTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionTypeServiceImpl implements SubscriptionTypeService {

    private final SubscriptionTypeRepository subscriptionTypeRepository;

    private final ModelMapper mapper;

    @Autowired
    public SubscriptionTypeServiceImpl(SubscriptionTypeRepository subscriptionTypeRepository, ModelMapper mapper) {
        this.subscriptionTypeRepository = subscriptionTypeRepository;
        this.mapper = mapper;
    }

    @Override
    public List<SubscriptionTypeDTO> findAllSubscriptionTypes() {
        return subscriptionTypeRepository.findAll()
                .stream()
                .map(subscriptionType -> mapper.map(subscriptionType, SubscriptionTypeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SubscriptionTypeDTO fetchSubscriptionTypeDetails(Long id) {
        SubscriptionType subscriptionType = subscriptionTypeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapper.map(subscriptionType, SubscriptionTypeDTO.class);
    }

    @Override
    public SubscriptionTypeDTO saveSubscriptionType(SubscriptionTypeDTO subscriptionTypeDTO) {
        SubscriptionType savedSubscriptionType =
                subscriptionTypeRepository.save(mapper.map(subscriptionTypeDTO, SubscriptionType.class));
        return mapper.map(savedSubscriptionType, SubscriptionTypeDTO.class);
    }

    @Override
    public void deactivateSubscriptionType(SubscriptionTypeDTO subscriptionTypeDTO) {
        subscriptionTypeDTO.setActive(false);
        //TODO: find all active subscriptions with this subscription type and set them to default
        subscriptionTypeRepository.save(mapper.map(subscriptionTypeDTO, SubscriptionType.class));
    }
}
