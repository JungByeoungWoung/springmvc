package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/hello-basic", method = RequestMethod.GET)
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    @GetMapping(value = "mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapping-get-v2");
        return "ok";
    }

    /**
     * @param data
     * @return
     * @PathVariable 사용
     * 경로 변수라고 함(url?userA 이런 방식은 쿼리 파라미터 방식이라고 함)
     * @PathVariable("userId") String data은
     * 실제 url에 파라미터 값을 넣어서 호출 하면 해당 데이터 값을 조회 가능하다.
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}", data);
        return "ok";
    }

    /**
     * PathVariable 사용 다중
     *
     * @param userId
     * @param orderId
     * @return http://localhost:8080/mapping/users/userA/orders/100라고 호출시
     * users에 있는 userA의 oreders의 값을 조회 한다는 의미이다.
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId={},orderId={}", userId, orderId);
        return "ok";
    }

    /**
     * 특정 헤더로 추가 맵핑
     * 헤더 값에 headers = "mode=debug" 값을 넣지 않으면
     * mode는 키값 / debug는 value 값
     * 405 오류를 발생시킨다.
     *
     * @return
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingPath() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * consumes = "application/json" -> 컨텐트 타입을 직접 지정해 줄 수 있다.
     *consume -> 요청 헤더의 컨텐트 타입을 의미
     * @return
     */
    @GetMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsume() {
        log.info("mappingConsume");
        return "ok";
    }
    /**
     *produces -> 컨트롤러가 생산해 내는 컨텐트 타입을 의미한다/요청헤더의 Accept 기반으로 맵핑
     * Accept -> 클라이언트 입장에서 받아들일 수 있는 요청 정보를 정하는 것
     */
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces(){
        log.info("mappingProduces");
        return "ok";
    }
}
