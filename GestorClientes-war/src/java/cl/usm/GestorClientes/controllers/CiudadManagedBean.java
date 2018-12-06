/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.GestorClientes.controllers;

import cl.usm.GestorClientes.dao.CiudadFacadeLocal;
import cl.usm.GestorClientes.dao.PaisFacadeLocal;
import cl.usm.GestorClientes.dto.Ciudad;
import cl.usm.GestorClientes.dto.Pais;
import java.io.Serializable;
import static java.lang.System.out;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author mhoar
 */
@Named(value = "ciudadManagedBean")
@ViewScoped
public class CiudadManagedBean implements Serializable{

    /**
     * Creates a new instance of CiudadManagedBean
     */
    @EJB
    private PaisFacadeLocal paisFacade;
    
    @EJB
    private CiudadFacadeLocal ciudadFacade;
    
    private int codigo;
    private int pais ;
    private String nombre;
    private List<Ciudad> ciudades;
    private List<Pais> paises;
    private Ciudad ciudad;
    @Inject
    private EditarCiudadManagedBean editarCiudadManagedBean;
    public CiudadManagedBean() {
    }
    @PostConstruct
    public void init(){
        setPaises(paisFacade.findAll());
        setCiudades(ciudadFacade.findAll());
    }
    public void agregar(ActionEvent ev){
        Ciudad c  = new Ciudad();
        Pais p  = new Pais();
        
        p.setCodPais(pais);
        c.setCodPais(p);
        c.setNombre(nombre);
        
        ciudadFacade.create(c);
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage("Ciudad Ingresada"));
        
    }
    public void eliminar(Ciudad c){
        ciudadFacade.remove(c);
        setCiudades(ciudadFacade.findAll());
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage("Ciudad eliminada"));
    }
    public void editar(Ciudad c) {
        ciudad = c;
        this.editarCiudadManagedBean.setCiudadEditada(c);
        try {
            //redirigir
            FacesContext.getCurrentInstance()
                    .getExternalContext().redirect("editar_ciudad.xhtml");
        } catch (Exception ex) {
            ciudad.getNombre();
        }
    }
 
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getPais() {
        return pais;
    }

    public void setPais(int pais) {
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Ciudad> getCiudades() {
        return ciudades;
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
    
    
}
