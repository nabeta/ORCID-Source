<#--

    =============================================================================

    ORCID (R) Open Source
    http://orcid.org

    Copyright (c) 2012-2013 ORCID, Inc.
    Licensed under an MIT-Style License (MIT)
    http://orcid.org/open-source-license

    This copyright and license information (including a link to the full license)
    shall be included in its entirety in all copies or substantial portion of
    the software.

    =============================================================================

-->
<#if !(affiliationsEmpty)??>
<!-- Education -->
<div id="workspace-education" class="workspace-accordion-item workspace-accordion-active" ng-controller="PublicEduAffiliation" ng-hide="!affiliationsSrvc.educations.length" ng-cloack>	       
      <div class="workspace-accordion-header">
           <a href="" ng-click="toggleEducation()" class="toggle-text">
           <i class="glyphicon-chevron-down glyphicon x0" ng-class="{'glyphicon-chevron-right':displayEducation==false}"></i></a>             
           <a href="" ng-click="toggleEducation()" class="toggle-text">${springMacroRequestContext.getMessage("org.orcid.jaxb.model.message.AffiliationType.education")}</a>                        
      </div>	
      <div ng-show="displayEducation" class="workspace-accordion-content">
		<#include "includes/affiliate/edu_body_inc.ftl" />
      </div>	       
</div>
<!-- Employment -->
<div id="workspace-employment" class="workspace-accordion-item workspace-accordion-active" ng-controller="PublicEmpAffiliation" ng-hide="!affiliationsSrvc.employments.length" ng-cloack>
      <div class="workspace-accordion-header">
           <a href="" ng-click="toggleEmployment()" class="toggle-text">
           <i class="glyphicon-chevron-down glyphicon x0" ng-class="{'glyphicon-chevron-right':displayEmployment==false}"></i></a>             
           <a href="" ng-click="toggleEmployment()" class="toggle-text">${springMacroRequestContext.getMessage("org.orcid.jaxb.model.message.AffiliationType.employment")}</a>                        
      </div>		
      <div ng-show="displayEmployment" class="workspace-accordion-content">
	  		<#include "includes/affiliate/emp_body_inc.ftl" />	
      </div>
</div>
  </#if>
  
  <!-- Funding -->
<#if !(fundingEmpty)??>		
	<div id="workspace-funding" class="workspace-accordion-item workspace-accordion-active" ng-controller="PublicFundingCtrl" ng-cloack>
		<div class="workspace-accordion-header">
	         <a href="" ng-click="toggleFunding()" class="toggle-text">
	         <i class="glyphicon-chevron-down glyphicon x0" ng-class="{'glyphicon-chevron-right':displayFunding==false}"></i></a>             
	         <a href="" ng-click="toggleFunding()" class="toggle-text">${springMacroRequestContext.getMessage("workspace.Funding")}</a>                        
	    </div>	
	    <div ng-show="displayFunding" class="workspace-accordion-content">
			<#include "includes/funding/body_funding_inc.ftl" />	
	    </div>
	</div>
</#if>

<!-- Works -->
<#if !(worksEmpty)??>		
	<div id="workspace-works" class="workspace-accordion-item workspace-accordion-active" ng-controller="PublicWorkCtrl" ng-cloack>
	      <div class="workspace-accordion-header">
	          <a href="" ng-click="toggleWorks()" class="toggle-text">
	          <i class="glyphicon-chevron-down glyphicon x0" ng-class="{'glyphicon-chevron-right':displayWorks==false}"></i></a>             
	          <a href="" ng-click="toggleWorks()" class="toggle-text">${springMacroRequestContext.getMessage("workspace.Works")}</a>                        
	      </div>	
	      <div ng-show="displayWorks" class="workspace-accordion-content">
				<#include "includes/work/public_works_body_list.ftl" />
	      </div>
	</div>
</#if>
    
    
    