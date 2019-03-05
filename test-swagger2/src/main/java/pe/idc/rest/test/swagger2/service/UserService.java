/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.idc.rest.test.swagger2.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.idc.rest.test.swagger2.dto.UserDTO;
import pe.idc.rest.test.swagger2.model.Cliente;
import pe.idc.rest.test.swagger2.model.Vendedor;
import pe.idc.rest.test.swagger2.security.JwtUtil;
import pe.idc.rest.test.swagger2.security.model.JwtUserDTO;
import pe.idc.rest.test.swagger2.security.model.RoleEnum;

/**
 *
 * @author j0s3
 */
@Service
@Transactional(readOnly = true)
public class UserService {

    /* 
        NOTE: since we run out of time, I am going to implement all the persistence stuff here in this class.
        Again, persistence code must be placed in the persistence layer
     */
    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private JwtUtil jwtUtil;

    public JwtUserDTO login(UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (username == null || password == null
                || username.isEmpty() || password.isEmpty()){
            return null;
        }
        String jpql = "select c from Cliente c where c.correo = :username";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("username", username);
        List<Cliente> clientes = query.getResultList();
        if (clientes != null && !clientes.isEmpty()) {
            Cliente cliente = clientes.get(0);
            if (cliente.getPassword().equalsIgnoreCase(password)) {
                return generateJwtUserDTO(cliente.getIdCliente(), username, RoleEnum.ROLE_CUSTOMER);
            }
        } else {
            jpql = "select v from Vendedor v where v.correo = :username";
            query = entityManager.createQuery(jpql);
            query.setParameter("username", username);
            List<Vendedor> vendedores = query.getResultList();
            if (vendedores != null && !vendedores.isEmpty()) {
                Vendedor vendedor = vendedores.get(0);
                if (vendedor.getPassword().equalsIgnoreCase(password)) {
                    return generateJwtUserDTO(vendedor.getIdVendedor(), username, RoleEnum.ROLE_SALESMAN);
                }
            }
        }
        return null;
    }
    
    public JwtUserDTO generateJwtUserDTO(Integer id, String username, RoleEnum role){
        JwtUserDTO jwtUserDTO = new JwtUserDTO();
        jwtUserDTO.setId(id);
        jwtUserDTO.setUsername(username);
        jwtUserDTO.setRole(role.getName());
        String token = jwtUtil.generateToken(jwtUserDTO);
        jwtUserDTO.setToken(token);
        return jwtUserDTO;
    }

}
