package ar.edu.utn.frba.dds.DomainEntities;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class RegistroClimatico {

  private String ciudad;
  private String pais;
  private Double temperatura;
  private Integer humedad;
  private String condicion;
  private LocalDateTime fechaHora;
  private boolean alertaGenerada = false;

}
