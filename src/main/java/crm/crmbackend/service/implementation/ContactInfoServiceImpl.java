package crm.crmbackend.service.implementation;

import crm.crmbackend.dto.ContactInfoDTO;
import crm.crmbackend.entity.ContactInfo;
import crm.crmbackend.repository.ContactInfoRepository;
import crm.crmbackend.service.ContactInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactInfoServiceImpl implements ContactInfoService {

    private final ContactInfoRepository contactInfoRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public ContactInfoServiceImpl(ContactInfoRepository contactInfoRepository, ModelMapper modelMapper) {
        this.contactInfoRepository = contactInfoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ContactInfo saveContactInfo(ContactInfoDTO contactInfoDTO) {
        return contactInfoRepository.save(modelMapper.map(contactInfoDTO, ContactInfo.class));
    }
}
