package crm.crmbackend.controller;

import crm.crmbackend.dto.TicketDTO;
import crm.crmbackend.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> fetchTicketDetails(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.fetchTicketDetails(id));
    }

    @PostMapping
    public ResponseEntity<TicketDTO> saveTicket(@Valid @RequestBody TicketDTO ticketDTO) {
        return ResponseEntity.ok(ticketService.saveTicket(ticketDTO));
    }

    @PostMapping("/resolve")
    public ResponseEntity<Void> resolveTicket(@RequestBody TicketDTO ticketDTO) {
        ticketService.resolveTicket(ticketDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }
}
