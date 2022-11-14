package crm.crmbackend.service.implementation;

import crm.crmbackend.dto.SubscriptionDTO;
import crm.crmbackend.entity.Publication;
import crm.crmbackend.entity.Subscriber;
import crm.crmbackend.entity.Subscription;
import crm.crmbackend.entity.SubscriptionType;
import crm.crmbackend.enumeration.PublicationPeriod;
import crm.crmbackend.enumeration.SubscriptionPeriod;
import crm.crmbackend.repository.PublicationRepository;
import crm.crmbackend.repository.SubscriberRepository;
import crm.crmbackend.repository.SubscriptionRepository;
import crm.crmbackend.repository.SubscriptionTypeRepository;
import crm.crmbackend.service.SubscriptionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final PublicationRepository publicationRepository;
    private final SubscriberRepository subscriberRepository;
    private final SubscriptionTypeRepository subscriptionTypeRepository;
    private final ModelMapper mapper;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, PublicationRepository publicationRepository, SubscriberRepository subscriberRepository, SubscriptionTypeRepository subscriptionTypeRepository, ModelMapper mapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.publicationRepository = publicationRepository;
        this.subscriberRepository = subscriberRepository;
        this.subscriptionTypeRepository = subscriptionTypeRepository;
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
        SubscriptionType subscriptionType = subscriptionTypeRepository.findById(subscriptionDTO.getSubscriptionTypeId()).orElseThrow(EntityNotFoundException::new);

        Subscription subscription = mapper.map(subscriptionDTO, Subscription.class);
        subscription.setPublication(publication);
        subscription.setSubscriber(subscriber);
        subscription.setSubscriptionType(subscriptionType);
        subscription.setPrice(calculatePrice(subscription));
        subscription.setDateEnded(subscriptionDTO.getDateEnded() != null ? subscriptionDTO.getDateEnded() : null);

        Subscription savedSubscription = subscriptionRepository.save(subscription);
        return mapper.map(savedSubscription, SubscriptionDTO.class);
    }

    @Override
    public SubscriptionDTO updateSubscription(SubscriptionDTO subscriptionDTO) {
        Subscription subscription = subscriptionRepository.findById(subscriptionDTO.getId()).orElseThrow(EntityNotFoundException::new);
        if (!Objects.equals(subscription.getSubscriptionType().getId(), subscriptionDTO.getSubscriptionTypeId())) {
            SubscriptionType subscriptionType = subscriptionTypeRepository.findById(subscriptionDTO.getSubscriptionTypeId()).orElseThrow(EntityNotFoundException::new);
            subscription.setSubscriptionType(subscriptionType);
            subscription.setPrice(calculatePrice(subscription));
        }
        subscription.setDateEnded(subscriptionDTO.getDateEnded() != null ? subscriptionDTO.getDateEnded() : null);
        Subscription savedSubscription = subscriptionRepository.save(subscription);
        return mapper.map(savedSubscription, SubscriptionDTO.class);
    }

    private BigDecimal calculatePrice(Subscription subscription) {
        PublicationPeriod publicationPeriod = subscription.getPublication().getIssuePeriod();
        BigDecimal publicationPrice = subscription.getPublication().getPrice();
        SubscriptionPeriod subscriptionPeriod = subscription.getSubscriptionType().getSubscriptionPeriod();
        BigDecimal discount = subscription.getSubscriptionType().getDiscount();

        BigDecimal price = publicationPrice.multiply(getMonthlyNumberOfIssues(publicationPeriod)).
                multiply(getNumberOfMonthsInSubscription(subscriptionPeriod)).setScale(2, RoundingMode.HALF_UP);
        return calculateDiscountedPrice(price, discount);
    }

    private BigDecimal getMonthlyNumberOfIssues(PublicationPeriod publicationPeriod) {
        return switch (publicationPeriod) {
            case DAILY -> BigDecimal.valueOf(30);
            case WEEKLY -> BigDecimal.valueOf(4);
            case MONTHLY -> BigDecimal.valueOf(1);
        };
    }

    private BigDecimal getNumberOfMonthsInSubscription(SubscriptionPeriod subscriptionPeriod) {
        return switch (subscriptionPeriod) {
            case MONTHLY -> BigDecimal.valueOf(1);
            case BIANNUALY -> BigDecimal.valueOf(6);
            case ANNUALY -> BigDecimal.valueOf(12);
        };
    }

    private BigDecimal calculateDiscountedPrice(BigDecimal price, BigDecimal discountPercentage) {
        BigDecimal discount = price.multiply(discountPercentage).divide(new BigDecimal(100), RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
        return price.subtract(discount);
    }


}
