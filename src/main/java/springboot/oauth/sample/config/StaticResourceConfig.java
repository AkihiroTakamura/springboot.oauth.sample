package springboot.oauth.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;
import org.springframework.web.servlet.resource.VersionResourceResolver;

/**
 * setting static file path
 */
@Configuration
public class StaticResourceConfig extends WebMvcConfigurerAdapter {

  private static final String STATIC_RESOURCE_PATH = "classpath:/public/";

  @Bean
  public ResourceUrlEncodingFilter resourceUrlEncodingFilter() {
    return new ResourceUrlEncodingFilter();
  }

  /**
   * static fileパスを/public配下にセット
   * static fileにはフィンガープリントをつけ、cacheをbreakする
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {

    VersionResourceResolver versionResolver = new VersionResourceResolver()
      .addContentVersionStrategy("/css/**", "/js/**", "/plugin/**");

    registry
      .addResourceHandler("/**")
      .addResourceLocations(STATIC_RESOURCE_PATH)
      .setCachePeriod(null)
      .resourceChain(true)
      .addResolver(versionResolver);
  }

}
