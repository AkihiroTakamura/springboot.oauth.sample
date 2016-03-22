package springboot.oauth.sample.example.component;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 404ページ等、Springが自動的に拾わないエラーのハンドリング
 * Servletのカスタマイズによりハンドリングする
 */
@Component
public class ServletErrorSettings implements EmbeddedServletContainerCustomizer {

  @Override
  public void customize(ConfigurableEmbeddedServletContainer container) {
    container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
  }
  
}
