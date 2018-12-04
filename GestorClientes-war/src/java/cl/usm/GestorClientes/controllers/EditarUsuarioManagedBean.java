/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.GestorClientes.controllers;

import cl.usm.GestorClientes.dao.UsuarioFacadeLocal;
import cl.usm.GestorClientes.dto.Usuario;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alexsander
 */
@Named(value = "editarUsuarioManagedBean")
@SessionScoped
public class EditarUsuarioManagedBean implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioFacade;

            
    
    private Usuario usuarioEditado;
            
    /**
     * Creates a new instance of EditarUsuarioManagedBean
     */
    public EditarUsuarioManagedBean() {
    }
    



    public void actualizarUsuario(){
        //Los campos ya estan actualizados, en el objeto, Binding
        this.usuarioFacade.edit(usuarioEditado);

        try {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect("ver_usuario.xhtml");
        } catch (IOException ex) {
        }

        }
    
    public Usuario getUsuarioEditado() {
        return usuarioEditado;
    }

    public void setUsuarioEditado(Usuario usuarioEditado) {
        this.usuarioEditado = usuarioEditado;
    }
}
