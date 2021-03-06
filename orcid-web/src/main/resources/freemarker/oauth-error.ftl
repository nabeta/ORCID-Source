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
<@public classes=['home'] nav="signin">

<div class="row">
    <div class="col-md-offset-3 col-md-9 col-sm-12 col-xs-12">
        <#if error??>
        	<p>${error}</p>
        <#else>
        	<p><@orcid.msg 'oauth.errors.other' /></p>
        </#if>        
    </div>
</div>
</@public>