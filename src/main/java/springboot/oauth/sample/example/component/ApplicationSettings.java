package springboot.oauth.sample.example.component;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * application.ymlのkey: application配下のものをbean化するクラス
 * getter/setterが必要
 * このクラスを@Autowiredして使う
 * application.ymlにキーが追加された場合、このクラスも修正する
 */
@Component
@ConfigurationProperties(prefix="application")
public class ApplicationSettings {

  //TODO: application.ymlに合わせてpropertyを追加する
  public String contextPath;

  public String mainPath;

  public Password password;
  
  public static class Password {
    public Boolean checkSize;
    public int minSize;
    public int maxSize;
    public int lockoutCount;
    public Boolean allowCntainId;
    public Boolean allowChangeSameday;
    public int exceedDate;
    public Boolean getCheckSize() {
      return checkSize;
    }
    public void setCheckSize(Boolean checkSize) {
      this.checkSize = checkSize;
    }
    public int getMinSize() {
      return minSize;
    }
    public void setMinSize(int minSize) {
      this.minSize = minSize;
    }
    public int getMaxSize() {
      return maxSize;
    }
    public void setMaxSize(int maxSize) {
      this.maxSize = maxSize;
    }
    public int getLockoutCount() {
      return lockoutCount;
    }
    public void setLockoutCount(int lockoutCount) {
      this.lockoutCount = lockoutCount;
    }
    public Boolean getAllowCntainId() {
      return allowCntainId;
    }
    public void setAllowCntainId(Boolean allowCntainId) {
      this.allowCntainId = allowCntainId;
    }
    public Boolean getAllowChangeSameday() {
      return allowChangeSameday;
    }
    public void setAllowChangeSameday(Boolean allowChangeSameday) {
      this.allowChangeSameday = allowChangeSameday;
    }
    public int getExceedDate() {
      return exceedDate;
    }
    public void setExceedDate(int exceedDate) {
      this.exceedDate = exceedDate;
    }
  }
  
    public String getContextPath() {
    return contextPath;
  }

  public void setContextPath(String contextPath) {
    this.contextPath = contextPath;
  }

  public String getMainPath() {
    return mainPath;
  }

  public void setMainPath(String mainPath) {
    this.mainPath = mainPath;
  }

  public Password getPassword() {
    return password;
  }

  public void setPassword(Password password) {
    this.password = password;
  }

}
