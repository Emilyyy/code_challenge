package hello;


import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service("ToDoService")
public class ToDoService {
    private static List<ToDo> toDos;
    private static RestTemplate rest = new RestTemplate();
    private static String url = "https://jsonplaceholder.typicode.com/todos";
    final static int itemsPerPage = 10;
    private static int maxPages;
    private static List<ToDo> empty = Collections.emptyList();

    
    /**
     * Initialize toDos and maxPages
     */
    static {
        toDos = fetchToDos();
        maxPages = calculatePages();
    }

    /**
     * Fetch ToDos from https://jsonplaceholder.typicode.com/todos
     * @return a list of toDos
     */
    private static List<ToDo> fetchToDos () {
        ResponseEntity<List<ToDo>> response = rest.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<List<ToDo>>(){});
        return response.getBody();
    }

    /**
     * 
     * Get ToDos
     * if page number is 0, return all toDos
     * if page number is less than 0, return empty list
     * if page number is greater than the calculated maximum number of pages, return empty list
     * @param page number is default to 1 in controller
     * @return if page number is within valid range, return elements from (page - 1) multiply itemsPerPage to page multiply itemsPerPage
     * 
     * 		   
     */
    public List<ToDo> getToDos (int page){
       // System.out.println("Page " + page);
    	System.out.println("max page" + maxPages);
    	
   
        if (page == 0) {
            return toDos;
        }
        else if(page < 0) {
        	return empty;
        }
        else if(page > maxPages) {
        	return empty;
        }
        
        return toDos.subList((page - 1) * itemsPerPage, page * itemsPerPage);
    }
    
    
    /**
     * calculate the maximum number of pages based on returned toDos.size() divided by itemsPerPage
     * @return 
     */
    
    public static int calculatePages() {
    	
    	if(toDos.size() % itemsPerPage == 0) {
    		maxPages = toDos.size()/itemsPerPage;
    	}else {
    		maxPages = (toDos.size()/itemsPerPage) + 1;
    	}
    	
    	return maxPages;
    	
    }
}

