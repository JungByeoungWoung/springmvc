package hello.springmvc.basic;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//return할 때 기존에는 컨트롤러에서 리턴시 스프링은 view의 이름을 반환하는 것으로
//되어있기 때문에 String 반환이 달라진다.
//따라서 @RestController를 사용할 경우 반환 값을 원하는 string으로 하고
//html에 body 값에 return할 string 값을 넣어준다.
@RestController
//롬복이 제공하는 로그
@Slf4j
public class LogTestController {
    //getLogger(LogTestController.class); 현재 내 클래스를 파라미터 값으로 넣어준다.
    //getLogger(getClass())로 사용가능

    //private final Logger log = LoggerFactory.getLogger(LogTestController.class);

    @GetMapping("/log-test")
    public String logTest(){
        String name = "Spring";
        //기존 방식
        System.out.println("name = "+name);
        //로그를 사용할 때
        //{} 부분에 String 변수 값이 치환 되서 출력
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);//debug는 개발 환경에서 많이 사용
        log.info("info log ={}", name);//info는 운영 시스템에서 꼭 확인해야하는 정보
        log.warn("warn log={}", name);//경고성 로그
        log.error("error log={}",name);//에러성 로그

        return "ok";
    }
}
