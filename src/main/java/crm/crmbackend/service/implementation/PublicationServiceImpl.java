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
    public List<PublicationDTO> findAllActive() {
        return publicationRepository.findAllByActiveTrue()
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
        Publication publication = mapper.map(publicationDTO, Publication.class);

        Publication savedPublication = publicationRepository.save(publication);
        return mapper.map(savedPublication, PublicationDTO.class);
    }

    @Override
    public void archivePublication(Long id) {
        Publication publication = publicationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        publication.setActive(false);
        publicationRepository.save(publication);
    }
}
