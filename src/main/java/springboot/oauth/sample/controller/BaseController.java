package springboot.oauth.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controllerの基底クラス
 */
public abstract class BaseController {

  /**
   * Logger
   */
  protected final Logger logger = LoggerFactory.getLogger(
    getClass()
  );
}
