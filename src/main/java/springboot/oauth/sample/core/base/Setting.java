package springboot.oauth.sample.core.base;

import springboot.oauth.sample.example.component.ApplicationSettings;

/**
 * Interface for Application Config
 * @author akihiro
 *
 */
public interface Setting {
  /**
   * application.ymlの情報を取得する
   * ApplicationSettingsクラスにapplication.ymlの情報を定義する
   * @return ApplicationSettings
   */
  ApplicationSettings getSetting();

}
