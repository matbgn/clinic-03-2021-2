package com.company.clinic.web.screens;

import com.company.clinic.entity.Visit;
import com.company.clinic.web.screens.visit.VisitEdit;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Calendar;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
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

    @Subscribe("refresh")
    public void onRefresh(Action.ActionPerformedEvent event) {
        visitsDl.load();
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
}