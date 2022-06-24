package crm.crmbackend.service;

import crm.crmbackend.dto.TicketDTO;

import java.util.List;

public interface TicketService {

    List<TicketDTO> findAllTicketsForSubscriber(Long subscriberId);

    TicketDTO fetchTicketDetails(Long id);

    TicketDTO saveTicket(TicketDTO ticketDTO);

    void resolveTicket(TicketDTO ticketDTO);

    void deleteTicket(Long id);
}
