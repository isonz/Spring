package cn.ptp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

@EnableDiscoveryClient
@SpringBootApplication
public class ClientTest1 {

	public static void main(String[] args)
	{
		SpringApplication.run(ClientTest1.class, args);
	}

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {

		return (container -> {
			ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/html/401.html");
			ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/html/404.html");
			ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/html/500.html");

			container.addErrorPages(error401Page, error404Page, error500Page);
		});
	}

}
