/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.idc.rest.test.swagger2.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author j0s3
 */
@ApiModel(description = "Class representing a sales order detail")
public class SalesOrderDetailDTO{

    @ApiModelProperty(notes = "Id")
    private Integer id;
    @ApiModelProperty(notes = "Product name")
    private String product;
    @ApiModelProperty(notes = "Quantity")
    private Double quantity;
    @ApiModelProperty(notes = "Subtotal")
    private Double subtotal;

    public SalesOrderDetailDTO() {
    }

    public SalesOrderDetailDTO(Integer id, String product, Double quantity, Double subtotal) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

}
