package crm.crmbackend.service;

import crm.crmbackend.dto.SubscriptionDTO;

import java.util.List;

public interface SubscriptionService {

    List<SubscriptionDTO> findAllForSubscriber(Long subscriberId);

    SubscriptionDTO fetchSubscriptionDetails(Long id);

    SubscriptionDTO saveSubscription(SubscriptionDTO subscriptionDTO);

    SubscriptionDTO updateSubscription(SubscriptionDTO subscriptionDTO);
}
