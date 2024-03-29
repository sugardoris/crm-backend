package crm.crmbackend.service;

import crm.crmbackend.dto.SubscriberDTO;

import java.util.List;

public interface SubscriberService {

    List<SubscriberDTO> findAllSubscribers();

    SubscriberDTO fetchSubscriberDetails(Long id);

    SubscriberDTO saveSubscriber(SubscriberDTO subscriberDTO);

    SubscriberDTO updateSubscriber(SubscriberDTO subscriberDTO);

    void deactivateSubscriber(Long id);
}
