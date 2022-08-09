package crm.crmbackend.service.implementation;

import crm.crmbackend.dto.SubscriptionDTO;
import crm.crmbackend.entity.Publication;
import crm.crmbackend.entity.Subscriber;
import crm.crmbackend.entity.Subscription;
import crm.crmbackend.repository.PublicationRepository;
import crm.crmbackend.repository.SubscriberRepository;
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
    private final PublicationRepository publicationRepository;
    private final SubscriberRepository subscriberRepository;
    private final ModelMapper mapper;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, PublicationRepository publicationRepository, SubscriberRepository subscriberRepository, ModelMapper mapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.publicationRepository = publicationRepository;
        this.subscriberRepository = subscriberRepository;
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
        Publication publication = publicationRepository.findById(subscriptionDTO.getPublicationId()).orElseThrow(EntityNotFoundException::new);
        Subscriber subscriber = subscriberRepository.findById(subscriptionDTO.getSubscriberId()).orElseThrow(EntityNotFoundException::new);
        Subscription subscription = mapper.map(subscriptionDTO, Subscription.class);
        subscription.setPublication(publication);
        subscription.setSubscriber(subscriber);
        Subscription savedSubscription = subscriptionRepository.save(subscription);
        return mapper.map(savedSubscription, SubscriptionDTO.class);
    }
}
