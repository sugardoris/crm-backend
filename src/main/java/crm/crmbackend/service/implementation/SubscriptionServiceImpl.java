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
        if (subscription.getSubscriptionType().getId() != subscriptionDTO.getSubscriptionTypeId()) {
            SubscriptionType subscriptionType = subscriptionTypeRepository.findById(subscriptionDTO.getSubscriptionTypeId()).orElseThrow(EntityNotFoundException::new);
            subscription.setSubscriptionType(subscriptionType);
            subscription.setPrice(calculatePrice(subscription));
        }
        subscription.setDateEnded(subscriptionDTO.getDateEnded() != null ? subscriptionDTO.getDateEnded() : null);
        Subscription savedSubscription = subscriptionRepository.save(subscription);
        return mapper.map(savedSubscription, SubscriptionDTO.class);
    }

    private BigDecimal calculatePrice(Subscription subscription) {
        PublicationPeriod publicationPeriod = subscription.getPublication().getPublishingInfo().getIssuePeriod();
        BigDecimal publicationPrice = subscription.getPublication().getPublishingInfo().getPrice();
        SubscriptionPeriod subscriptionPeriod = subscription.getSubscriptionType().getSubscriptionPeriod();
        BigDecimal discount = subscription.getSubscriptionType().getDiscount();

        BigDecimal price = publicationPrice.multiply(getMonthlyNumberOfIssues(publicationPeriod)).multiply(getNumberOfMonthsInSubscription(subscriptionPeriod));
        return calculateDiscountedPrice(price, discount);
    }

    private BigDecimal getMonthlyNumberOfIssues(PublicationPeriod publicationPeriod) {
        switch (publicationPeriod) {
            case DAILY:
                return new BigDecimal(30);
            case WEEKLY:
                return new BigDecimal(4);
            case MONTHLY:
                return new BigDecimal(1);
            default:
                return new BigDecimal(0);
        }
    }

    private BigDecimal getNumberOfMonthsInSubscription(SubscriptionPeriod subscriptionPeriod) {
        switch (subscriptionPeriod) {
            case MONTHLY:
                return new BigDecimal(1);
            case BIANNUALY:
                return new BigDecimal(6);
            case ANNUALY:
                return new BigDecimal(12);
            default:
                return new BigDecimal(0);
        }
    }

    private BigDecimal calculateDiscountedPrice(BigDecimal price, BigDecimal discountPercentage) {
        BigDecimal discount = price.multiply(discountPercentage).divide(new BigDecimal(100)).setScale(2, RoundingMode.DOWN);
        return price.subtract(discount);
    }


}
