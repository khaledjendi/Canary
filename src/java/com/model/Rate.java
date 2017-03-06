/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author khaled
 */
@Entity
@Table(name = "rate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rate.findAll", query = "SELECT r FROM Rate r"),
    @NamedQuery(name = "Rate.findById", query = "SELECT r FROM Rate r WHERE r.id = :id"),
    @NamedQuery(name = "Rate.findByRate", query = "SELECT r FROM Rate r WHERE r.rate = :rate"),
    @NamedQuery(name = "Rate.findByForign", query = "SELECT r FROM Rate r WHERE r.fromCurrencyId.id = :fromId and r.toCurrencyId.id = :toId")})
public class Rate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rate")
    private float rate;
    @JoinColumn(name = "from_currency_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Currency fromCurrencyId;
    @JoinColumn(name = "to_currency_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Currency toCurrencyId;

    public Rate() {
    }

    public Rate(Integer id) {
        this.id = id;
    }

    public Rate(Integer id, float rate) {
        this.id = id;
        this.rate = rate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public Currency getFromCurrencyId() {
        return fromCurrencyId;
    }

    public void setFromCurrencyId(Currency fromCurrencyId) {
        this.fromCurrencyId = fromCurrencyId;
    }

    public Currency getToCurrencyId() {
        return toCurrencyId;
    }

    public void setToCurrencyId(Currency toCurrencyId) {
        this.toCurrencyId = toCurrencyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rate)) {
            return false;
        }
        Rate other = (Rate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Rate[ id=" + id + " ]";
    }
    
}
