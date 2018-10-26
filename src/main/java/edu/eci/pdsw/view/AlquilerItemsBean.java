package edu.eci.pdsw.view;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.event.UnselectEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;

@SuppressWarnings("deprecation")
@ManagedBean(name = "alquierItemsBean")
@SessionScoped
public class AlquilerItemsBean extends BasePageBean {	
	@Inject
	private ServiciosAlquiler serviciosAlquiler;
	
	private Cliente selectedCliente; 
	private long documento;
	
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
	
	public Cliente seleccionarCliente(){    
		return selectedCliente;               
    } 
	
	public void setSelectedCliente(Cliente selectedCliente) {
        this.selectedCliente = selectedCliente;
    }
	
	public List<Item> consultarItemsSinDevolver() throws Exception{
		/**
		 * aqui puedo usar directamente el metodo de la calse Cliente (getRentados) ? o tengo que hacer el proceso?
		 * Cuando consulto en servicioAlquier implementacion uso el metodo de la clase Cliente getRentados, y no hago una consulta en la base de datos, está mal ?
		 */		       	
        try {
			return serviciosAlquiler.consultarItemsClienteSinDevoler(this.documento);
        }catch (ExcepcionServiciosAlquiler e) {
			//System.out.println(e.getMessage());
			throw e;
		}       		
    }
	
	public long consultarMultaAlquiler(int idItem) throws Exception{   	
        try {
        	Calendar fecha = Calendar.getInstance();        	
        	Date fechaActual = (Date)fecha.getTime(); 
			return serviciosAlquiler.consultarMultaAlquiler(idItem, fechaActual);
		} catch (ExcepcionServiciosAlquiler e) {			
			throw e;
		}       		
    }  
	
	public void setDocCliente(ActionEvent event) {
        this.documento = (long) event.getComponent().getAttributes().get("parameter");
    }
}
