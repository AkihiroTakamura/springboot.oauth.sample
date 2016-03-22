package springboot.oauth.sample.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

public class PropertyConfig {

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
