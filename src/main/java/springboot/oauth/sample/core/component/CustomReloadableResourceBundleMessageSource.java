package springboot.oauth.sample.core.component;

import java.util.Locale;
import java.util.Properties;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

/**
 * Message Source Component for i18n
 * 
 * @author Akihiro Takamura
 *
 */
@Component
public class CustomReloadableResourceBundleMessageSource extends ReloadableResourceBundleMessageSource {
  
  public Properties getMessages(Locale locale) {        
    clearCacheIncludingAncestors();
    PropertiesHolder propertiesHolder = getMergedProperties(locale);
    Properties properties = propertiesHolder.getProperties();
    return properties;
  }
}
