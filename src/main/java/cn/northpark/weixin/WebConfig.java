package cn.northpark.weixin;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.MultipartConfigElement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;

@SpringBootApplication()
@EnableTransactionManagement
@MapperScan("cn.northpark.weixin.dao")
@EnableScheduling
public class WebConfig extends WebMvcConfigurerAdapter {
	private CorsConfiguration buildConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		return corsConfiguration;
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", buildConfig());
		return new CorsFilter(source);
	}

//	@Bean(name = "viewResolver")
//	public InternalResourceViewResolver getViewResolver() {
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		viewResolver.setPrefix("/WEB-INF/jsp/");
//		viewResolver.setSuffix(".jsp");
//		viewResolver.setViewClass(JstlView.class);
//		return viewResolver;
//	}

	@Bean(name = "multipartResolver")
	public MultipartConfigElement getMultipartResolver() {
		return new MultipartConfigFactory().createMultipartConfig();
	}
	
	@Bean(name = "contextSourceTarget")
	public InternalResourceViewResolver getLdapContextSource() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		return viewResolver;
	}
	

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated method stub
		converters.add(getStringMessageConverter());
		converters.add(getJsonConverter());
	}

	private StringHttpMessageConverter getStringMessageConverter() {
		StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
		List<MediaType> supportedMediaTypes = new ArrayList<>();
		supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		stringConverter.setDefaultCharset(Charset.forName("UTF-8"));
		return stringConverter;
	}

	private FastJsonHttpMessageConverter4 getJsonConverter() {
		FastJsonHttpMessageConverter4 jsonConverter = new FastJsonHttpMessageConverter4();
		FastJsonConfig fjc = new FastJsonConfig();
		fjc.setCharset(Charset.forName("UTF-8"));
		fjc.setDateFormat("yyyy-MM-dd");
		fjc.setSerializerFeatures(SerializerFeature.PrettyFormat);
		jsonConverter.setFastJsonConfig(fjc);
		List<MediaType> supportedMediaTypes = new ArrayList<>();
		supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		supportedMediaTypes.add(MediaType.TEXT_HTML);
		jsonConverter.setSupportedMediaTypes(supportedMediaTypes);
		return jsonConverter;
	}

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		ConfigurableApplicationContext context = SpringApplication.run(WebConfig.class, args);
	}
}
