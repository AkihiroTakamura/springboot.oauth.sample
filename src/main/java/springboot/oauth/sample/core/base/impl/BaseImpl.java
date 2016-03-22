package springboot.oauth.sample.core.base.impl;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import springboot.oauth.sample.core.base.Auth;
import springboot.oauth.sample.core.base.Base;
import springboot.oauth.sample.core.base.Setting;
import springboot.oauth.sample.core.component.MessageByLocaleService;
import springboot.oauth.sample.example.component.ApplicationSettings;

/**
 * Base implemented Class
 */
public abstract class BaseImpl implements Base, Setting, Auth {

  @Autowired
  ApplicationSettings applicationSettings;

  Logger logger = LoggerFactory.getLogger(
    getClass()
  );

  @Override
  public ApplicationSettings getSetting() {
    return applicationSettings;
  }

  @Override
  public Logger getLogger() {
    return logger;
  }

  @Override
  public Boolean isLogined() {
    return (!(getAuth() instanceof AnonymousAuthenticationToken));
  }

  @Override
  public String getUserName() {
    if (!isLogined()) return null; 
    return getAuth().getName();
  }

  @Override
  public Boolean hasRole(String role) {
    for (GrantedAuthority authority : getAuth().getAuthorities()) {
      if (role.equals(authority.getAuthority()))
        return true;
    }
    return false;
  }
  
  private Authentication getAuth() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  /**
   * Message Resource Instance(i18n compatible)
   * select language automatic by browser setting language
   * target message resouce file: src/main/resources/i18n/messages_*.properties
   */
  @Autowired
  public MessageByLocaleService messageSource;
  
  /**
   * get Message From MessageResource
   * @param messageKey
   * @return
   */
  public String getMessage(String messageKey) {
    String message = messageSource.getMessage(messageKey);
    if (message == null || message.equals("")) {
      return "";
    }
    return message;
  }
  
  /**
   * get Message From MessageResource
   * @param messageKey
   * @param replaceText array
   * @return
   */
  public String getMessage(String messageKey, Object[] replace) {
    String message = this.getMessage(messageKey);    
    MessageFormat mf = new MessageFormat(message);
    return mf.format(replace);
  }

  
}
