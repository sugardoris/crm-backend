package crm.crmbackend.controller;

import crm.crmbackend.dto.CityDTO;
import crm.crmbackend.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<List<CityDTO>> findAllCities() {
        return ResponseEntity.ok(cityService.findAllCities());
    }

    @PostMapping
    public ResponseEntity<CityDTO> addCity(@Valid @RequestBody CityDTO cityDTO) {
        return ResponseEntity.ok(cityService.addCity(cityDTO));
    }
}
