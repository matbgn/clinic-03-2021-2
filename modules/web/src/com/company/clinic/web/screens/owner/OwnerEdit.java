package com.company.clinic.web.screens.owner;

import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.ValidationException;
import com.haulmont.cuba.gui.screen.*;
import com.company.clinic.entity.Owner;
import com.haulmont.reports.gui.actions.EditorPrintFormAction;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

@UiController("clinic_Owner.edit")
@UiDescriptor("owner-edit.xml")
@EditedEntityContainer("ownerDc")
@LoadDataBeforeShow
public class OwnerEdit extends StandardEditor<Owner> {

    @Inject
    private MessageBundle messageBundle;
    @Inject
    private Button reportBtn;

    @Subscribe
    public void onInit(InitEvent event) {
        reportBtn.setAction(new EditorPrintFormAction(this, null));
    }

    @Install(to = "nameField", subject = "validator")
    private void nameFieldValidator(String string) {
        if (!StringUtils.isAlphaSpace(string)) {
            throw new ValidationException(messageBundle.getMessage("name.invalid"));
        }
    }
}