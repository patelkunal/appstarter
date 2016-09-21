package org.coderearth.kitchens.appstarter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kunal_patel on 9/21/16.
 */
@RestController
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("hello world !!");
    }

}
