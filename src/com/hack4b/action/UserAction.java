package com.hack4b.action;

import com.hack4b.model.User;
import com.hack4b.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 用户理控制器
 * @author lejie
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {
	
	private UserService userService = null;  //用户服务层
	
	private User user = new User();  //用户请求Bean
	@Override
	public User getModel() {
		return user;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	/**
	 * 用户登录
	 * @return
	 */
	public String login(){
		User loginUser = userService.login(user.getUsername(), user.getPassword());
		if(loginUser!=null){
			return "success";
		}
		return "error";
	}
}
