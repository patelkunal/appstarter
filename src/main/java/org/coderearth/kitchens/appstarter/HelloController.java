package org.coderearth.kitchens.appstarter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * Created by kunal_patel on 9/21/16.
 */
@RestController
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("hello world !!");
    }

    @PostConstruct
    protected void checkInit() {
        LOGGER.info("Started checking post-initialization in HelloController !!");
        LOGGER.info("finished checking post-initialization in HelloController !!");
    }

}
