package crm.crmbackend.service.implementation;

import crm.crmbackend.dto.SubscriberDTO;
import crm.crmbackend.entity.Subscriber;
import crm.crmbackend.repository.SubscriberRepository;
import crm.crmbackend.service.SubscriberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriberServiceImpl implements SubscriberService {

    private final SubscriberRepository subscriberRepository;

    private final ModelMapper mapper;

    @Autowired
    public SubscriberServiceImpl(SubscriberRepository subscriberRepository, ModelMapper mapper) {
        this.subscriberRepository = subscriberRepository;
        this.mapper = mapper;
    }

    @Override
    public List<SubscriberDTO> findAllSubscribers() {
        return subscriberRepository.findAll()
                .stream()
                .map(subscriber -> mapper.map(subscriber, SubscriberDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SubscriberDTO fetchSubscriberDetails(Long id) {
        Subscriber subscriber = subscriberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapper.map(subscriber, SubscriberDTO.class);
    }

    @Override
    public SubscriberDTO saveSubscriber(SubscriberDTO subscriberDTO) {
        Subscriber savedSubscriber = subscriberRepository.save(mapper.map(subscriberDTO, Subscriber.class));
        return mapper.map(savedSubscriber, SubscriberDTO.class);
    }

    @Override
    public void deactivateSubscriber(SubscriberDTO subscriberDTO) {
        subscriberDTO.setActive(false);
        subscriberRepository.save(mapper.map(subscriberDTO, Subscriber.class));
    }
}
