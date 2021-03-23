package com.company.clinic.core.role;

import com.company.clinic.entity.*;
import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.EntityAttributeAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.app.role.annotation.ScreenAccess;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.role.EntityAttributePermissionsContainer;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.ScreenPermissionsContainer;

@Role(name = VeterinarianRole.NAME, description = "Veterinarian")
public class VeterinarianRole extends AnnotatedRoleDefinition {
    public final static String NAME = "veterinarian-role";

    @ScreenAccess(screenIds = {"clinic_Consumable.browse"
            , "application-clinic"
            , "clinic_Visit.browse"
            , "clinic_UsedConsumables"
            , "clinic_Visit.edit"})
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }

    @EntityAccess(entityClass = Visit.class, operations = {EntityOp.READ, EntityOp.UPDATE})
    @EntityAccess(entityClass = Veterinarian.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = PetType.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Pet.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Owner.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Consumable.class, operations = EntityOp.READ)
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = Consumable.class, view = {"title", "price"})
    @EntityAttributeAccess(entityClass = Visit.class, modify = {"hoursSpent", "consumables"}, view = {"pet", "veterinarian", "date", "description"})
    @EntityAttributeAccess(entityClass = Veterinarian.class, view = "*")
    @EntityAttributeAccess(entityClass = PetType.class, view = "*")
    @EntityAttributeAccess(entityClass = Pet.class, view = "*")
    @EntityAttributeAccess(entityClass = Owner.class, view = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }
}
