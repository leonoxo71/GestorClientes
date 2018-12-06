/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.GestorClientes.controllers;


import cl.usm.GestorClientes.dto.Ciudad;
import cl.usm.GestorClientes.dto.Pais;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import cl.usm.GestorClientes.dao.CiudadFacadeLocal;
import javax.faces.context.FacesContext;

/**
 *
 * @author mhoar
 */
@Named(value = "editarCiudadManagedBean")
@SessionScoped
public class EditarCiudadManagedBean implements Serializable {

    /**
     * Creates a new instance of EditarCiudadManagedBean
     * 
     */
    @EJB
    CiudadFacadeLocal ciudadFacade;
    
    Ciudad ciudadEditada;
    public EditarCiudadManagedBean() {
    }
     public void actualizarCiudad(){
        //Los campos ya estan actualizados, en el objeto, Binding
        this.ciudadFacade.edit(ciudadEditada);

        try {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect("gestor_ciudad.xhtml");
        } catch (IOException ex) {
        }

        }

    public Ciudad getCiudadEditada() {
        return ciudadEditada;
    }

    public void setCiudadEditada(Ciudad ciudadEditada) {
        this.ciudadEditada = ciudadEditada;
    }
    

}
