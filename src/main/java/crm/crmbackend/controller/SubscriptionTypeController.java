package crm.crmbackend.controller;

import crm.crmbackend.dto.SubscriptionTypeDTO;
import crm.crmbackend.service.SubscriptionTypeService;
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
@RequestMapping("/api/subscription-types")
public class SubscriptionTypeController {

    private final SubscriptionTypeService subscriptionTypeService;

    @Autowired
    public SubscriptionTypeController(SubscriptionTypeService subscriptionTypeService) {
        this.subscriptionTypeService = subscriptionTypeService;
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionTypeDTO>> findAllSubscriptionTypes() {
        return ResponseEntity.ok(subscriptionTypeService.findAllSubscriptionTypes());
    }

    @GetMapping("/active")
    public ResponseEntity<List<SubscriptionTypeDTO>> findAllActiveSubscriptionTypes() {
        return ResponseEntity.ok(subscriptionTypeService.findAllActiveSubscriptionTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionTypeDTO> fetchSubscriptionTypeDetails(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionTypeService.fetchSubscriptionTypeDetails(id));
    }

    @PostMapping
    public ResponseEntity<SubscriptionTypeDTO> saveSubscriptionType(@Valid @RequestBody SubscriptionTypeDTO subscriptionTypeDTO) {
        return ResponseEntity.ok(subscriptionTypeService.saveSubscriptionType(subscriptionTypeDTO));
    }

    @PutMapping
    public ResponseEntity<SubscriptionTypeDTO> updateSubscriptionType(@Valid @RequestBody SubscriptionTypeDTO subscriptionTypeDTO) {
        return ResponseEntity.ok(subscriptionTypeService.updateSubscriptionType(subscriptionTypeDTO));
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateSubscriptionType(@PathVariable Long id) {
        subscriptionTypeService.deactivateSubscriptionType(id);
        return ResponseEntity.ok().build();
    }
}
