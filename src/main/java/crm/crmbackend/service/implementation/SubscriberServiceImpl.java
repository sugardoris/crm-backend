package crm.crmbackend.service.implementation;

import crm.crmbackend.dto.SubscriberDTO;
import crm.crmbackend.entity.Subscriber;
import crm.crmbackend.entity.Subscription;
import crm.crmbackend.repository.SubscriberRepository;
import crm.crmbackend.repository.SubscriptionRepository;
import crm.crmbackend.service.SubscriberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriberServiceImpl implements SubscriberService {

    private final SubscriberRepository subscriberRepository;

    private final SubscriptionRepository subscriptionRepository;

    private final ModelMapper mapper;

    @Autowired
    public SubscriberServiceImpl(SubscriberRepository subscriberRepository, SubscriptionRepository subscriptionRepository,
                                 ModelMapper mapper) {
        this.subscriberRepository = subscriberRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.mapper = mapper;
    }

    @Override
    public List<SubscriberDTO> findAllSubscribers() {
        return subscriberRepository.findAll()
                .stream()
                .map(subscriber -> mapper.map(subscriber, SubscriberDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SubscriberDTO fetchSubscriberDetails(Long id) {
        Subscriber subscriber = subscriberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapper.map(subscriber, SubscriberDTO.class);
    }

    @Transactional
    @Override
    public SubscriberDTO saveSubscriber(SubscriberDTO subscriberDTO) {
        Subscriber subscriber = mapper.map(subscriberDTO, Subscriber.class);

        Subscriber savedSubscriber = subscriberRepository.save(subscriber);
        return mapper.map(savedSubscriber, SubscriberDTO.class);
    }

    @Override
    public void deactivateSubscriber(Long id) {
        Subscriber subscriber = subscriberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        subscriber.setActive(false);
        subscriberRepository.save(subscriber);

        //Deactivate all subscriptions for this subscriber
        List<Subscription> activeSubscriptions = subscriptionRepository.findAllBySubscriber_Id(id);
        activeSubscriptions.stream()
                .filter(subscription -> subscription.getDateEnded().isAfter(LocalDate.now()))
                .forEach(subscription -> subscription.setDateEnded(LocalDate.now()));
    }
}
