package crm.crmbackend.service;

import crm.crmbackend.dto.SubscriptionTypeDTO;

import java.util.List;

public interface SubscriptionTypeService {

    List<SubscriptionTypeDTO> findAllSubscriptionTypes();

    List<SubscriptionTypeDTO> findAllActiveSubscriptionTypes();

    SubscriptionTypeDTO fetchSubscriptionTypeDetails(Long id);

    SubscriptionTypeDTO saveSubscriptionType(SubscriptionTypeDTO subscriptionTypeDTO);

    void deactivateSubscriptionType(Long id);
}
