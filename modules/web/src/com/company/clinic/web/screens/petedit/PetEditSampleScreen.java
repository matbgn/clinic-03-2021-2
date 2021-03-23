package com.company.clinic.web.screens.petedit;

import com.company.clinic.entity.Owner;
import com.company.clinic.entity.Pet;
import com.company.clinic.entity.PetType;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;

@UiController("clinic_PetEditSampleScreen")
@UiDescriptor("pet-edit-sample-screen.xml")
public class PetEditSampleScreen extends Screen {

    @Inject
    private DataManager dataManager;
    private Owner owner;
    private PetType petType;
    @Inject
    private InstanceContainer<Pet> petDc;
    @Inject
    private DataContext dataContext;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        owner = dataManager.load(Owner.class).list().get(0);
        petType = dataManager.load(PetType.class).list().get(0);
    }


    @Subscribe("createPet")
    public void onCreatePet(Action.ActionPerformedEvent event) {
        createPetWithDatamanager();
    }

    private void createPetWithDatamanager() {
        Pet pet = dataManager.create(Pet.class);
        pet.setPetType(petType);
        pet.setOwner(owner);

        pet = dataContext.merge(pet);

        petDc.setItem(pet);

    }

    @Subscribe("save")
    public void onSaveClick(Button.ClickEvent event) {
        dataContext.commit();
    }
}