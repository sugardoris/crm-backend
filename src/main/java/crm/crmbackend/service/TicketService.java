package crm.crmbackend.service;

import crm.crmbackend.dto.TicketDTO;

import java.util.List;

public interface TicketService {

    List<TicketDTO> findAllTicketsForSubscriber(Long subscriberId);

    TicketDTO fetchTicketDetails(Long id);

    TicketDTO saveTicket(TicketDTO ticketDTO);

    TicketDTO updateTicket(TicketDTO ticketDTO);

    void resolveTicket(Long id);

    void deleteTicket(Long id);
}
