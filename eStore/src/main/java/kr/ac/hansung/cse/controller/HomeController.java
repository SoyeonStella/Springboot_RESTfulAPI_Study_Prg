package kr.ac.hansung.cse.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */

// servlet-context.xml의 <context:component-scan />이 컴포넌트 스캔을 하여 
// 아래의 컨트롤러 어노테이션이 있는 부분을 빈으로 등록해준다. 
@Controller
public class HomeController {
	
	// HomeController.class라는 이름의 Logger 생성 
	// private static final Logger logger 
	// 		= LoggerFactory.getLogger(kr/ac/hansung/cse/controller/HomeController.java); 과 동일함.
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//리퀘스트가 루트로 들어오면 home.jsp로 넘어간다. 
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Locale locale) {
		
		// logger level : trace -> debug -> info -> warn -> error 암기하기! 
		// logback 설정 파일에서 kr.ac.hansung.cse라는 이름을 갖는 logger에 대해
		// logger level을 debug로 주었기때문에 logger.trace() 로깅 리퀘스트는 출력되지 않는다.
		// HomeController.class logger는 kr.ac.hansung.cse logger의 하위 계층 로거이고,
		// 별도로 로거 레벨을 지정해주지 않았기때문에 kr.ac.hansung.cse logger의 레벨을 상속받아
		// 동일하게 logger.debug() 로깅 리퀘스트 이상부터만 출력된다. 
		logger.trace("trace level : Welcome home! The client loclae is {}", locale);
		logger.debug("debug level : Welcome home! The client loclae is {}", locale);
		logger.info("info level : Welcome home! The client loclae is {}", locale);
		logger.warn("warn level : Welcome home! The client loclae is {}", locale);
		logger.error("error level : Welcome home! The client loclae is {}", locale);
		
		// 사용자 url을 HttpServletRequest request로 받아 로깅 하기 
		String url = request.getRequestURL().toString();
		String clientIPaddress = request.getRemoteAddr();
		// placeholder {} 를 사용해 인자가 있는 로그메시지를 오버헤드를 줄여서 출력함 
		logger.info("Request URL : {},  Client IP : {}", url, clientIPaddress);

		
		// 예전에는 루트로 리퀘스트가 들어오면 home.jsp로 넘어간다. 
		// 이젠 tiles를 사용하기때문에 home이라는 definition으로 넘어간다.
		// " " 안의 이름은 이제 definition 이름이다. 
		return "home";
	}
	
}
