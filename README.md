# Code Challenge

## Spring | Maven | AngularJS | Boostrap
### Start the application
- Select "Open Projects from File System" if you are using Spring STS
- run /src/main/java/hello/Application.java 
### File Structure
             └── todo
                  └── src
                        └── main
                              └── java
                                    └── hello
                                          └── Application.java     -- Run this file to boostrap the Spring Application
                                          └── ToDo.java            -- ToDo Model Definition
                                          └── ToDoService.java     -- Fetch result from 3rd party API & provide data access to controller
                                          └── ToDoController.java  -- Provide RESTful services
                              └── resources
                                    └── static
                                          └── Controllers     
                                                └── todo.js        -- AngularJS Controller, fetch data from Spring RESTful API, client side rendering logics
                                          └── app.js               -- Initialize AngularJS
                                          └── index.html           -- Main page of the project, Load Angular and Bootstrap
