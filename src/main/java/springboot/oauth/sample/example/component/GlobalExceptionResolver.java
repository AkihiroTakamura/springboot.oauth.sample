package springboot.oauth.sample.example.component;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * Global Exception Handler
 * ほぼ全てのExceptionを把握する(rest controller/ controller / thymeleaf error)
 *   ただし、@ResponseStatusアノテーションされているクラスがスローされた場合は通らない
 *   ただし、new Errorはここを通らずに直接templates/errorに飛ばされる
 * このクラス内で@RestControllerか判断し、Restの場合はSpring標準のerror json処理のままにする
 *   一方、@Controllerの場合はtemplates/errorに飛ばしている
 * なおエラーページのURLマッピングは別途ErrorPageControllerを使っている
 */
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

  Logger logger = LoggerFactory.getLogger(
    getClass()
  );
  
  @Override
  public ModelAndView resolveException(HttpServletRequest request,
      HttpServletResponse response, Object handler, Exception ex) {

    logger.error(ex.getLocalizedMessage(), ex);
    
    if (isRestController(handler)) {
      // if cause method is rest controller -> returns default json
      return null;
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("exception", ex);
    mv.addObject("trace", ExceptionUtils.getStackTrace(ex));
    mv.addObject("url", request.getRequestURL());    
    mv.addObject("timestamp", new Date()); // spring errorに合わせてdate型のformatはしない    
    mv.setViewName("error");

    return mv;
  }

  /**
   * 呼出元が@RestControllerかどうかを判定
   * @param handler
   * @return
   */
  private boolean isRestController(Object handler) {
    if (handler instanceof HandlerMethod) {
      HandlerMethod method = (HandlerMethod)handler;
      return method.getMethod().getDeclaringClass().isAnnotationPresent(RestController.class);
    }
    return false;
  }

}
