package springboot.oauth.sample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter.XFrameOptionsMode;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Value("${security.oauth2.custom.server-logout-url}") private String serverLogoutUrl;
  
  @Value("${security.oauth2.custom.server-logouted-redirect-url}") private String serverLogoutedRedirectUrl;
  
  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
      .headers()
        // allow iframe
        .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsMode.SAMEORIGIN))
      .and()
      .authorizeRequests()
      .antMatchers("/public/**").permitAll()
      .antMatchers("/").permitAll()
      .antMatchers("/admin/**").hasRole("ADMIN")
      .anyRequest().authenticated()
      .and()
      .csrf()
        .csrfTokenRepository(csrfTokenRepository())
      .and()
      .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl(serverLogoutUrl + "?next=" + serverLogoutedRedirectUrl)
        .deleteCookies("JSESSIONID")
        .invalidateHttpSession(true)
        .permitAll()
    ;
  }
 
  private CsrfTokenRepository csrfTokenRepository() {
    HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
    repository.setHeaderName("X-XSRF-TOKEN");
    return repository;
  }
}
