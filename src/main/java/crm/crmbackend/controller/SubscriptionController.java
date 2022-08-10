package crm.crmbackend.controller;

import crm.crmbackend.dto.SubscriptionDTO;
import crm.crmbackend.service.SubscriptionService;
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

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionDTO> fetchSubscriptionDetails(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.fetchSubscriptionDetails(id));
    }

    @PostMapping
    public ResponseEntity<SubscriptionDTO> saveSubscription(@Valid @RequestBody SubscriptionDTO subscriptionDTO) {
        return ResponseEntity.ok(subscriptionService.saveSubscription(subscriptionDTO));
    }

    @PutMapping
    public ResponseEntity<SubscriptionDTO> updateSubscription(@Valid @RequestBody SubscriptionDTO subscriptionDTO) {
        return ResponseEntity.ok(subscriptionService.updateSubscription(subscriptionDTO));
    }
}
