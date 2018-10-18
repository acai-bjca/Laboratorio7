package edu.eci.pdsw.samples.services.impl;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import com.google.inject.Inject;

import edu.eci.pdsw.sampleprj.dao.ClienteDAO;
import edu.eci.pdsw.sampleprj.dao.ItemDAO;
import edu.eci.pdsw.sampleprj.dao.ItemRentadoDAO;
import edu.eci.pdsw.sampleprj.dao.TipoItemDAO;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;

public class ServiciosAlquilerImpl implements ServiciosAlquiler {
	@Inject 
	private ClienteDAO clienteDAO;
	@Inject 
	private ItemDAO itemDAO;
	@Inject 
	private TipoItemDAO tipoItemDAO;
	
	@Override
	public int valorMultaRetrasoxDia(int itemId) {		
		Item item= itemDAO.load(itemId);
		return (int) item.getTarifaxDia();		 
	}

	@Override
	public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler {
		return clienteDAO.load((int) docu);
	}

	@Override
	public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler {
		return clienteDAO.load((int)idcliente).getRentados();		
	}

	@Override
	public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
		return clienteDAO.load();
	}

	@Override
	public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
		return itemDAO.load(id);
	}

	@Override
	public List<Item> consultarItemsDisponibles() {
		return itemDAO.loadItemsDisponibles();
	}

	@Override
	public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {		
		return itemDAO.loadMultaItemAlquilado(iditem, fechaDevolucion);
	}

	@Override
	public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {		
		return tipoItemDAO.loadTipoItem(id);
	}

	@Override
	public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {		
		return tipoItemDAO.loadTiposItem();
	}

	@Override
	public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler {		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date); 
		calendar.add(Calendar.DAY_OF_YEAR, numdias);  
		clienteDAO.saveAlquilerItem(new ItemRentado(0, item, date, (Date)calendar.getTime()), docu);

	}

	@Override
	public void registrarCliente(Cliente p) throws ExcepcionServiciosAlquiler {
		clienteDAO.save(p);

	}

	@Override
	public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {		
		return valorMultaRetrasoxDia(iditem)*numdias;	
	}

	@Override
	public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {		
		itemDAO.saveTarifaItem(id, tarifa);
	}

	@Override
	public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
		itemDAO.save(i);
	}

	@Override
	public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
		clienteDAO.saveEstadoCliente(docu, estado);
	}

}
