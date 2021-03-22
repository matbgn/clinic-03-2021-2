package com.company.clinic.web.screens.usedconsumables;

import com.company.clinic.entity.Consumable;
import com.company.clinic.service.ConsumablesService;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;

@UiController("clinic_UsedConsumables")
@UiDescriptor("used-consumables.xml")
public class UsedConsumables extends Screen {

    @Inject
    private CollectionContainer<Consumable> consumablesDc;

    @Inject
    private ConsumablesService consumablesService;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        consumablesDc.setItems(consumablesService.getUsedConsumables());
    }


}