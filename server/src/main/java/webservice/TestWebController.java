package webservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestWebController {

    @RequestMapping("/hello")
    public String test(@RequestParam(value="name", defaultValue="World" ) String name) {
        return "Hallo" + name;
    }
}
