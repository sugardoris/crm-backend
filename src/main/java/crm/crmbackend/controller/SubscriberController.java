package crm.crmbackend.controller;

import crm.crmbackend.dto.SubscriberDTO;
import crm.crmbackend.dto.SubscriptionDTO;
import crm.crmbackend.service.SubscriberService;
import crm.crmbackend.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @Autowired
    public SubscriberController(SubscriberService subscriberService, SubscriptionService subscriptionService) {
        this.subscriberService = subscriberService;
        this.subscriptionService = subscriptionService;
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

    @PostMapping
    public ResponseEntity<SubscriberDTO> saveSubscriber(@Valid @RequestBody SubscriberDTO subscriberDTO) {
        return ResponseEntity.ok(subscriberService.saveSubscriber(subscriberDTO));
    }

    @PostMapping("/deactivate")
    public ResponseEntity<Void> deactivateSubscriber(@RequestBody SubscriberDTO subscriberDTO) {
        subscriberService.deactivateSubscriber(subscriberDTO);
        return ResponseEntity.noContent().build();
    }
}
