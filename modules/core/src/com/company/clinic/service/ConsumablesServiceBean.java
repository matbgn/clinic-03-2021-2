package com.company.clinic.service;

import com.company.clinic.entity.Consumable;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.TypedQuery;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.View;
import com.haulmont.cuba.core.sys.PersistenceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service(ConsumablesService.NAME)
public class ConsumablesServiceBean implements ConsumablesService {

/*
    @Inject
    private Persistence persistence;

*/
    @Inject
    private DataManager dataManager;

    @Override
    public List<Consumable> getUsedConsumables() {

        LoadContext<Consumable> loadContext = new LoadContext<>(Consumable.class)
                .setQuery(
                        new LoadContext.Query("select distinct c from clinic_Visit v join v.consumables c " +
                        "where @between(v.date, now-7, now+1, day)"))
                .setView(View.LOCAL);

        return dataManager.secure().loadList(loadContext);

    }


    /*
        EntityManager em = persistence.getEntityManager();

        TypedQuery<Consumable> query = em.createQuery(
                "select distinct c from clinic_Visit v join v.consumables c " +
                        "where @between(v.date, now-7, now+1, day)", Consumable.class);

        query.setViewName(View.LOCAL);

        return query.getResultList();
*/


/*
        return persistence.createTransaction().execute(em -> {

            TypedQuery<Consumable> query = em.createQuery(
                    "select distinct c from clinic_Visit v join v.consumables c " +
                            "where @between(v.date, now-7, now+1, day)", Consumable.class);

            query.setViewName(View.LOCAL);

            return query.getResultList();

        });
*/

/*
        try (Transaction tx = persistence.createTransaction()) {

            EntityManager em = persistence.getEntityManager();

            TypedQuery<Consumable> query = em.createQuery(
                    "select distinct c from clinic_Visit v join v.consumables c " +
                            "where @between(v.date, now-7, now+1, day)", Consumable.class);

            query.setViewName(View.LOCAL);

            List<Consumable> consumables = query.getResultList();

            tx.commit();

            return consumables;
        }
*/

}