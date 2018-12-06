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
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author mhoar
 */
@Named(value = "clienteManagedBean")
@ViewScoped
public class ClienteManagedBean implements Serializable {

    /**
     * Creates a new instance of ClienteManagedBean
     * 
     */
    @EJB
    private CiudadFacadeLocal ciudadFacade;
    
    @EJB
    private ClienteFacadeLocal clienteFacade;
    
    @EJB
    private PaisFacadeLocal paisFacade;
    
    private String rut, apMaterno, apPaterno, telefono, nombre, direccion, correo;
    private int pais, ciudad;
    private List <Ciudad> ciudades;
    private List <Pais> paises;
    private List <Cliente> clientes;
    private Date nacimiento;
    @Inject
    private EditarClienteManagedBean editarClienteManagedBean;
    private Cliente clienteEditado;
    
    public ClienteManagedBean() {
    }
    @PostConstruct
    public void init() {
        clientes = (List<Cliente>)clienteFacade.findAll();
        this.setPaises(paisFacade.findAll());
       
        ciudades = (List<Ciudad>)(paises.get(0).getCiudadList());
        
    }
    public void changedPais(){
         ciudades = (List<Ciudad>)(paisFacade.find(pais).getCiudadList());


    }
    public void agregar(){
        Cliente cliente = new Cliente();
        Ciudad  c = new Ciudad();
        c.setCodCiudad(ciudad);       
        cliente.setCodCiudad(c);
        cliente.setApMaterno(apMaterno);
        cliente.setApPaterno(apPaterno);
        cliente.setNombre(nombre);
        cliente.setRut(rut);
        cliente.setTelefono(telefono);
        cliente.setDireccion(direccion);
        cliente.setMail(correo);
        cliente.setFechaNacimiento(nacimiento);
        
        
        
        clienteFacade.create(cliente);
        
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage("Cliente Ingresado"));
             
        
    }
      public void eliminar(Cliente c){
        clienteFacade.remove(c);
        setClientes(clienteFacade.findAll());
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage("Cliente eliminado"));
    }
    public void editar(Cliente c) {
        this.editarClienteManagedBean.setCliente(c);
        try {
            //redirigir
            FacesContext.getCurrentInstance()
                    .getExternalContext().redirect("editar_cliente.xhtml");
        } catch (Exception ex) {
        }
    }  
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getPais() {
        return pais;
    }

    public void setPais(int pais) {
        this.pais = pais;
    }

    public int getCiudad() {
        return ciudad;
    }

    public void setCiudad(int ciudad) {
        this.ciudad = ciudad;
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

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }
    
}
