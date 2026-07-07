package ar.edu.utn.frba.dds.Clients;

import ar.edu.utn.frba.dds.DTOs.WeatherApiResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherApiClient {
  @Value("${apiKey}")
  private String apiKey;

  @Value("${apiUrl}")
  private String apiUrl;

  private final RestTemplate restTemplate = new RestTemplate();

  public WeatherApiResponseDto obtenerClimaActual(String ubicacion) {
    String url = apiUrl + "?key=" + apiKey + "&q="+ ubicacion;

    WeatherApiResponseDto respuesta = restTemplate.getForObject(url, WeatherApiResponseDto.class);
    
    System.out.println("========== DATOS RECIBIDOS DE LA API ==========");
    System.out.println(respuesta);
    System.out.println("===============================================");
    
    return respuesta;
  }
}
