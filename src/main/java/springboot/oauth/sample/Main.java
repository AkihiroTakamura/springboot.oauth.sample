package springboot.oauth.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Application Entory Point
 * @author Akihiro Takamura
 *
 */
@Configuration
@ComponentScan(basePackages = {
  "springboot.oauth.sample.core",
  "springboot.oauth.sample.sample", // for sample. delete this in production
  "springboot.oauth.sample.example"
})
@EnableAutoConfiguration
public class Main {

  public static void main(String[] args)  {
    SpringApplication.run(Main.class, args);
  }
  
}
