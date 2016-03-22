package springboot.oauth.sample.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springboot.oauth.sample.core.base.impl.BaseController;

/**
 * エラーページ用Controller
 * 以下のマッピングをする
 *   403(Spring Security上でdenyになった場合)のページマッピング
 *   404(Servlet上で見つからない場合)のページマッピング
 */
@Controller
//public class ErrorPageController extends BaseController implements ErrorController{
public class ErrorPageController extends BaseController {

//NOTE:
// ErrorControllerの実装はしない
//  -> ErrorControllerを実装するとspringが検出するerrorで飛ばすページのマッピングができる
// ErrorControllerを実装すると指定したtemplateが存在しない場合、tomcatのエラーページに飛ぶ
// ここで何も指定せず、templates/errorのテンプレートを用意すると
//  -> pageの場合は/template/errorのページへ
//  -> restの場合はspring標準のエラーjsonへ
// Restでエラーの場合、Jsonを返却する為にはGlobalExceptionResolverでnullを返す-> Springのデフォルトエラーjsonになる
// Pageでエラーの場合、GlobalExceptionResolverで/templates/errorに飛ばしている
  
//  private static final String PATH = "/error";
//  
//  @Override
//  public String getErrorPath() {
//    return PATH;
//  }
//  
//  // For global error page(URL: /error <- auto mapping by Spring)
//  // この指定をしなくてもSpringは自動的に/templates/errorがあればそれを呼び出す
//  // 逆に/templates/errorがなければwhite label error pageになる
//  // 下記指定はデフォルトの/templates/errorを違うテンプレートに飛ばす場合に有効
//  @RequestMapping(PATH)
//  String home() {
//    return "error";
//  }
  
  // For Access Denied Page - from SecurityConfig 
  @RequestMapping("/error/403")
  public String forbidden() {
    return "error/403";
  }

  // For 404 - from ServletErrorSettings 
  @RequestMapping("/error/404")
  public String notfound() {
    return "error/404";
  }

}
