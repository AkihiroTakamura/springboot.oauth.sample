package springboot.oauth.sample.util;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.domingosuarez.boot.autoconfigure.jade4j.JadeHelper;

@JadeHelper("util")
public class JadeUtil {
  
  /**
   * get auth information from securityContextHolder
   * @return
   */
  private Authentication getAuth() {
    return SecurityContextHolder.getContext().getAuthentication();
  }
  
  public Boolean isLogined() {
    return (!(getAuth() instanceof AnonymousAuthenticationToken));
  }
  
  public String username() {
    if (!isLogined()) return null; 

    return getAuth().getName();
  }
  
  public Boolean hasRole(String role) {
    for (GrantedAuthority authority : getAuth().getAuthorities()) {
      if (role.equals(authority.getAuthority()))
        return true;
    }
    return false;    
  }
  
}
