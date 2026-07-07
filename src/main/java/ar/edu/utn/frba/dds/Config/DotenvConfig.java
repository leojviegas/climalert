package ar.edu.utn.frba.dds.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "file:.env.local", ignoreResourceNotFound = true)
public class DotenvConfig {
}
