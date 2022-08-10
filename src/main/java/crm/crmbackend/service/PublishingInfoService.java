package crm.crmbackend.service;

import crm.crmbackend.dto.PublishingInfoDTO;
import crm.crmbackend.entity.PublishingInfo;

public interface PublishingInfoService {

    PublishingInfo savePublishingInfo(PublishingInfoDTO publishingInfoDTO);

    PublishingInfo updatePublishingInfo(PublishingInfoDTO publishingInfoDTO);
}
