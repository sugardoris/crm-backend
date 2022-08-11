package crm.crmbackend.service.implementation;

import crm.crmbackend.dto.TicketDTO;
import crm.crmbackend.entity.Subscriber;
import crm.crmbackend.entity.Ticket;
import crm.crmbackend.repository.SubscriberRepository;
import crm.crmbackend.repository.TicketRepository;
import crm.crmbackend.service.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final SubscriberRepository subscriberRepository;
    private final ModelMapper mapper;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, SubscriberRepository subscriberRepository, ModelMapper mapper) {
        this.ticketRepository = ticketRepository;
        this.subscriberRepository = subscriberRepository;
        this.mapper = mapper;
    }

    @Override
    public List<TicketDTO> findAllTicketsForSubscriber(Long subscriberId) {
        return ticketRepository.findAllBySubscriber_Id(subscriberId)
                .stream()
                .map(ticket -> mapper.map(ticket, TicketDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TicketDTO fetchTicketDetails(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapper.map(ticket, TicketDTO.class);
    }

    @Transactional
    @Override
    public TicketDTO saveTicket(TicketDTO ticketDTO) {
        Ticket ticket = mapper.map(ticketDTO, Ticket.class);
        ticket.setResolved(false);

        Subscriber subscriber = subscriberRepository.findById(ticketDTO.getSubscriberId()).orElseThrow(EntityNotFoundException::new);
        ticket.setSubscriber(subscriber);

        Ticket savedTicket = ticketRepository.save(ticket);
        return mapper.map(savedTicket, TicketDTO.class);
    }

    @Override
    public TicketDTO updateTicket(TicketDTO ticketDTO) {
        Ticket ticket = ticketRepository.findById(ticketDTO.getId()).orElseThrow(EntityNotFoundException::new);

        ticket.setType(ticketDTO.getType());
        ticket.setDescription(ticketDTO.getDescription());
        ticket.setResolved(ticketDTO.getResolved());

        Ticket savedTicket = ticketRepository.save(ticket);
        return mapper.map(savedTicket, TicketDTO.class);
    }

    @Override
    public void resolveTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        ticket.setResolved(true);
        ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
}
