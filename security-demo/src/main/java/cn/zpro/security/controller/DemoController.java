package cn.zpro.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author leon
 */
@Controller
public class DemoController {

    @GetMapping
    public ModelAndView index(ModelAndView view){
        System.out.println("hello");
        view.setViewName("index");
        return view;
    }
}
