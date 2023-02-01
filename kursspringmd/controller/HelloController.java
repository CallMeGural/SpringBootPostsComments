package pl.fg.kursspringmd.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.fg.kursspringmd.service.HelloService;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final HelloService helloService;

    //@Autowired - not needed
    /*public HelloController(HelloService helloService) { // - not needed cuz of lombok requiredArgsConstructor
        this.helloService = helloService;
    }*/

    @GetMapping("/")
    public String hello() {
        return helloService.hello();
    }
}
