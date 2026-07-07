package ar.edu.utn.frba.dds.Clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class WeatherApiClient {
  @Value("${apiKey}")
  private String apiKey;

  @Value("${apiUrl}")
  private String apiUrl;

  private final RestTemplate restTemplate = new RestTemplate();

  public WeatherApiResponseDto obtenerClimaActual(String ubicacion) {
    String url = apiUrl + "?key=" + apiKey + "&q="+ ubicacion;

    // Pide la URL y transforma el JSON directamente a tu DTO
    return restTemplate.getForObject(url, WeatherApiResponseDto.class);
  }
}

}
