package springboot.oauth.sample.core.component;

import java.util.Locale;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Message Resouce(i18n compatible)
 * 
 * for use
 *   @Autowired
 *   protected MessageByLocaleService messageSource;
 * 
 * @author Akihiro Takamura
 *
 */
@Component
public class MessageByLocaleServiceImpl implements MessageByLocaleService {

  @Autowired
  private MessageSource messageSource;

  private Locale locale = LocaleContextHolder.getLocale();
  
  @Override
  public String getMessage(String id) {
    try {
      return messageSource.getMessage(id, null, locale);
    } catch (NoSuchMessageException e) {
      return String.format("{%s}", id);
    }
  }

  @Override
  public Properties getMessages() {
    if (messageSource instanceof CustomReloadableResourceBundleMessageSource) {
      CustomReloadableResourceBundleMessageSource customMessageSource = (CustomReloadableResourceBundleMessageSource)messageSource;
      return customMessageSource.getMessages(locale);
    }
    return null;
  }

}
