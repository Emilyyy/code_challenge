package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
public class ToDoController {
    @Autowired
    ToDoService toDoService;

    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public ResponseEntity<List<ToDo>> getToDos(@RequestParam(name="page", required=false, defaultValue="1") Integer page) {
        final List<ToDo> todos = toDoService.getToDos(page);
        return new ResponseEntity<List<ToDo>>(todos, HttpStatus.OK);
    }
}
