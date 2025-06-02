package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import  org.springframework.ui.Model;
import  org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.model.IModel;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model)
    {
        model.addAttribute("data","hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc") // 웹브라우저 주소창에 /hello-mvc라는주소값이 들어올시감지
    public String helloMvc(@RequestParam(value = "name" , required = true) String name, Model model)
       //@RequestParam("name") URL 파라미터로 전달된 name값을 가져옴 + Model model은 뷰에 데이터를 전달하는 객체
    {
        model.addAttribute("name",name); // html코드에서 ${name}으로 작성된 부분에 값을 전달
    return "hello-template"; // resources/templates/hello-template.html 파일을 찾아 웹브라우저에 랜더링 해줌
    }


    @GetMapping("hello-string") // hello-string 경로로 들어오는 GET요청을 처리
    @ResponseBody // http방식에서 body부분에 내가 데이터를 직접 넣어주겠다
    public String helloString(@RequestParam("name") String name) { // 파라미터로 넘어온 name값 받음
        return "hello " + name;
    }

    @GetMapping("hello-api") // URL주소에 /hello-api가 올시 호출
    @ResponseBody // HttepMessageConverter가 동작하여 html BODY부분 내용을 직접 반환
    public Hello helloApi(@RequestParam("name")String name)
    {
        Hello hello = new Hello(); // hello객체생성
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name=name;
        }
    }



    }



