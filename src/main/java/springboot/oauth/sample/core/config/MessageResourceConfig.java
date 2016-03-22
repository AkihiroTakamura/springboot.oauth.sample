package springboot.oauth.sample.core.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import springboot.oauth.sample.core.component.CustomReloadableResourceBundleMessageSource;

/**
 * Configuration for Message Resource
 * @author Akihiro Takamura
 *
 */
@Configuration
public class MessageResourceConfig extends WebMvcConfigurerAdapter {

  /**
   * for default locale
   * @return
   */
  @Bean
  public LocaleResolver localResolver() {
    SessionLocaleResolver slr = new SessionLocaleResolver();
    slr.setDefaultLocale(Locale.JAPAN);
    return slr;
  }
  
  /**
   * for message resouce(international)
   * target is src/main/resources/i18n/messages_*.properties
   * @return
   */
  @Bean
  public CustomReloadableResourceBundleMessageSource messageSource() {
    CustomReloadableResourceBundleMessageSource messageSource = new CustomReloadableResourceBundleMessageSource();
    messageSource.setBasename("classpath:i18n/messages");
    messageSource.setCacheSeconds(3600); // reflesh message cache per hour.
    return messageSource;
  }

  /**
   * for validate message resource(international)
   * target is src/main/resources/i18n/messages_*.properties
   * @return
   */
  @Bean
  public LocalValidatorFactoryBean validator() {
    LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
    localValidatorFactoryBean.setValidationMessageSource(messageSource());
    return localValidatorFactoryBean;
  }

  @Override
  public Validator getValidator() {
    LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
    factory.setValidationMessageSource(messageSource());
    return validator();
  }
  
}
