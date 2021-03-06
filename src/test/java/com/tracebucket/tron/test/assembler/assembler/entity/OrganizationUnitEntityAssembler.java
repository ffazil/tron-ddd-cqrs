package com.tracebucket.tron.test.assembler.assembler.entity;

import com.tracebucket.tron.assembler.AssemblerResolver;
import com.tracebucket.tron.assembler.EntityAssembler;
import com.tracebucket.tron.ddd.domain.EntityId;
import com.tracebucket.tron.test.assembler.sample.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by sadath on 31-Mar-15.
 */
//@Component
public class OrganizationUnitEntityAssembler extends EntityAssembler<OrganizationUnit, OrganizationUnitResource> {

    @Autowired
    private AssemblerResolver assemblerResolver;

    public OrganizationUnit toEntity(OrganizationUnitResource resource, Class<OrganizationUnit> entityClass) {
        OrganizationUnit organizationUnit = null;
        if(resource != null) {
            organizationUnit = new OrganizationUnit();
            if (resource.getUid() != null) {
                organizationUnit.setEntityId(new EntityId(resource.getUid()));
            }
            organizationUnit.setName(resource.getName());
            organizationUnit.setDescription(resource.getDescription());
            organizationUnit.setOrganizationFunctions(resource.getOrganizationFunctions());
            organizationUnit.setBusinessLines(assemblerResolver.resolveEntityAssembler(BusinessLine.class, BusinessLineResource.class)
                    .toEntities(resource.getBusinessLines(), BusinessLine.class));
            organizationUnit.setDepartments(assemblerResolver.resolveEntityAssembler(Department.class, DepartmentResource.class)
                    .toEntities(resource.getDepartments(), Department.class));
            organizationUnit.setChildren(toEntities(resource.getChildren(), OrganizationUnit.class));
            organizationUnit.setParent(toEntity(resource.getParent(), OrganizationUnit.class));
        }
        return organizationUnit;
    }

    @Override
    public Set<OrganizationUnit> toEntities(Collection<OrganizationUnitResource> resources, Class<OrganizationUnit> entityClass) {
        Set<OrganizationUnit> organizationUnits = new HashSet<OrganizationUnit>();
        if(resources != null) {
            Iterator<OrganizationUnitResource> iterator = resources.iterator();
            if(iterator.hasNext()) {
                OrganizationUnitResource organizationUnitResource = iterator.next();
                organizationUnits.add(toEntity(organizationUnitResource, OrganizationUnit.class));
            }
        }
        return organizationUnits;
    }
}