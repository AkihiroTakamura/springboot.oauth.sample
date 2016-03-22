package springboot.oauth.sample.core.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

/**
 * 型変換カスタマイズクラス
 * springの型変換サービスクラスの拡張
 * 日付文字列・日時文字列をDateやLocalDataに変換
 * 
 * <pre>{@code
 * @Autowired
 * ConversionService conversionService;
 * }</pre>
 * 
 * <pre>{@code
 * Integer i = conversionService.convert("2015", Integer.class);
 * Long l = conversionService.convert("2015", Long.class);
 * BigDecimal b = conversionService.convert("124.45", BigDecimal.class);
 * String s  = conversionService.convert(120, String.class);
 * String[] ary = conversionService.convert("a,b,c", String[].class);
 * List<String> list = conversionService.convert("aa", List.class); 
 * 
 * Date d = conversionService.convert("2015-10-03", Date.class);
 * Date d = conversionService.convert("2015-10-03 12:00:01", Date.class);
 * LocalDate ld = conversionService.convert("2015-10-03", LocalDate.class);
 * LocalDateTime ld = conversionService.convert("2015-10-03 12:00:02", LocalDateTime.class);
 * }</pre>
 * 
 * @author akihiro takamura
 *
 */
@Configuration
public class ConversionConfig {

  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  
  @Bean
  public static ConversionService conversionService() {

    FormattingConversionServiceFactoryBean factoryBean = new FormattingConversionServiceFactoryBean();
    factoryBean.setConverters(getConverters());
    factoryBean.afterPropertiesSet();
    ConversionService conversionService = factoryBean.getObject();

    return conversionService;
  }
  
  private static Set<Converter<String, ?>> getConverters() {
    Set<Converter<String, ?>> converters = new HashSet<Converter<String, ?>>();

    converters.add(
      new Converter<String, Date>() {
        @Override
        public Date convert(String str) {
          SimpleDateFormat sdftime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          try {
            return sdftime.parse(str);
          } catch (ParseException e) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
              return sdf.parse(str);
            } catch (ParseException e2) {
              return null;
            }
          }
        }
    });

    converters.add(
      new Converter<String, LocalDate>() {
        @Override
        public LocalDate convert(String str) {
          return LocalDate.parse(str, DATE_FORMATTER);
        }
    });

    converters.add(
      new Converter<String, LocalDateTime>() {
        @Override
        public LocalDateTime convert(String str) {
          return LocalDateTime.parse(str, DATETIME_FORMATTER);
        }
    });

    return converters;
  }
  
}
