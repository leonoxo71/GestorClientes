/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.GestorClientes.controllers;

import cl.usm.GestorClientes.dao.CiudadFacadeLocal;
import cl.usm.GestorClientes.dao.ClienteFacadeLocal;
import cl.usm.GestorClientes.dao.PaisFacadeLocal;
import cl.usm.GestorClientes.dto.Ciudad;
import cl.usm.GestorClientes.dto.Cliente;
import cl.usm.GestorClientes.dto.Pais;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author mhoar
 */
@Named(value = "editarClienteManagedBean")
@SessionScoped
public class EditarClienteManagedBean implements Serializable {

    /**
     * Creates a new instance of EditarClienteJSFManagedBean
     */
    @EJB
    private CiudadFacadeLocal ciudadFacade;
    
    @EJB
    private ClienteFacadeLocal clienteFacade;
    
    @EJB
    private PaisFacadeLocal paisFacade;
    private List <Ciudad> ciudades;
    private List <Pais> paises;
    private Cliente cliente;

    public List<Ciudad> getCiudades() {
        return ciudades;
    }
    public void changedPaisEdit(){
         ciudades = (List<Ciudad>)(paisFacade.find(cliente.getCodCiudad().getCodPais().getCodPais()).getCiudadList());


    }
    public void actualizar(){
        Ciudad c = new Ciudad ();
        c.setCodCiudad(cliente.getCodCiudad().getCodCiudad());
        cliente.setCodCiudad(c);
        clienteFacade.edit(cliente);
        try {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect("gestor_cliente.xhtml");
        } catch (IOException ex) {
        }
    }
    public void setCiudades(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }

    public List<Pais> getPaises() {
        return paises;
    }

    public void setPaises(List<Pais> paises) {
        this.paises = paises;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public EditarClienteManagedBean() {
    }
    @PostConstruct
    public void init() {
  
        this.setPaises(paisFacade.findAll());
       
        ciudades = (List<Ciudad>)(paises.get(0).getCiudadList());
        
    }
}
