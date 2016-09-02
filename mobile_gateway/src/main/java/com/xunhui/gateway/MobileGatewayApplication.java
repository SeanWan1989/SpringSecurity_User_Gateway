package com.xunhui.gateway;

import com.xunhui.gateway.config.SecurityConfiguration;
import com.xunhui.gateway.service.session.SessionService;
import com.xunhui.gateway.service.user.UserAccountService;
import com.xunhui.gateway.utils.spring.SpringTool;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@RestController
@EnableZuulProxy
@Import({SecurityConfiguration.class})
public class MobileGatewayApplication implements CommandLineRunner {
	public static UserAccountService userAccountService;
	public static SessionService sessionService;

	@Override
	public void run(String... arg0) {
		userAccountService = SpringTool.getBean("userAccountService");
		sessionService = SpringTool.getBean("sessionService");
	}

	@RequestMapping("/user")
	public String user(Principal user) {
		return sessionService.findUserRedisSession();
	}

	@RequestMapping(value = "/csrfCookie")
	public String csrfCookie() {
		return "hi";
	}

	public static void main(String[] args) {
		SpringApplication.run(MobileGatewayApplication.class, args);
	}
}
