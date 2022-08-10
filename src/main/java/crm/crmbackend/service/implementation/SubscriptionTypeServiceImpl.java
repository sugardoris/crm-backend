package crm.crmbackend.service.implementation;

import crm.crmbackend.dto.SubscriptionTypeDTO;
import crm.crmbackend.entity.Subscription;
import crm.crmbackend.entity.SubscriptionType;
import crm.crmbackend.repository.SubscriptionRepository;
import crm.crmbackend.repository.SubscriptionTypeRepository;
import crm.crmbackend.service.SubscriptionTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
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
        if (subscriptionTypeDTO.getActive() == null) {
            subscriptionTypeDTO.setActive(true);
        }
        SubscriptionType savedSubscriptionType =
                subscriptionTypeRepository.save(mapper.map(subscriptionTypeDTO, SubscriptionType.class));
        return mapper.map(savedSubscriptionType, SubscriptionTypeDTO.class);
    }

    @Transactional
    @Override
    public void deactivateSubscriptionType(SubscriptionTypeDTO subscriptionTypeDTO) {
        subscriptionTypeDTO.setActive(false);
        subscriptionTypeRepository.save(mapper.map(subscriptionTypeDTO, SubscriptionType.class));
//        //Set subscription type for all active subscriptions who are using this one to default
//        List<Subscription> activeSubscriptions = subscriptionRepository
//                .findAllBySubscriptionType_IdAndDateEndedAfter(subscriptionTypeDTO.getId(), LocalDate.now());
//        SubscriptionType defaultSubscription = subscriptionTypeRepository.findById(0L).orElseThrow(EntityNotFoundException::new);
//        activeSubscriptions.forEach(subscription -> subscription.setSubscriptionType(defaultSubscription));
//        subscriptionRepository.saveAll(activeSubscriptions);
    }
}
