/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author khaled
 */
@Local
public interface ConverterFacadeLocal {
    
    public Map<String, Integer> listAllCurrencies();
    public String getConvertionResult(Integer fromCurrencyId, Integer toCurrencyId, Double ammount);
    
}
