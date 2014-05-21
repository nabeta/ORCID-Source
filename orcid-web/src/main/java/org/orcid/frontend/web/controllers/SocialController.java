package org.orcid.frontend.web.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.orcid.core.manager.ProfileEntityManager;
import org.orcid.frontend.web.forms.ManagePasswordOptionsForm;
import org.orcid.frontend.web.forms.PreferencesForm;
import org.orcid.jaxb.model.message.OrcidProfile;
import org.orcid.jaxb.model.message.SecurityDetails;
import org.orcid.persistence.jpa.entities.ProfileEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

@Controller
@RequestMapping(value = { "/orcid-social" })
public class SocialController extends BaseWorkspaceController {

    @Resource
    ProfileEntityManager profileEntityManager;
    
    Twitter twitter = null;
    
    public SocialController() {
        twitter = TwitterFactory.getSingleton();
        try {
            twitter.setOAuthConsumer("soCTKWWByfjq91SxuaQRh4Gnk", "sjtMHV2myGQ6qZAoKROoKaNfvRFvyDtIuGn0cKdy5h0RQ55NPM");
        } catch(IllegalStateException ie) {
            
        }
    }
    
    
    @RequestMapping(value = { "/twitter" }, method = RequestMethod.POST)
    public @ResponseBody String goToTwitterAuthPage() throws TwitterException {
        String result = "";        
        RequestToken requestToken = twitter.getOAuthRequestToken();
        result = requestToken.getAuthorizationURL();
        return result;
    }
    
    @RequestMapping(value = { "/twitter" }, method = RequestMethod.GET)
    public ModelAndView setTwitterKeyToProfileGET(@RequestParam("oauth_token") String token, @RequestParam("oauth_verifier") String verifier ) {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Twitter callback has been recieved GET: " + token  + " -> " + verifier);
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");   
        String orcid = getEffectiveUserOrcid();
        profileEntityManager.enableTwitter(orcid, verifier);
        String activeTab = "profile-tab";
        ModelAndView mav = new ModelAndView("manage");
        mav.addObject("showPrivacy", true);
        OrcidProfile profile = getEffectiveProfile();
        mav.addObject("managePasswordOptionsForm", populateManagePasswordFormFromUserInfo());
        mav.addObject("preferencesForm", new PreferencesForm(profile));
        mav.addObject("profile", profile);
        mav.addObject("activeTab", activeTab);
        mav.addObject("securityQuestions", getSecurityQuestions());
        
        return mav;
    }
    
    private ManagePasswordOptionsForm populateManagePasswordFormFromUserInfo() {
        OrcidProfile profile = getEffectiveProfile();

        // TODO - placeholder just to test the retrieve etc..replace with only
        // fields that we will populate
        // password fields are never populated
        OrcidProfile unecryptedProfile = orcidProfileManager.retrieveOrcidProfile(profile.getOrcidIdentifier().getPath());
        ManagePasswordOptionsForm managePasswordOptionsForm = new ManagePasswordOptionsForm();
        managePasswordOptionsForm.setVerificationNumber(unecryptedProfile.getVerificationCode());
        managePasswordOptionsForm.setSecurityQuestionAnswer(unecryptedProfile.getSecurityQuestionAnswer());
        Integer securityQuestionId = null;
        SecurityDetails securityDetails = unecryptedProfile.getOrcidInternal().getSecurityDetails();
        // TODO - confirm that security details aren't null and that we can
        // change schema to be an int for security
        // questions field
        if (securityDetails != null) {
            securityQuestionId = securityDetails.getSecurityQuestionId() != null ? new Integer((int) securityDetails.getSecurityQuestionId().getValue()) : null;
        }

        managePasswordOptionsForm.setSecurityQuestionId(securityQuestionId);

        return managePasswordOptionsForm;
    }
    
    public Map<String, String> getSecurityQuestions() {
        Map<String, String> securityQuestions = securityQuestionManager.retrieveSecurityQuestionsAsInternationalizedMap();
        Map<String, String> securityQuestionsWithMessages = new LinkedHashMap<String, String>();
        for (String key : securityQuestions.keySet()) {
            securityQuestionsWithMessages.put(key, getMessage(securityQuestions.get(key)));
        }
        return securityQuestionsWithMessages;
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
