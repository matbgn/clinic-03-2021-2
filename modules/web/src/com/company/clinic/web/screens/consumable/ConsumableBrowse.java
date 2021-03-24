package com.company.clinic.web.screens.consumable;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.screen.*;
import com.company.clinic.entity.Consumable;
import com.haulmont.reports.app.service.ReportService;
import com.haulmont.reports.entity.Report;
import com.haulmont.reports.gui.ReportGuiManager;

import javax.inject.Inject;

@UiController("clinic_Consumable.browse")
@UiDescriptor("consumable-browse.xml")
@LookupComponent("consumablesTable")
@LoadDataBeforeShow
public class ConsumableBrowse extends StandardLookup<Consumable> {

    @Inject
    private ReportGuiManager reportGuiManager;
    @Inject
    private DataManager dataManager;

    @Subscribe("runReport")
    public void onRunReportClick(Button.ClickEvent event) {

        Report report = dataManager.load(Report.class)
                .view(ReportService.MAIN_VIEW_NAME)
                .query("select r from report$Report r where r.code = :code")
                .parameter("code", "pricelist")
                .one();

        reportGuiManager.runReport(report, this);
    }
}