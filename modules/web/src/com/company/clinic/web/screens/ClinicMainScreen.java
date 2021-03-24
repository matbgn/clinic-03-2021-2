package com.company.clinic.web.screens;

import com.company.clinic.entity.Pet;
import com.company.clinic.entity.Visit;
import com.company.clinic.service.VisitService;
import com.company.clinic.web.screens.petedit.PetEditSampleScreen;
import com.company.clinic.web.screens.visit.VisitEdit;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Calendar;
import com.haulmont.cuba.gui.components.DateField;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.global.UserSession;
import com.haulmont.cuba.web.app.main.MainScreen;

import javax.inject.Inject;
import java.time.LocalDateTime;


@UiController("clinicMainScreen")
@UiDescriptor("clinic-main-screen.xml")
@LoadDataBeforeShow
public class ClinicMainScreen extends MainScreen {

    @Inject
    private CollectionLoader<Visit> visitsDl;
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private DataManager dataManager;
    @Inject
    private CollectionLoader<Pet> petsDl;
    @Inject
    private LookupField<Pet> petSelector;
    @Inject
    private DateField<LocalDateTime> dateSelector;
    @Inject
    private VisitService visitService;
    @Inject
    private UserSession userSession;
    @Inject
    private CollectionContainer<Visit> visitsDc;

    @Subscribe("refresh")
    public void onRefresh(Action.ActionPerformedEvent event) {
        petsDl.load();
        visitsDl.load();
        petSelector.setValue(null);
        dateSelector.setValue(null);
    }

    @Subscribe("visitsCalendar")
    public void onVisitsCalendarCalendarEventClick(Calendar.CalendarEventClickEvent<LocalDateTime> event) {
        Visit visit = (Visit) event.getEntity();

        if (visit == null) return;

        VisitEdit visitEdit = screenBuilders.editor(Visit.class, this)
                .withScreenClass(VisitEdit.class)
                .editEntity(visit)
                .withOpenMode(OpenMode.DIALOG)
                .build();

        visitEdit.addAfterCloseListener(closeEvent -> {
            if (closeEvent.getCloseAction() == WINDOW_COMMIT_AND_CLOSE_ACTION) {
                visitsDl.load();
            }
        });

        visitEdit.show();

    }

    @Subscribe("schedule")
    public void onSchedule(Action.ActionPerformedEvent event) {
        Visit visit = dataManager.create(Visit.class);

        visit.setPet(petSelector.getValue());
        visit.setDate(dateSelector.getValue());
        visit.setHoursSpent(1);
        visit.setVeterinarian(visitService.findVetByUser(userSession.getUser()));

        dataManager.commit(visit);
        onRefresh(null);
    }

    @Install(to = "schedule", subject = "enabledRule")
    private boolean scheduleEnabledRule() {
        return petSelector.getValue() != null && dateSelector.getValue() != null;
    }
}