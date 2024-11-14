package com.jinsungwon99.post.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/model/feed")
public class FeedModelController {

    @GetMapping
    public ModelAndView feedForm(){

        ModelAndView modelAndView =new ModelAndView();

        modelAndView.setViewName("image/feed");
        return modelAndView;
    }
    @GetMapping("/post")
    public ModelAndView createPostForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("image/uploadPost");
        return modelAndView;
    }
}
