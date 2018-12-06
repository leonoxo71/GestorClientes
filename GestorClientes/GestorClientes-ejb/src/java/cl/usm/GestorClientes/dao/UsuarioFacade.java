/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.GestorClientes.dao;

import cl.usm.GestorClientes.dto.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alexsander
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "GestorClientes-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    @Override
    public Usuario iniciarSesion(Usuario us){
        Usuario usuario = null;
        String consulta;
        try{
            consulta = "SELECT u FROM Usuario u WHERE u.usuario = :usuario AND u.password = :password";
            Query query = em.createQuery(consulta);
            query.setParameter("usuario", us.getUsuario());
            query.setParameter("password", us.getPassword());
            
            List<Usuario> lista = query.getResultList();
            if(!lista.isEmpty()){
                usuario = lista.get(0);
            }
                    
        }catch(Exception e){
            return usuario;
      
        }
        return usuario;
    }
    
}
