/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.Currency;
import com.model.Rate;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author khaled
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class ConvertionFacade implements ConverterFacadeLocal {

    @PersistenceContext(unitName = "CurrencyConverterPU")
    EntityManager em;

    @Override
    public Map<String, Integer> listAllCurrencies() {
        List<Currency> currencies = em.createNamedQuery("Currency.findAll", Currency.class).getResultList();
        if (currencies != null && !currencies.isEmpty()) {
            Map<String, Integer> mCurrencies = new HashMap<>();
            currencies.stream().forEach((currency) -> {
                mCurrencies.put(currency.getName() + " [" + currency.getAcronym() + "]", currency.getId());
            });
            return mCurrencies;
        }
        return null;
    }

    @Override
    public String getConvertionResult(Integer fromCurrencyId, Integer toCurrencyId, Double ammount) {
        List<Rate> rates = em.createNamedQuery("Rate.findByForign", Rate.class)
                .setParameter("fromId", fromCurrencyId).setParameter("toId", toCurrencyId).getResultList();
        if(rates != null){
            Currency c = em.find(Currency.class, toCurrencyId);
            Double result = ammount * (double) rates.get(0).getRate();
            return new DecimalFormat("#.###").format(result) + " " + c.getAcronym();
        }
        return "No Result!";
    }

}
