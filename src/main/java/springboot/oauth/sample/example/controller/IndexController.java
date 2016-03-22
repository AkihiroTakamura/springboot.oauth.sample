package springboot.oauth.sample.example.controller;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import springboot.oauth.sample.core.base.impl.BaseController;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

  @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
  public String index(OAuth2Authentication authentication) {    
    return "index";
  }

  @RequestMapping(value = {"/view/{kind}/{id}"}, method = RequestMethod.GET)
  public ModelAndView directReport(
    OAuth2Authentication authentication,
    @PathVariable String kind,
    @PathVariable String id
  ) {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("index");
    mv.addObject("kind", kind);
    mv.addObject("id", id);
    return mv;
  }

}
