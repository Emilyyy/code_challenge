package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class TodoController {

    @GetMapping("/todos")
    @ResponseBody
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        String url = "https://jsonplaceholder.typicode.com/todos";
        
        RestTemplate restTemplate = new RestTemplate();
        String todos = restTemplate.getForObject(url,String.class);
        
        return todos;
    }

}
