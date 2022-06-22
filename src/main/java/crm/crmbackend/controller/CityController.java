package crm.crmbackend.controller;

import crm.crmbackend.dto.CityDTO;
import crm.crmbackend.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<List<CityDTO>> findAllCities() {
        return ResponseEntity.ok(cityService.findAllCities());
    }

    @PostMapping
    public ResponseEntity<CityDTO> addCity(CityDTO cityDTO) {
        return ResponseEntity.ok(cityService.addCity(cityDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCity(Long id) {
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }
}
