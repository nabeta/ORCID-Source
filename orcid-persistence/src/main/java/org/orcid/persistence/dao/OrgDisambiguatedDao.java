/**
 * =============================================================================
 *
 * ORCID (R) Open Source
 * http://orcid.org
 *
 * Copyright (c) 2012-2013 ORCID, Inc.
 * Licensed under an MIT-Style License (MIT)
 * http://orcid.org/open-source-license
 *
 * This copyright and license information (including a link to the full license)
 * shall be included in its entirety in all copies or substantial portion of
 * the software.
 *
 * =============================================================================
 */
package org.orcid.persistence.dao;

import java.util.List;

import org.orcid.jaxb.model.message.Iso3166Country;
import org.orcid.persistence.jpa.entities.OrgDisambiguatedEntity;

/**
 * 
 * @author Will Simpson
 * 
 */
public interface OrgDisambiguatedDao extends GenericDao<OrgDisambiguatedEntity, Long> {

    List<OrgDisambiguatedEntity> getOrgs(String searchTerm, int firstResult, int maxResults);

    OrgDisambiguatedEntity findBySourceIdAndSourceType(String sourceId, String sourceType);

    OrgDisambiguatedEntity findByNameCityRegionCountryAndSourceType(String name, String city, String region, Iso3166Country country, String sourceType);

}