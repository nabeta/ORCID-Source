package org.orcid.frontend.web.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.orcid.core.manager.ProfileEntityManager;
import org.orcid.persistence.jpa.entities.ProfileEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

@Controller
@RequestMapping(value = { "/orcid-social" })
public class SocialController extends BaseController {

    @Resource
    ProfileEntityManager profileEntityManager;
    
    @RequestMapping(value = { "/twitter" }, method = RequestMethod.POST)
    public @ResponseBody String goToTwitterAuthPage() throws TwitterException {
        String result = "";
        Twitter twitter = TwitterFactory.getSingleton();
        try {
            twitter.setOAuthConsumer("soCTKWWByfjq91SxuaQRh4Gnk", "sjtMHV2myGQ6qZAoKROoKaNfvRFvyDtIuGn0cKdy5h0RQ55NPM");
        } catch(IllegalStateException ie) {
            
        }
        RequestToken requestToken = twitter.getOAuthRequestToken();
        result = requestToken.getAuthorizationURL();
        return result;
    }
    
    @RequestMapping(value = { "/twitter" }, method = RequestMethod.GET)
    public void setTwitterKeyToProfileGET(@RequestParam("oauth_token") String token, @RequestParam("oauth_verifier") String verifier ) {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Twitter callback has been recieved GET: " + token  + " -> " + verifier);
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");   
        String orcid = getEffectiveUserOrcid();
        
        profileEntityManager.enableTwitter(orcid, verifier);
    }
    
    @RequestMapping(value = { "/disable-twitter" }, method = RequestMethod.POST)
    public @ResponseBody boolean disableTwitter() throws TwitterException {
        String orcid = getEffectiveUserOrcid();
        return profileEntityManager.disableTwitter(orcid);
    }
    
    public void processTwitterNotifications() throws TwitterException {
        List<ProfileEntity> profiles = profileEntityManager.getAllProfilesToTweet();
        Twitter twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer("soCTKWWByfjq91SxuaQRh4Gnk", "sjtMHV2myGQ6qZAoKROoKaNfvRFvyDtIuGn0cKdy5h0RQ55NPM");
        RequestToken requestToken = twitter.getOAuthRequestToken();
        
        
        for(ProfileEntity profile : profiles) {
            String pin = profile.getTwitter();
            AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, pin);
            twitter.updateStatus("I just updted my ORCID profile, checking out here! " + getBaseUri() + '/' + profile.getId());
        }
    }
}
