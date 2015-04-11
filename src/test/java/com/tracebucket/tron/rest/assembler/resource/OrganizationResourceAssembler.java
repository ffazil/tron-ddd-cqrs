package com.tracebucket.tron.rest.assembler.resource;

import com.tracebucket.tron.domain.Organization;
import com.tracebucket.tron.rest.resource.OrganizationResource;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by sadath on 06-Apr-15.
 */
@Component
public class OrganizationResourceAssembler extends ResourceAssembler<OrganizationResource, Organization> {
    @Autowired
    private Mapper mapper;

    @Autowired
    private OrganizationUnitResourceAssembler organizationUnitResourceAssembler;

    public OrganizationResource toResource(Organization entity) {
        OrganizationResource organizationResource = null;//mapper.map(entity, OrganizationResource.class);
        if(entity != null) {
            organizationResource = new OrganizationResource();
            organizationResource.setUid(entity.getAggregateId().getId());
            organizationResource.setName(entity.getName());
            organizationResource.setDescription(entity.getDescription());
            organizationResource.setCode(entity.getCode());
            organizationResource.setImage(entity.getImage());
            organizationResource.setWebsite(entity.getWebsite());
            organizationResource.setOrganizationUnits(organizationUnitResourceAssembler.toResources(entity.getOrganizationUnits()));
        }
        return organizationResource;
    }

    @Override
    public Set<OrganizationResource> toResources(Collection<Organization> entities) {
        Set<OrganizationResource> organizations = new HashSet<OrganizationResource>();
        if(entities != null && entities.size() > 0) {
            Iterator<Organization> iterator = entities.iterator();
            if(iterator.hasNext()) {
                Organization organization = iterator.next();
                organizations.add(toResource(organization));
            }
        }
        return organizations;
    }
}