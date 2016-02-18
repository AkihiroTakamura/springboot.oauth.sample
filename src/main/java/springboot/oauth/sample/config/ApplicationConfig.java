package springboot.oauth.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

public class ApplicationConfig {

  /**
   * for getting property file value
   * 
   * @return
   */
  @Bean
  public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
    PropertySourcesPlaceholderConfigurer config = new PropertySourcesPlaceholderConfigurer();

    // prevent error when key that @Value setted undefined in propetyfile.
    config.setIgnoreUnresolvablePlaceholders(true);
    return config;
  }

}
