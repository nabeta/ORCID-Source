package org.orcid.frontend.web.controllers;

import javax.annotation.Resource;

import org.orcid.core.manager.ProfileEntityManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = { "/orcid-social" })
public class SocialController extends BaseController {

    @Resource
    ProfileEntityManager profileEntityManager;
    
    @RequestMapping(value = { "/twitter" }, method = RequestMethod.GET)
    public void setTwitterKeyToProfileGET(@RequestParam("oauth_token") String token, @RequestParam("oauth_verifier") String verifier ) {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Twitter callback has been recieved GET: " + token  + " -> " + verifier);
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");   
        String orcid = getEffectiveUserOrcid();
        
        profileEntityManager.enableTwitter(orcid, verifier);
    }
}
