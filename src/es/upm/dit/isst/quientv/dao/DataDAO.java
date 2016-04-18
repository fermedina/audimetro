package es.upm.dit.isst.quientv.dao;

import java.util.Date;
import java.util.List;

import es.upm.dit.isst.quientv.model.Data;

public interface DataDAO {
	
public Data newData(String hashtag_id, Date intervalo, int tweets, int retweets, int favoritos);
	
	public Data getData(int id);
	
	public List<Data> getDataList();
	
	public List<Data> getDataListByHashtagId(String hashtag_id);
	
	public Data updateData(Data data);
	
	public void deleteData(String id);

}
