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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author labinf10
 */
@Named(value = "usuarioLogeadoManagedBean")
@SessionScoped
public class UsuarioLogeadoManagedBean implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioFacade;

   private String usuarioTxt;
   private String passwordTxt;
    
    private Usuario usuarioLogeado;
    
    public UsuarioLogeadoManagedBean() {
    }
    
    
    public void logOut(){
        usuarioLogeado = null;
        try {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect("login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(UsuarioLogeadoManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void autenticate(ActionEvent ev){

        Usuario usuario = new Usuario();
        usuario.setPassword(passwordTxt);
        usuario.setUsuario(usuarioTxt);
        try{
           usuarioLogeado =  usuarioFacade.iniciarSesion(usuario);
           if(usuarioLogeado != null){
               FacesContext.getCurrentInstance()
                  .getExternalContext().redirect("index.xhtml");
           }else{
           FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage("El usuario ingresado no se encuentra registrado en el sistema"));
           }
            
        }
        catch(Exception e){
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage("El usuario no esta ingresado aca"));
        }

    }
    
    public String getUsuarioTxt() {
        return usuarioTxt;
    }

    public void setUsuarioTxt(String usuarioTxt) {
        this.usuarioTxt = usuarioTxt;
    }

    public String getPasswordTxt() {
        return passwordTxt;
    }

    public void setPasswordTxt(String passwordTxt) {
        this.passwordTxt = passwordTxt;
    }
    
}
