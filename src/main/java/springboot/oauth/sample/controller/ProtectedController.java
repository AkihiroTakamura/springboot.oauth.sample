package springboot.oauth.sample.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/protected")
public class ProtectedController extends BaseController {

  @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
  public Authentication index(OAuth2Authentication authentication) {
    return authentication;
  }
}
