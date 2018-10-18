package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;

import org.apache.ibatis.annotations.Param;
import edu.eci.pdsw.samples.entities.ItemRentado;

public interface ItemRentadoMapper {
	//public void save(ItemRentado itr);
	public ItemRentado consultarItemRentado(@Param("idi") int itr);
}
