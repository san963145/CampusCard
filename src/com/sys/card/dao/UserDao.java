package com.sys.card.dao;


import com.sys.card.bean.User;



public interface UserDao {
	

     public User select(String userId);
     public void addUser(User user);

}
