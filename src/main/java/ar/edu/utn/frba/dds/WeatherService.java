package ar.edu.utn.frba.dds;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {


  @Value("${apiKey}")
  private String apiKey;

  @Value("${ciudad}")
  private String city;

  @Value("${apiUrl}")
  private String apiUrl;

  public void fetchWeatherData() {
    // Aquí armas la URL para pegarle a la API
    String finalUrl = apiUrl + "?key=" + apiKey + "&q=" + city;

    System.out.println("Consultando clima en: " + city);
    // Lógica de conexión (usando RestTemplate o WebClient)...
  }
}
