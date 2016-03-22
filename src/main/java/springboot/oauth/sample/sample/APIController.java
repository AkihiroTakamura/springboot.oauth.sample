package springboot.oauth.sample.sample;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springboot.oauth.sample.core.base.impl.BaseController;
import springboot.oauth.sample.core.util.PropertiesUtil;

@RestController
@RequestMapping(value = "/sample/api")
public class APIController extends BaseController {

  @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
  public String index() {
    return "api woks fine!";
  }

  @RequestMapping(value = {"/json"}, method = RequestMethod.GET)
  public Map<String, Object> returnJson(OAuth2Authentication authentication) {
    Map<String, Object> map = new HashMap<>();
    map.put("messages", PropertiesUtil.toMap(messageSource.getMessages()));
    map.put("authentication", authentication);
    return map;
  }
  
  @RequestMapping(value = {"/exception"}, method = RequestMethod.GET)
  public String exceptionSample() {
    throw new NullPointerException("sample error!");
  }
  
}
