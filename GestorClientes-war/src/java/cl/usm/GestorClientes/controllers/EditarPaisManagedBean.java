/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.GestorClientes.controllers;


import cl.usm.GestorClientes.dao.PaisFacadeLocal;
import cl.usm.GestorClientes.dto.Pais;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alexsander
 */
@Named(value = "editarPaisManagedBean")
@SessionScoped
public class EditarPaisManagedBean implements Serializable {

    @EJB
    private PaisFacadeLocal paisFacade;

    /**
     * Creates a new instance of EditarPaisManagedBean
     */


    
    private Pais paisEditado;
    /**
     * Creates a new instance of EditarPokemonManagedBean
     */
    public EditarPaisManagedBean() {
    }

    public void actualizarPais(){
        //Los campos ya estan actualizados, en el objeto, Binding
        this.paisFacade.edit(paisEditado);

        try {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect("ver_pais.xhtml");
        } catch (IOException ex) {
        }

        }
    
    public Pais getPaisEditado() {
        return paisEditado;
    }

    public void setPaisEditado(Pais paisEditado) {
        this.paisEditado = paisEditado;
    }
    
}
