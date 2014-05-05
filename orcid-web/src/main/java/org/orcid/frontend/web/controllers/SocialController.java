package org.orcid.frontend.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = { "/orcid-social" })
public class SocialController extends BaseController {

    @RequestMapping(value = { "/twitter" }, method = RequestMethod.GET)
    public ModelAndView setTwitterKeyToProfile() {
        ModelAndView mav = new ModelAndView("manage");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Twitter callback has been recieved");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        return mav;
    }

}
