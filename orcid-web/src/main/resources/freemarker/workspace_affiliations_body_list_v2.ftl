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

<#include "includes/affiliate/del_affiliate_inc.ftl"/>

<#include "includes/affiliate/add_affiliate_inc.ftl"/>
<div ng-controller="AffiliationCtrl">
	<!-- Education -->
	<div id="workspace-education" class="workspace-accordion-item workspace-accordion-active" >
		<div class="workspace-accordion-header">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-12">
					<a name='workspace-educations' />
				    <a href="" ng-click="workspaceSrvc.toggleEducation($event)" ng-click="workspaceSrvc.toggleEducation($event)" class="toggle-text">
				  		<i class="glyphicon-chevron-down glyphicon x075" ng-class="{'glyphicon-chevron-right':workspaceSrvc.displayEducation==false}"></i>
				  		<@orcid.msg 'org.orcid.jaxb.model.message.AffiliationType.education'/> (<span ng-bind="affiliationsSrvc.educations.length"></span>)
				  	</a>
				  	<!-- 
				   	<a href="" ng-click="workspaceSrvc.toggleEducation($event)" class="toggle-text"><@orcid.msg 'org.orcid.jaxb.model.message.AffiliationType.education'/></a>
				   	 --> 
			    </div>
			    <div class="col-md-9 col-sm-9 col-xs-12 action-button-bar" ng-show="workspaceSrvc.displayEducation">
			    	<!-- Sort -->
       				<div class="sort-menu-container">			       					 
	       				<a class="action-option manage-button toggle-menu" ng-click="">
							<span class="glyphicon glyphicon-sort"></span>							
							<@orcid.msg 'manual_orcid_record_contents.sort'/>
						</a>
						<ul class="sort-menu-options">
							<li><a href="" ng-click=""><@orcid.msg 'manual_orcid_record_contents.sort_title'/> <span class=""></span></a></li>
							<li><a href="" ng-click="" class="checked"><@orcid.msg 'manual_orcid_record_contents.sort_data'/> <span class="glyphicon glyphicon-ok pull-right"></span></a></li>
							<li><a href="" ng-click=""><@orcid.msg 'manual_orcid_record_contents.sort_type'/> <span class=""></span></a></li>
							<li><a href="" ng-click=""><@orcid.msg 'manual_orcid_record_contents.sort_source'/> <span class=""></span></a></li>
						</ul>
					</div>					
					<ul class="workspace-bar-menu">
                			<!-- Manage view -->		                			
	        				<li>
	        					<a href="" class="action-option manage-button two-options" ng-click="">
									<span class="glyphicon glyphicon-cog"></span>
									<@orcid.msg 'manual_orcid_record_contents.manage_view'/>
								</a>	        				
	        				</li>
	        				<!-- Link Manually -->
	        				<li>		        			
								<a href="" class="action-option manage-button two-options" ng-click="addAffiliationModal('education')">
									<span class="glyphicon glyphicon-plus"></span>
									<@orcid.msg 'manual_orcid_record_contents.link_manually'/>
								</a>
	        				</li>
					</ul>
				</div>
			</div>
		</div>
		<div ng-show="workspaceSrvc.displayEducation" class="workspace-accordion-content">
			<#include "includes/affiliate/edu_body_inc.ftl" />
		</div>
	</div>
	<!-- Employment -->
	<div id="workspace-employment" class="workspace-accordion-item workspace-accordion-active" >
		<div class="workspace-accordion-header">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-12">
					<a name='workspace-employments' />
				    <a href="" ng-click="workspaceSrvc.toggleEmployment($event)" ng-click="workspaceSrvc.toggleEmployment($event)" class="toggle-text">
				  		<i class="glyphicon-chevron-down glyphicon x075" ng-class="{'glyphicon-chevron-right':workspaceSrvc.displayEmployment==false}"></i>
				  		<@orcid.msg 'org.orcid.jaxb.model.message.AffiliationType.employment'/> (<span ng-bind="affiliationsSrvc.employments.length"></span>)
				   	</a>				   	
				</div>
				<div class="col-md-9 col-sm-9 col-xs-12 action-button-bar" ng-show="workspaceSrvc.displayEmployment">
					<!-- Sort -->
       				<div class="sort-menu-container">			       					 
	       				<a class="action-option manage-button toggle-menu" ng-click="">
							<span class="glyphicon glyphicon-sort"></span>							
							<@orcid.msg 'manual_orcid_record_contents.sort'/>
						</a>
						<ul class="sort-menu-options">
							<li><a href="" ng-click=""><@orcid.msg 'manual_orcid_record_contents.sort_title'/> <span class=""></span></a></li>
							<li><a href="" ng-click="" class="checked"><@orcid.msg 'manual_orcid_record_contents.sort_data'/> <span class="glyphicon glyphicon-ok pull-right"></span></a></li>
							<li><a href="" ng-click=""><@orcid.msg 'manual_orcid_record_contents.sort_type'/> <span class=""></span></a></li>
							<li><a href="" ng-click=""><@orcid.msg 'manual_orcid_record_contents.sort_source'/> <span class=""></span></a></li>
						</ul>
					</div>
					<ul class="workspace-bar-menu">
                			<!-- Manage view -->		                			
	        				<li>
	        					<a href="" class="action-option manage-button two-options" ng-click="">
									<span class="glyphicon glyphicon-cog"></span>
									<@orcid.msg 'manual_orcid_record_contents.manage_view'/>
								</a>	        				
	        				</li>
	        				<!-- Link Manually -->
	        				<li>		        			
								<a href="" class="action-option manage-button two-options" ng-click="addAffiliationModal('employment')">
									<span class="glyphicon glyphicon-plus"></span>
									<@orcid.msg 'manual_orcid_record_contents.link_manually'/>
								</a>				
	        				</li>
					</ul>	
				</div>
			</div>
		</div>
		<div ng-show="workspaceSrvc.displayEmployment" class="workspace-accordion-content">
			<#include "includes/affiliate/emp_body_inc.ftl" />
		</div>
	</div>
	<!-- Affiliations -->
	<!-- This section still requires styling -->
	<div ng-show='affiliationsSrvc.affiliations.length != 0' id="workspace-affiliations" class="workspace-accordion-item workspace-accordion-active" ng-cloak>	
		<div class="workspace-accordion-header">
			<div class="row">
				<div class="col-md-6 col-sm-6 col-xs-12 action-button-bar">
					<a name='workspace-affiliations' />
				    <a href="" ng-click="workspaceSrvc.toggleAffiliations($event)" class="toggle-text">
				  		<i class="glyphicon-chevron-down glyphicon x075" ng-class="{'glyphicon-chevron-right':workspaceSrvc.displayAffiliations==false}"></i>
				  		<@orcid.msg 'workspace_bio.Affiliations'/>
				   	</a>				    
				 </div>
		    </div>
		</div>
		<div ng-show="workspaceSrvc.displayAffiliations" class="workspace-accordion-content">
			<#include "includes/affiliate/aff_body_inc.ftl" />		
		</div>
	</div>
</div>
    