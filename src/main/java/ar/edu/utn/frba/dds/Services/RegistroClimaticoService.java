package ar.edu.utn.frba.dds.Services;


import ar.edu.utn.frba.dds.Clients.WeatherApiClient;
import ar.edu.utn.frba.dds.Repositories.RegistroClimaticoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;





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

  @Scheduled(fixedRate = 300000) // 5 minutos (300.000 ms)
  public void consultarYGuardarClima() {
    // 1. Obtenemos el DTO ya armado por nuestro Cliente
    WeatherApiResponseDto respuestaApi = weatherApiClient.obtenerClimaActual(ciudad);

    if (respuestaApi != null) {
      // 2. Creamos la entidad
      RegistroClimatico registro = new RegistroClimatico();
      registro.setFechaHora(LocalDateTime.now());

      // 3. Mapeamos dato por dato desde el DTO hacia la Entidad
      registro.setCiudad(respuestaApi.getLocation().getName());
      registro.setPais(respuestaApi.getLocation().getCountry());
      registro.setTemperatura(respuestaApi.getCurrent().getTempC());
      registro.setHumedad(respuestaApi.getCurrent().getHumidity());
      registro.setCondicion(respuestaApi.getCurrent().getCondition().getText());

      // 4. Guardamos en el registro histórico
      registroClimaticoRepository.crearRegistroClimatico(registro);
    }
  }
}

}
