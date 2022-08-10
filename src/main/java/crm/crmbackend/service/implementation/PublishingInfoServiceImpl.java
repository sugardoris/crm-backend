package crm.crmbackend.service.implementation;

import crm.crmbackend.dto.PublishingInfoDTO;
import crm.crmbackend.entity.PublishingInfo;
import crm.crmbackend.repository.PublishingInfoRepository;
import crm.crmbackend.service.PublishingInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class PublishingInfoServiceImpl implements PublishingInfoService {

    private final PublishingInfoRepository publishingInfoRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public PublishingInfoServiceImpl(PublishingInfoRepository publishingInfoRepository, ModelMapper modelMapper) {
        this.publishingInfoRepository = publishingInfoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PublishingInfo savePublishingInfo(PublishingInfoDTO publishingInfoDTO) {
        PublishingInfo publishingInfo = publishingInfoRepository.save(modelMapper.map(publishingInfoDTO, PublishingInfo.class));
        return publishingInfo;
    }

    @Override
    public PublishingInfo updatePublishingInfo(PublishingInfoDTO publishingInfoDTO) {
        PublishingInfo publishingInfo = publishingInfoRepository.findById(publishingInfoDTO.getId()).orElseThrow(EntityNotFoundException::new);
        publishingInfo.setFirstIssueDate(publishingInfoDTO.getFirstIssueDate());
        publishingInfo.setIssuePeriod(publishingInfoDTO.getIssuePeriod());
        publishingInfo.setComesOut(publishingInfoDTO.getComesOut());
        publishingInfo.setPrice(publishingInfoDTO.getPrice());
        return publishingInfoRepository.save(publishingInfo);
    }
}
