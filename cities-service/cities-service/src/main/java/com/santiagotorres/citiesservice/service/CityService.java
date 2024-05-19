package com.santiagotorres.citiesservice.service;

import com.santiagotorres.citiesservice.dto.CityDTO;
import com.santiagotorres.citiesservice.model.City;
import com.santiagotorres.citiesservice.repository.IHotelsAPI;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService implements ICityService {

    @Autowired
    private IHotelsAPI hotelsAPI;

    List<City> cities = new ArrayList<>();


    @Override
    @CircuitBreaker(name="hotels-service", fallbackMethod = "fallbackGetCitiesHotel")
    @Retry(name="hotels-service")
    public CityDTO getCitiesHotels(String name, String country) {

        //buscamos ciudad original
        City city = this.findCity(name, country);

        //creamos el DTO de la ciudad + lista de hoteles
        CityDTO cityDTO = new CityDTO();
        cityDTO.setCity_id(city.getCity_id());
        cityDTO.setName(city.getName());
        cityDTO.setCountry(city.getCountry());
        cityDTO.setContinent(city.getContinent());
        cityDTO.setState(city.getState());

        //buscamos la lista de hoteles en la API y asignamos
        cityDTO.setHotelList(hotelsAPI.getHotelsByCityId(city.getCity_id()));

        /* createException(); */

        return cityDTO;
    }

    public CityDTO fallbackGetCitiesHotel (Throwable throwable) {

        return new CityDTO(9999999999L, "Fallido", "Fallido", "Fallido", "Fallido", null);

    }

    public void createException () {

        throw new IllegalArgumentException("Prueba Resilience y Circuit Breaker");

    }

    public City findCity(String name, String country) {
        this.loadCities();
        for (City c:cities) {
            if (c.getName().equals(name)) {
                if (c.getCountry().equals(country)) {
                    return c;
                }
            }
        }
        return null;
    }

    public void loadCities () {

        cities.add(new City(1L, "Buenos Aires", "South America", "Argentina", "Buenos Aires"));
        cities.add(new City(1L, "Oberá", "South America", "Argentina", "Misiones"));
        cities.add(new City(1L, "Mexico City", "North America", "Mexico", "Mexico City"));
        cities.add(new City(1L, "Guadalajara", "North America", "Mexico", "Jalisco"));
        cities.add(new City(1L, "Bogotá", "South America", "Colombia", "Cundinamarca"));
        cities.add(new City(1L, "Medellín", "South America", "Colombia", "Antioquia"));
        cities.add(new City(1L, "Santiago", "South America", "Chile", "Santiago Metropolitan"));
        cities.add(new City(1L, "Valparaiso", "South America", "Chile", "Valparaiso"));
        cities.add(new City(1L, "Asuncion", "South America", "Paraguay", "Asuncion"));
        cities.add(new City(1L, "Montevideo", "South America", "Uruguay", "Montevideo"));
        cities.add(new City(1L, "Madrid", "Europe", "Spain", "Community of Madrid"));
        cities.add(new City(1L, "Barcelona", "Europe", "Spain", "Catalonia"));
        cities.add(new City(1L, "Seville", "Europe", "Spain", "Andalucia"));
        cities.add(new City(1L, "Monterrey", "North America", "Mexico", "Nuevo León"));
        cities.add(new City(1L, "Valencia", "Europe", "Spain", "Valencian Community"));

    }
}
