package ar.edu.utn.frba.dds.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WeatherApiResponseDto {
  private LocationDto location;
  private CurrentDto current;

  @Data
  @NoArgsConstructor
  public static class LocationDto {
    private String name;
    private String country;
  }

  @Data
  @NoArgsConstructor
  public static class CurrentDto {
    private Double temp_c;
    private Integer humidity;
    private ConditionDto condition;
  }

  @Data
  @NoArgsConstructor
  public static class ConditionDto {
    private String text;
  }
}
