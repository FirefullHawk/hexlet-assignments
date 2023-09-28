package exercise.controller;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class CityController {

    private final CityRepository cityRepository;

    private final WeatherService weatherService;

    // BEGIN
    @GetMapping(path = "/cities/{id}")
    public Map<String, String> getCity(@PathVariable long id) {
        return weatherService.getWeather(id);
    }

    @GetMapping(path = "/search")
    public List<Map<String, String>> getCities(@RequestParam(required = false) String name) {
        List<City> cities = name == null? cityRepository.findAllByOrderByName()
                : cityRepository.findByNameStartingWithIgnoreCase(name);

        List<Map<String, String>> citiesTemperature = new ArrayList<>();

        List<Map<String, String>> result = new ArrayList<>();

        cities
                .forEach(city -> citiesTemperature.add(weatherService.getWeather(city.getId())));

        citiesTemperature
                .forEach(city -> {
                    Map<String, String> oneCity = new HashMap<>();

                    oneCity.put("temperature", city.get("temperature"));
                    oneCity.put("name", city.get("name"));
                    result.add(oneCity);
                });

        return result;
    }
    // END
}

