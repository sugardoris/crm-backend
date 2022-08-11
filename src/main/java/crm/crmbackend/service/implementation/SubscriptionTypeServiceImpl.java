package crm.crmbackend.service.implementation;

import crm.crmbackend.dto.SubscriptionTypeDTO;
import crm.crmbackend.entity.SubscriptionType;
import crm.crmbackend.repository.SubscriptionRepository;
import crm.crmbackend.repository.SubscriptionTypeRepository;
import crm.crmbackend.service.SubscriptionTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionTypeServiceImpl implements SubscriptionTypeService {

    private final SubscriptionTypeRepository subscriptionTypeRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final ModelMapper mapper;

    @Autowired
    public SubscriptionTypeServiceImpl(SubscriptionTypeRepository subscriptionTypeRepository, SubscriptionRepository subscriptionRepository, ModelMapper mapper) {
        this.subscriptionTypeRepository = subscriptionTypeRepository;
        this.subscriptionRepository = subscriptionRepository;
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

    @Transactional
    @Override
    public SubscriptionTypeDTO saveSubscriptionType(SubscriptionTypeDTO subscriptionTypeDTO) {
        SubscriptionType subscriptionType = mapper.map(subscriptionTypeDTO, SubscriptionType.class);
        subscriptionType.setActive(true);

        SubscriptionType savedSubscriptionType =
                subscriptionTypeRepository.save(subscriptionType);
        return mapper.map(savedSubscriptionType, SubscriptionTypeDTO.class);
    }

    @Override
    public SubscriptionTypeDTO updateSubscriptionType(SubscriptionTypeDTO subscriptionTypeDTO) {
        SubscriptionType subscriptionType = subscriptionTypeRepository.findById(subscriptionTypeDTO.getId())
                .orElseThrow(EntityNotFoundException::new);
        subscriptionType.setName(subscriptionTypeDTO.getName());
        subscriptionType.setDiscount(subscriptionTypeDTO.getDiscount());
        subscriptionType.setSubscriptionPeriod(subscriptionTypeDTO.getSubscriptionPeriod());

        SubscriptionType savedSubscriptionType =
                subscriptionTypeRepository.save(subscriptionType);
        return mapper.map(savedSubscriptionType, SubscriptionTypeDTO.class);
    }

    @Override
    public void deactivateSubscriptionType(Long id) {
        SubscriptionType subscriptionType = subscriptionTypeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        subscriptionType.setActive(false);
        subscriptionTypeRepository.save(subscriptionType);
    }
}
