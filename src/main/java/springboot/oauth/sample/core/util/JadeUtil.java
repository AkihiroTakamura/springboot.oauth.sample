package springboot.oauth.sample.core.util;

import springboot.oauth.sample.core.base.impl.BaseImpl;

import com.domingosuarez.boot.autoconfigure.jade4j.JadeHelper;

/**
 * helper function for jade
 * example.jade
 * 
 *   if util.isLogined()
 *    p you logined successfully.
 *    p!= 'your name is ' + util.getUserName()
 *    if util.hasRole('admin')
 *      p you have admin role!
 *      
 * 'util' object is created automatically by @JadeHelper annotation.
 * 
 * @author akihiro
 *
 */
@JadeHelper("util")
public class JadeUtil extends BaseImpl {
  
  
}
