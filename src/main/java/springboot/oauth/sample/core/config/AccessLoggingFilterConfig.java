package springboot.oauth.sample.core.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class AccessLoggingFilterConfig extends WebMvcConfigurerAdapter {

  @Bean
  public FilterRegistrationBean getFilterRegistrationBean() {
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
    filterRegistrationBean.setFilter(new LoggingFilter());
    filterRegistrationBean.setOrder(999);
    return filterRegistrationBean;
  }

  /**
   * リクエストの開始・終了ログを発行するfilter
   */
  private static class LoggingFilter implements Filter {

    // ignore uri(contains) 
    private String[] ignoreArray = { "/js/", "/css/", "/favicon.ico", "/img/" };
    
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
      
      // before filter
      if (request instanceof HttpServletRequest) {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        
        if (isLogTarget(httpRequest)) {
          logger.info(makeLogMessage("start", httpRequest));
        }
      }
      
      // execute originally action
      filterChain.doFilter(request, response);
      
      // after filter
      if (request instanceof HttpServletRequest) {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        
        if (isLogTarget(httpRequest)) {
          logger.info(makeLogMessage("end", httpRequest));
        }
      }
    }

    public void destroy() {
    }

    private boolean isLogTarget(HttpServletRequest request) {
      String uri = request.getRequestURI();
      
      for (String ignore : ignoreArray) {
        if (uri.contains(ignore)) {
          return false;
        }
      }
      return true;
    }
    
    public String makeLogMessage(String message, HttpServletRequest request) {
      String uri = request.getRequestURI();
      String method = request.getMethod();
      return String.format("<< %-8s >> method:%-8s uri:%-40s ", message, method, uri);
    }
    

  }
}
