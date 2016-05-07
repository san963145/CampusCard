package com.sys.card.dao;

import java.util.List;

public interface AdminDao {
	
	/**
 	 *  验证用户名、密码是否正确
 	 */
     public List select(String userName,String password);

}
