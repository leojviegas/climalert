package ar.edu.utn.frba.dds.Repositories;

import ar.edu.utn.frba.dds.DomainEntities.RegistroClimatico;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;

@Repository
public class RegistroClimaticoRepository {

  private final ArrayList<RegistroClimatico> historialRegistroClimatico;

  public RegistroClimaticoRepository() {
    this.historialRegistroClimatico = new ArrayList<>();
  }

  public void crearRegistroClimatico(RegistroClimatico registro) {
    this.historialRegistroClimatico.add(registro);
  }

  public RegistroClimatico getRegistroClimaticoMasReciente() {
    if (this.historialRegistroClimatico.isEmpty()) {
      return null;
    }
    return this.historialRegistroClimatico.getLast();
  }

}
