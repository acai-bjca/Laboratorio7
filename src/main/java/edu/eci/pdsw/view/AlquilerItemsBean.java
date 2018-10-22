package edu.eci.pdsw.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;

@SuppressWarnings("deprecation")
@ManagedBean(name = "alquierItemsBean")
@SessionScoped
public class AlquilerItemsBean extends BasePageBean {	
	@Inject
	private ServiciosAlquiler serviciosAlquiler;
	
	public List<Cliente> getClientes() throws Exception{
        try {
            return serviciosAlquiler.consultarClientes();
        } catch (ExcepcionServiciosAlquiler ex) {            
            throw ex;
        }        
    }   
	
	public void registrarCliente(String  nombreR, String  documentoR, String  direccionR, String  telefonoR, String  emailR) throws Exception{
        try {
        	Cliente clienteR = new Cliente(nombreR, Long.parseLong(documentoR), direccionR, telefonoR, emailR);
            serviciosAlquiler.registrarCliente(clienteR);
        } catch (ExcepcionServiciosAlquiler ex) {            
            throw ex;
        }        
    } 
}
