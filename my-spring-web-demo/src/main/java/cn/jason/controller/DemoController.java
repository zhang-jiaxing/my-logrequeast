package cn.jason.controller;

import cn.jason.annotation.LogRequest;
import cn.jason.pojo.Demo;
import org.springframework.web.bind.annotation.*;

import static cn.jason.annotation.RequestParamType.*;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @GetMapping("/test")
    @LogRequest(paramType = PARAM)
    public String demo(@RequestParam("name") String name) {
        return name;
    }
    @PostMapping("/test2")
    @LogRequest(paramType = BODY)
    public Demo demo2(@RequestBody Demo demo) {
        return demo;
    }
    @GetMapping("/test3/{name}")
    @LogRequest(paramType = VARIABLE)
    public String demo3(@PathVariable("name") String name) {
        return name;
    }
    @GetMapping("/test3/{name}/{age}")
    @LogRequest(paramType = VARIABLE)
    public String demo3(@PathVariable("name") String name,@PathVariable("age") Integer age) {
        return name+":"+age;
    }
}
