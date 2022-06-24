package crm.crmbackend.service.implementation;

import crm.crmbackend.dto.SubscriptionDTO;
import crm.crmbackend.entity.Subscription;
import crm.crmbackend.repository.SubscriptionRepository;
import crm.crmbackend.service.SubscriptionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    private final ModelMapper mapper;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, ModelMapper mapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.mapper = mapper;
    }

    @Override
    public List<SubscriptionDTO> findAllForSubscriber(Long subscriberId) {
        return subscriptionRepository.findAllBySubscriber_Id(subscriberId)
                .stream()
                .map(subscription -> mapper.map(subscription, SubscriptionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SubscriptionDTO fetchSubscriptionDetails(Long id) {
        Subscription subscription = subscriptionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapper.map(subscription, SubscriptionDTO.class);
    }

    @Transactional
    @Override
    public SubscriptionDTO saveSubscription(SubscriptionDTO subscriptionDTO) {
        Subscription savedSubscription = subscriptionRepository.save(mapper.map(subscriptionDTO, Subscription.class));
        return mapper.map(savedSubscription, SubscriptionDTO.class);
    }
}
