package springboot.oauth.sample.core.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {

  /**
   * properties -> HashMap
   * @param prop
   * @return
   */
  public static Map<String, Object> toMap(Properties prop) {
    
    Map<String, Object> map = new HashMap<>();
    
    prop.forEach((key, value) -> {
      map.put((String)key, value);
    });
    return map;
  }
}
