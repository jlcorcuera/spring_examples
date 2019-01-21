/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.idc.web.test.springmvc.dto;


import java.util.Date;
import java.util.List;

/**
 *
 * @author j0s3
 */
public class SalesOrderDTO {

    private Integer id;
    private String numDoc;
    private Date date;
    private Double total;
    private Double taxes;
    private Integer customerId;    
    private String customer;
    private String salesman;
    private List<SalesOrderDetailDTO> details;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTaxes() {
        return taxes;
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    public List<SalesOrderDetailDTO> getDetails() {
        return details;
    }

    public void setDetails(List<SalesOrderDetailDTO> details) {
        this.details = details;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "SalesOrderDTO{" + "numDoc=" + numDoc + ", date=" + date + ", total=" + total + ", taxes=" + taxes + ", customerId=" + customerId + ", customer=" + customer + ", salesman=" + salesman + ", details=" + details + '}';
    }
    
}
