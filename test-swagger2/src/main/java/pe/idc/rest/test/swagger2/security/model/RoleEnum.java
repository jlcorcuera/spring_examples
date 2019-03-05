/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.idc.rest.test.swagger2.security.model;

/**
 *
 * @author j0s3
 */
public enum RoleEnum {
    
    ROLE_CUSTOMER(1, "ROLE_CUSTOMER"),
    ROLE_SALESMAN(1, "ROLE_SALESMAN");
    
    int id;
    String name;
    
    RoleEnum(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public static RoleEnum fromId(int id){
        if (id > 0 ){
            for(RoleEnum role:RoleEnum.values()){
                if (role.getId() == id){
                    return role;
                }
            }
        }
        return null;
    }
    
}
