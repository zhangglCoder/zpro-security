package cn.zpro.security.controller;

import cn.zpro.security.browser.support.ResponesMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
/**
 * @author leon
 */
@Controller
public class DemoController {

    @GetMapping("index")
    public ModelAndView index(ModelAndView view) throws IOException {
        System.out.println("index");
        view.setViewName("index");
        return view;
    }

    @GetMapping("login")
    public ModelAndView login(ModelAndView view) throws IOException {
        System.out.println("login");
        view.setViewName("login");
        return view;
    }

    @GetMapping("abort")
    @ResponseBody
    public ResponesMsg abort() throws IOException {
        System.out.println("abort");
        return new ResponesMsg("abort数据");
    }

}
