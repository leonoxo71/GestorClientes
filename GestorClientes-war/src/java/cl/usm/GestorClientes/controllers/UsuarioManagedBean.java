/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.GestorClientes.controllers;

import cl.usm.GestorClientes.dao.UsuarioFacadeLocal;
import cl.usm.GestorClientes.dto.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Alexsander
 */
@Named(value = "usuarioManagedBean")
@ViewScoped
public class UsuarioManagedBean implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    private List<Usuario> usuarios;
    private String nombreTxt;
    private String usuarioTxt;
    private String rut_usuarioTxt;
    private String passwordTxt;
    private int sexoTxt;
    
    @Inject
    private EditarUsuarioManagedBean editarUsuarioManagedBean;

    private Usuario usuarioEditado;
    
    public UsuarioManagedBean() {
    }
    
    @PostConstruct
    public void init(){
        this.usuarios = usuarioFacade.findAll();
    }
    
    public void eliminarUsuario(Usuario usuario){
        this.usuarioFacade.remove(usuario);
        this.usuarios = usuarioFacade.findAll();
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage("Pais eliminado"));
    }
    
    public void editarUsuario(Usuario usuario) {
        this.usuarioEditado = usuario;
        this.editarUsuarioManagedBean.setUsuarioEditado(usuario);
        try {
            //redirigir
            FacesContext.getCurrentInstance()
                    .getExternalContext().redirect("editar_usuario.xhtml");
        } catch (Exception ex) {

        }
    }
    
    public void agregarUsuario(ActionEvent ev){
        Usuario usuario = new Usuario();
        usuario.setNombre(nombreTxt);
        usuario.setPassword(passwordTxt);
        usuario.setRutUsuario(rut_usuarioTxt);
        usuario.setUsuario(usuarioTxt);
        usuario.setSexo(sexoTxt);

        this.usuarioFacade.create(usuario);
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage("Usuario Ingresado"));

    }
    

    public void autenticate(){
        Usuario us;
        Usuario usuario = new Usuario();
        usuario.setPassword(passwordTxt);
        usuario.setUsuario(usuarioTxt);
        try{
           //us =  usuarioFacade.iniciarSesion(usuario);
           //if(us != null){
            //    FacesContext.getCurrentInstance()
                 //   .getExternalContext().redirect("index.xhtml");
           //}else{
           FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage("El usuario ingresado no se encuentra registrado en el sistema"));
   //        }
            
        }
        catch(Exception e){
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage("El usuario ingresado aca"));
        }

    }

    
    public String getSexo(int sexo){
        String u = "";
        switch (sexo) {
            case 0:
                u = "Hombre";
                break;
            case 1:
                u = "Mujer";
                break;
            case 2:
                u = "Helicoptero de guerra";
                break;
            default:
                break;
        }
        return u;
    }
    
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getNombreTxt() {
        return nombreTxt;
    }

    public void setNombreTxt(String nombreTxt) {
        this.nombreTxt = nombreTxt;
    }

    public String getUsuarioTxt() {
        return usuarioTxt;
    }

    public void setUsuarioTxt(String usuarioTxt) {
        this.usuarioTxt = usuarioTxt;
    }

    public String getRut_usuarioTxt() {
        return rut_usuarioTxt;
    }

    public void setRut_usuarioTxt(String rut_usuarioTxt) {
        this.rut_usuarioTxt = rut_usuarioTxt;
    }

    public String getPasswordTxt() {
        return passwordTxt;
    }

    public void setPasswordTxt(String passwordTxt) {
        this.passwordTxt = passwordTxt;
    }

    public int getSexoTxt() {
        return sexoTxt;
    }

    public void setSexoTxt(int sexoTxt) {
        this.sexoTxt = sexoTxt;
    }   
    
    
}
