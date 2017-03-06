/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import com.controller.ConverterFacadeLocal;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author khaled
 */
@ManagedBean(name = "currencyConverter")
@SessionScoped
public class ConverterBean implements Serializable {

    private Integer fromCurrency;
    private Integer toCurrency;
    private Double amount;
    private String convertionValue;
    private Map<String, Integer> currencies;
    public @EJB
    ConverterFacadeLocal converterFacade;

    public Integer getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(Integer fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public Integer getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(Integer toCurrency) {
        this.toCurrency = toCurrency;
    }

    public Map<String, Integer> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Map<String, Integer> currencies) {
        this.currencies = currencies;
    }

    public String getConvertionValue() {
        return convertionValue;
    }

    public void setConvertionValue(String convertionValue) {
        this.convertionValue = convertionValue;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public ConverterBean() {

    }

    @PostConstruct
    public void inti() {
        currencies = converterFacade.listAllCurrencies();
        convertionValue = "No Result";
    }

    public String action() {
        if (fromCurrency != null && toCurrency != null && amount != null) {
            if (fromCurrency.equals(toCurrency)) {
                String messageToDisplay = "From currency must be different from To currency!";
                return returnAction(messageToDisplay);
            }
            convertionValue = converterFacade.getConvertionResult(fromCurrency, toCurrency, amount) + " ";
            return "index.xhtml?faces-redirect=true";
        }
        String messageToDisplay = "Incorrect Convertion! Please select from - to currencies!";
        return returnAction(messageToDisplay);
    }

    private String returnAction(String messageToDisplay) {
        convertionValue = "No Redult!";
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, messageToDisplay, "");
        FacesContext.getCurrentInstance().addMessage("growl", message);
        return "index.xhtml?faces-redirect=false";
    }

}
