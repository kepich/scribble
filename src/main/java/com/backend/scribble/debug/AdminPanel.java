package com.backend.scribble.debug;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@ConditionalOnProperty("app.is_debugging")
@Controller
public class AdminPanel {
    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String getIndex(Model model) {
        return "index";
    }
}
