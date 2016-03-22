package springboot.oauth.sample.example.controller;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import springboot.oauth.sample.core.base.impl.BaseController;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

  @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
  @ResponseBody
  public String index(OAuth2Authentication authentication) {    
    return "Welcome Admin Page";
  }

}
