package org.orcid.frontend.web.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.orcid.core.manager.ProfileEntityManager;
import org.orcid.frontend.web.forms.ManagePasswordOptionsForm;
import org.orcid.frontend.web.forms.PreferencesForm;
import org.orcid.jaxb.model.message.OrcidProfile;
import org.orcid.jaxb.model.message.SecurityDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import twitter4j.TwitterException;

@Controller
@RequestMapping(value = { "/orcid-social" })
public class SocialController extends BaseWorkspaceController {

    @Resource
    ProfileEntityManager profileEntityManager;    
    
    public SocialController() {
        
    }
    
    @RequestMapping(value = { "/twitter" }, method = RequestMethod.POST)
    public @ResponseBody String goToTwitterAuthPage() throws TwitterException {
        return profileEntityManager.getAuthUrl();
    }
    
    @RequestMapping(value = { "/twitter" }, method = RequestMethod.GET)
    public ModelAndView setTwitterKeyToProfileGET(@RequestParam("oauth_token") String token, @RequestParam("oauth_verifier") String verifier ) throws Exception {
        String orcid = getEffectiveUserOrcid();
        profileEntityManager.enableTwitter(orcid, token, verifier);
        String activeTab = "profile-tab";
        ModelAndView mav = new ModelAndView("manage");
        mav.addObject("showPrivacy", true);
        OrcidProfile profile = getEffectiveProfile();
        mav.addObject("managePasswordOptionsForm", populateManagePasswordFormFromUserInfo());
        mav.addObject("preferencesForm", new PreferencesForm(profile));
        mav.addObject("profile", profile);
        mav.addObject("activeTab", activeTab);
        mav.addObject("twitter", true);
        mav.addObject("securityQuestions", getSecurityQuestions());
        
        return mav;
    }
    
    @RequestMapping(value = { "/twitter/check-twitter-status" }, method = RequestMethod.GET)
    public @ResponseBody boolean isTwitterEnabled(){
        String orcid = getEffectiveUserOrcid();
        return profileEntityManager.getTwitterKey(orcid) != null;
    } 
    
    
    private ManagePasswordOptionsForm populateManagePasswordFormFromUserInfo() {
        OrcidProfile profile = getEffectiveProfile();
        OrcidProfile unecryptedProfile = orcidProfileManager.retrieveOrcidProfile(profile.getOrcidIdentifier().getPath());
        ManagePasswordOptionsForm managePasswordOptionsForm = new ManagePasswordOptionsForm();
        managePasswordOptionsForm.setVerificationNumber(unecryptedProfile.getVerificationCode());
        managePasswordOptionsForm.setSecurityQuestionAnswer(unecryptedProfile.getSecurityQuestionAnswer());
        Integer securityQuestionId = null;
        SecurityDetails securityDetails = unecryptedProfile.getOrcidInternal().getSecurityDetails();
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
}
