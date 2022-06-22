package crm.crmbackend.service;

import crm.crmbackend.dto.CityDTO;

import java.util.List;

public interface CityService {

    List<CityDTO> findAllCities();

    CityDTO addCity(CityDTO cityDTO);

    void deleteCity(Long id);
}
