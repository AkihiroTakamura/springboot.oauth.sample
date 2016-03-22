package springboot.oauth.sample.core.base;

/**
 * Interface of Authentification information
 * 
 * @author Akihiro Takamura
 *
 */
public interface Auth {
  
  /**
   * returns login status
   * @return
   */
  Boolean isLogined();
    
  /**
   * returns username if logined.
   * returns null if not logined.
   * @return username or null
   */
  String getUserName();

  
  /**
   * returns true if user has role.
   * @param role role-name
   * @return true: has role
   */
  Boolean hasRole(String role);

}
