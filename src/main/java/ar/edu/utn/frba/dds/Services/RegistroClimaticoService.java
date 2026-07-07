package ar.edu.utn.frba.dds.Services;

import ar.edu.utn.frba.dds.Clients.WeatherApiClient;
import ar.edu.utn.frba.dds.DTOs.WeatherApiResponseDto;
import ar.edu.utn.frba.dds.DomainEntities.RegistroClimatico;
import ar.edu.utn.frba.dds.Repositories.RegistroClimaticoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegistroClimaticoService {

  private final WeatherApiClient weatherApiClient;
  private final RegistroClimaticoRepository registroClimaticoRepository;
  
  @Value("${ciudad}")
  private String ciudad;

  public RegistroClimaticoService(WeatherApiClient weatherApiClient, RegistroClimaticoRepository registroClimaticoRepository) {
    this.weatherApiClient = weatherApiClient;
    this.registroClimaticoRepository = registroClimaticoRepository;
  }

  @Scheduled(fixedRate = 300000)
  public void consultarYGuardarClima() {
    WeatherApiResponseDto respuestaApi = weatherApiClient.obtenerClimaActual(ciudad);

    if (respuestaApi != null) {
      RegistroClimatico registro = new RegistroClimatico();
      registro.setFechaHora(LocalDateTime.now());

      if(respuestaApi.getLocation() != null) {
        registro.setCiudad(respuestaApi.getLocation().getName());
        registro.setPais(respuestaApi.getLocation().getCountry());
      }
      if(respuestaApi.getCurrent() != null) {
        registro.setTemperatura(respuestaApi.getCurrent().getTemp_c());
        registro.setHumedad(respuestaApi.getCurrent().getHumidity());
        if(respuestaApi.getCurrent().getCondition() != null) {
          registro.setCondicion(respuestaApi.getCurrent().getCondition().getText());
        }
      }

      registroClimaticoRepository.crearRegistroClimatico(registro);
    }
  }
}
