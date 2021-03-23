package com.company.clinic.web.screens.owner;

import com.haulmont.cuba.gui.components.ValidationException;
import com.haulmont.cuba.gui.screen.*;
import com.company.clinic.entity.Owner;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

@UiController("clinic_Owner.edit")
@UiDescriptor("owner-edit.xml")
@EditedEntityContainer("ownerDc")
@LoadDataBeforeShow
public class OwnerEdit extends StandardEditor<Owner> {

    @Inject
    private MessageBundle messageBundle;

    @Install(to = "nameField", subject = "validator")
    private void nameFieldValidator(String string) {
        if (!StringUtils.isAlphaSpace(string)) {
            throw new ValidationException(messageBundle.getMessage("name.invalid"));
        }
    }
}