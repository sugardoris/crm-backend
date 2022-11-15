package crm.crmbackend.controller;

import crm.crmbackend.dto.PublicationDTO;
import crm.crmbackend.service.PublicationService;
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
@RequestMapping("/api/publications")
public class PublicationController {

    private final PublicationService publicationService;

    @Autowired
    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @GetMapping
    public ResponseEntity<List<PublicationDTO>> findAllPublications() {
        return ResponseEntity.ok(publicationService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<PublicationDTO>> findAllActivePublications() {
        return ResponseEntity.ok(publicationService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicationDTO> fetchPublicationDetails(@PathVariable Long id) {
        return ResponseEntity.ok(publicationService.fetchPublicationDetails(id));
    }

    @PostMapping
    public ResponseEntity<PublicationDTO> savePublication(@Valid @RequestBody PublicationDTO publicationDTO) {
        return ResponseEntity.ok(publicationService.savePublication(publicationDTO));
    }

    @PutMapping
    public ResponseEntity<PublicationDTO> updatePublication(@Valid @RequestBody PublicationDTO publicationDTO) {
        return ResponseEntity.ok(publicationService.updatePublication(publicationDTO));
    }

    @PostMapping("/{id}/archive")
    public ResponseEntity<Void> archivePublication(@PathVariable Long id) {
        publicationService.archivePublication(id);
        return ResponseEntity.ok().build();
    }
}
