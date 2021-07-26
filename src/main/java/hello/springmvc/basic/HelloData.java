package hello.springmvc.basic;

import lombok.Data;

//롬복에서 제공하는 get ,set , 생성자 등을 모두 한번에 자동으로 만들어주는 어노테이션
@Data
public class HelloData {
    private String username;
    private int age;
}
