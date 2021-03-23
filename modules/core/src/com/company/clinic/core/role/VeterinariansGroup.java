package com.company.clinic.core.role;

import com.company.clinic.entity.Visit;
import com.haulmont.cuba.security.app.group.AnnotatedAccessGroupDefinition;
import com.haulmont.cuba.security.app.group.annotation.AccessGroup;
import com.haulmont.cuba.security.app.group.annotation.JpqlConstraint;
import com.haulmont.cuba.security.group.ConstraintsContainer;

@AccessGroup(name = "Veterinarians")
public class VeterinariansGroup extends AnnotatedAccessGroupDefinition {

    @Override
    @JpqlConstraint(target = Visit.class, where = "{E}.veterinarian.user.id = :session$userId")
    public ConstraintsContainer accessConstraints() {
        return super.accessConstraints();
    }
}
