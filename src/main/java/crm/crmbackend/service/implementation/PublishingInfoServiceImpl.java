package crm.crmbackend.service.implementation;

import crm.crmbackend.dto.PublishingInfoDTO;
import crm.crmbackend.entity.PublishingInfo;
import crm.crmbackend.repository.PublishingInfoRepository;
import crm.crmbackend.service.PublishingInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PublishingInfoServiceImpl implements PublishingInfoService {

    private final PublishingInfoRepository publishingInfoRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public PublishingInfoServiceImpl(PublishingInfoRepository publishingInfoRepository, ModelMapper modelMapper) {
        this.publishingInfoRepository = publishingInfoRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public PublishingInfo savePublishingInfo(PublishingInfoDTO publishingInfoDTO) {
        PublishingInfo publishingInfo = publishingInfoRepository.save(modelMapper.map(publishingInfoDTO, PublishingInfo.class));
        return publishingInfo;
    }
}
