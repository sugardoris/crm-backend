package crm.crmbackend.service.implementation;

import crm.crmbackend.dto.CityDTO;
import crm.crmbackend.entity.City;
import crm.crmbackend.repository.CityRepository;
import crm.crmbackend.service.CityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    private final ModelMapper mapper;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, ModelMapper mapper) {
        this.cityRepository = cityRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CityDTO> findAllCities() {
        return cityRepository.findAll().stream().map(city -> mapper.map(city, CityDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CityDTO addCity(CityDTO cityDTO) {
        City savedCity = cityRepository.save(mapper.map(cityDTO, City.class));
        return mapper.map(savedCity, CityDTO.class);
    }

    @Override
    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }
}
