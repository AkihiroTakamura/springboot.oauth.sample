package springboot.oauth.sample.sample;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springboot.oauth.sample.core.base.impl.BaseController;

@Controller
@RequestMapping(value = "/sample/page")
public class PageController extends BaseController {

  @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
  public String index() {
    
    // message resource sample
    System.out.println("get message:" + getMessage("app.name"));
    
    // message resource replace sample
    // messages_*.propertiesに{0}のような指定についてreplaceする
    System.out.println("get replaced message:" + getMessage("typeMismatch", new String[] {"10"}));
    return "index";
  }

  @RequestMapping(value = {"/exception"}, method = RequestMethod.GET)
  public String exceptionSample() {
    throw new NullPointerException("sample error!");
  }
    
  @RequestMapping("/pdf")
  public void file1(HttpServletResponse res) throws IOException {
    // pdf viewerで参照するには以下のようなURLを叩けばOK
    // http://localhost:8080/plugin/pdfjs-build/generic/web/viewer.html?file=/sample/page/pdf
    Resource resource = new ClassPathResource("sample/lesson2.pdf");
    byte[] data = StreamUtils.copyToByteArray(resource.getInputStream()); // resource to byte array
    
    streamReport(res, data, "my_report.pdf");
  }
  
  protected void streamReport(HttpServletResponse response, byte[] data, String name) throws IOException {
    response.setContentType("application/pdf");
    response.setHeader("Content-disposition", "inline; filename=" + name);
    response.setContentLength(data.length);  
    response.getOutputStream().write(data);
    response.getOutputStream().flush();
  }
}
