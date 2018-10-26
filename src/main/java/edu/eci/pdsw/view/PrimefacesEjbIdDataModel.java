package edu.eci.pdsw.view;

import java.util.List;

import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;
import edu.eci.pdsw.samples.entities.Cliente;

public class PrimefacesEjbIdDataModel <T extends Cliente> extends ListDataModel<T> implements SelectableDataModel<T> {
	public PrimefacesEjbIdDataModel(List<T> data) 
	{ 
		super(data); 
	} 
	
	@Override public T getRowData(String rowKey){	
		List<T> list = (List<T>) getWrappedData(); 	
		for(T ejb : list){	
			if(ejb.getDocumento()==(new Integer(rowKey))){return ejb;} 
		} 
		return null; 
	} 

	@Override public Object getRowKey(T item) {return item.getDocumento();} 
	
}