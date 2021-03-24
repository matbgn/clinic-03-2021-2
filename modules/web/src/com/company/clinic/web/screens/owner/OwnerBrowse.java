package com.company.clinic.web.screens.owner;

import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.screen.*;
import com.company.clinic.entity.Owner;
import com.haulmont.reports.gui.actions.RunReportAction;

import javax.inject.Inject;

@UiController("clinic_Owner.browse")
@UiDescriptor("owner-browse.xml")
@LookupComponent("ownersTable")
@LoadDataBeforeShow
public class OwnerBrowse extends StandardLookup<Owner> {

    @Inject
    private GroupTable<Owner> ownersTable;
    @Inject
    private Notifications notifications;
    @Inject
    private MessageBundle messageBundle;
    @Inject
    private Button runReport;

    @Subscribe
    public void onInit(InitEvent event) {
        runReport.setAction(new RunReportAction());
    }

    @Subscribe("ownersTable.greet")
    public void onOwnersTableGreet(Action.ActionPerformedEvent event) {
        Owner owner = ownersTable.getSingleSelected();
        notifications.create().withCaption(messageBundle.formatMessage("hello.string", owner.getName())).show();
    }

    @Install(to = "ownersTable.greet", subject = "enabledRule")
    private boolean ownersTableGreetEnabledRule() {
        return ownersTable.getSingleSelected() != null;
    }
}