package crm.crmbackend.service;

import crm.crmbackend.dto.ContactInfoDTO;
import crm.crmbackend.entity.ContactInfo;

public interface ContactInfoService {

    ContactInfo saveContactInfo(ContactInfoDTO contactInfoDTO);
}
