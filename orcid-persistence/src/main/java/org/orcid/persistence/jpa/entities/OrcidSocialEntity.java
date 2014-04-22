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
package org.orcid.persistence.jpa.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.orcid.persistence.jpa.entities.keys.OrcidSocialPk;
import org.orcid.persistence.jpa.entities.keys.ProfileWorkEntityPk;

/**
 * @author Angel Montenegro
 */

@Entity
@Table(name = "orcid_social")
public class OrcidSocialEntity extends BaseEntity<OrcidSocialPk> {

    private ProfileEntity owner;
    private String type;
    private String jsonKeys;
    
    @Override
    @Transient
    public OrcidSocialPk getId() {
        return null;
    }

    @Id
    @ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
    @JoinColumn(name = "orcid", nullable = false)
    public ProfileEntity getOwner() {
        return owner;
    }

    public void setOwner(ProfileEntity owner) {
        this.owner = owner;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "json_keys")
    public String getJsonKeys() {
        return jsonKeys;
    }

    public void setJsonKeys(String jsonKeys) {
        this.jsonKeys = jsonKeys;
    }

}
