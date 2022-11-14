package crm.crmbackend.controller;

import crm.crmbackend.dto.SubscriberDTO;
import crm.crmbackend.dto.SubscriptionDTO;
import crm.crmbackend.dto.TicketDTO;
import crm.crmbackend.service.SubscriberService;
import crm.crmbackend.service.SubscriptionService;
import crm.crmbackend.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/subscribers")
public class SubscriberController {

    private final SubscriberService subscriberService;

    private final SubscriptionService subscriptionService;

    private final TicketService ticketService;

    @Autowired
    public SubscriberController(SubscriberService subscriberService, SubscriptionService subscriptionService, TicketService ticketService) {
        this.subscriberService = subscriberService;
        this.subscriptionService = subscriptionService;
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<List<SubscriberDTO>> findAllSubscribers() {
        return ResponseEntity.ok(subscriberService.findAllSubscribers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriberDTO> fetchSubscriberDetails(@PathVariable Long id) {
        return ResponseEntity.ok(subscriberService.fetchSubscriberDetails(id));
    }

    @GetMapping("/{id}/subscriptions")
    public ResponseEntity<List<SubscriptionDTO>> findAllSubscriptions(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.findAllForSubscriber(id));
    }

    @GetMapping("/{id}/tickets")
    public ResponseEntity<List<TicketDTO>> findAllTickets(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.findAllTicketsForSubscriber(id));
    }

    @PostMapping
    public ResponseEntity<SubscriberDTO> saveSubscriber(@Valid @RequestBody SubscriberDTO subscriberDTO) {
        return ResponseEntity.ok(subscriberService.saveSubscriber(subscriberDTO));
    }

    @PutMapping
    public ResponseEntity<SubscriberDTO> updateSubscriber(@Valid @RequestBody SubscriberDTO subscriberDTO) {
        return ResponseEntity.ok(subscriberService.saveSubscriber(subscriberDTO));
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateSubscriber(@PathVariable Long id) {
        subscriberService.deactivateSubscriber(id);
        return ResponseEntity.ok().build();
    }
}
