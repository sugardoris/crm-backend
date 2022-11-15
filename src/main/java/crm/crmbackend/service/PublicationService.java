package crm.crmbackend.service;

import crm.crmbackend.dto.PublicationDTO;

import java.util.List;

public interface PublicationService {

    List<PublicationDTO> findAll();

    List<PublicationDTO> findAllActive();

    PublicationDTO fetchPublicationDetails(Long id);

    PublicationDTO savePublication(PublicationDTO publicationDTO);

    PublicationDTO updatePublication(PublicationDTO publicationDTO);

    void archivePublication(Long id);
}
