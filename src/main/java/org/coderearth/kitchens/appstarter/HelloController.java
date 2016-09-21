package org.coderearth.kitchens.appstarter;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * Created by kunal_patel on 9/21/16.
 */
@RestController
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("hello world !!");
    }

    @RequestMapping(value = "/hi", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> sayJsonHello() {
        return ResponseEntity.ok(ImmutableMap.of("message", "hello world !!"));
    }

    @PostConstruct
    protected void checkInit() {
        LOGGER.info("Started checking post-initialization in HelloController !!");
        LOGGER.info("finished checking post-initialization in HelloController !!");
    }

}
