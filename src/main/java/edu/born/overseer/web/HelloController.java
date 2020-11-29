package edu.born.overseer.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = HelloController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class HelloController {

    public static final String REST_URL = "/rest/hello";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Message getAll() {
        return new Message("Hello,", "Web!");
    }
    class Message {
        private String firstLine;
        private String secondLine;

        public Message(String firstLine, String secondLine) {
            this.firstLine = firstLine;
            this.secondLine = secondLine;
        }
    }
}

