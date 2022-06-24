package crm.crmbackend.service.implementation;

import crm.crmbackend.dto.PublicationDTO;
import crm.crmbackend.entity.Publication;
import crm.crmbackend.repository.PublicationRepository;
import crm.crmbackend.service.PublicationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;
    private final ModelMapper mapper;

    @Autowired
    public PublicationServiceImpl(PublicationRepository publicationRepository, ModelMapper mapper) {
        this.publicationRepository = publicationRepository;
        this.mapper = mapper;
    }

    @Override
    public List<PublicationDTO> findAll() {
        return publicationRepository.findAll()
                .stream()
                .map(publication -> mapper.map(publication, PublicationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PublicationDTO fetchPublicationDetails(Long id) {
        Publication publication = publicationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapper.map(publication, PublicationDTO.class);
    }

    @Transactional
    @Override
    public PublicationDTO savePublication(PublicationDTO publicationDTO) {
        if (publicationDTO.getId() != null) {
            publicationDTO.setActive(true);
        }
        Publication savedPublication = publicationRepository.save(mapper.map(publicationDTO, Publication.class));
        return mapper.map(savedPublication, PublicationDTO.class);
    }

    @Transactional
    @Override
    public void archivePublication(PublicationDTO publicationDTO) {
        publicationDTO.setActive(false);
        publicationRepository.save(mapper.map(publicationDTO, Publication.class));
    }
}
