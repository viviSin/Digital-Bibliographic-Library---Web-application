package service;

import dao.WriteDao;

public class WriteService {
	public static void save(int itemid, int authorid){
		WriteDao.save(itemid, authorid);
	}
}
