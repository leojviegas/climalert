package ar.edu.utn.frba.dds.Repositories;

import ar.edu.utn.frba.dds.DomainEntities.RegistroClimatico;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;

@Repository
public class RegistroClimaticoRepository {

  private ArrayList<RegistroClimatico> historialRegistroClimatico;


  public RegistroClimaticoRepository() {
    this.historialRegistroClimatico = new ArrayList<>();
  }

  public crearRegistroClimatico(RegistroClimatico registro) {

  }

  public getRegistroClimaticoMasReciente() {
    return this.historialRegistroClimatico.get(this.historialRegistroClimatico.size()-1);
  }

}
