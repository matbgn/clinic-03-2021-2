package com.company.clinic.service;

import com.company.clinic.entity.Veterinarian;
import com.company.clinic.entity.Visit;
import com.haulmont.cuba.security.entity.User;

import java.math.BigDecimal;

public interface VisitService {
    String NAME = "clinic_VisitService";

    BigDecimal calculateAmount(Visit visit);

    Veterinarian findVetByUser(User user);

}