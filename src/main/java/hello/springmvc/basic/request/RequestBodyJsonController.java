package hello.springmvc.basic.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

//json 객체로 요청 파라미터를 받을 경우
@Slf4j
@Controller
public class RequestBodyJsonController {
    //json 객체를 받기 위해서는 objectmapper를 생성 해줘야한다.
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        InputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        //messagebody 값을 조회 해서 HelloData 클래스의 값에 맞으면 저장
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={},age={}", helloData.getUsername(), helloData.getAge());

        response.getWriter().write("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws JsonProcessingException {
        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        log.info("username = {},age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    //@RequestBody HelloData helloData 하면 @RequestBody에 직접 만든 객체를 지정 가능하다.
    public String requestBodyJsonV3(@RequestBody HelloData helloData) throws JsonProcessingException {

        log.info("username = {},age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
    @ResponseBody
    @PostMapping("/request-body-json-v4")
    //@RequestBody HelloData helloData 하면 @RequestBody에 직접 만든 객체를 지정 가능하다.
    public String requestBodyJsonV4(HttpEntity<HelloData>  httpEntity) throws JsonProcessingException {
        //HttpEntity를 사용함으로써 body에 들어온 json 객체 값들을 getBody()로 꺼내야한다.
        HelloData data = httpEntity.getBody();
        log.info("username = {},age={}", data.getUsername(), data.getAge());
        return "ok";
    }

    //json 객체로 요청을 받았는데 위의 예시와 다른게 json 객체로 응답해줄 수 있게 하는 예시
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData) {
        log.info("username ={},age={}", helloData.getUsername(), helloData.getAge());
        return helloData;
    }
}
