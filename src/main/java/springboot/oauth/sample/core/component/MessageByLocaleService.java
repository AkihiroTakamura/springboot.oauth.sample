package springboot.oauth.sample.core.component;

import java.util.Properties;

/**
 * Message Resouce(i18n compatible)
 * 
 * for use
 *   @Autowired
 *   protected MessageByLocaleService messageSource;
 * 
 * @author Akihiro Takamura
 *
 */
public interface MessageByLocaleService {

  /**
   * メッセージリソースからIDでメッセージを取得する
   * 多言語対応済み
   * @param id
   * @return
   */
  public String getMessage(String id);
  
  /**
   * メッセージリソースに保持しているメッセージ一覧を取得する
   * @return
   */
  public Properties getMessages();

}
