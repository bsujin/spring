package kr.or.ddit.ioc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
//다른 파일 추가할때 사용 
@ImportResource("classpath:/kr/or/ddit/config/spring/datasource-context.xml")
@ComponentScan(basePackages = {"kr.or.ddit"})
@Configuration
public class ComponentScanJavaConfig {

	
}
