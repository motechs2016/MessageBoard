package com.hack4b.action;

import java.util.Map;

import com.hack4b.model.User;
import com.hack4b.service.UserService;
import com.hack4b.util.MD5Util;
import com.opensymphony.xwork2.ActionContext;
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
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		String authcodeSe = session.get("authCode").toString();
		String authCodeIn = user.getAuthcode();
		if(!authCodeIn.equals(authcodeSe)){		//校检验证码
			context.put("msg", "验证码错误！");	//向登录页发送错误消息
			context.put("user", user);	//为方便用户登录，向登录页发送当前登录信息
			return "error";
		}
		String userSalt = userService.getSalt(user.getUsername());  //获取用户的散列盐值
		if(userSalt==null){	//如果没有查询到散列盐值，说明用户不存在
			context.put("msg", "用户名或密码错误！");
			return "error";
		}
		User loginUser = userService.login(user.getUsername(), MD5Util.getMd5(user.getPassword()+userSalt));	//登录检查
		if(loginUser!=null){
			session.put("user", loginUser);
			return "success";
		}
		context.put("msg", "用户名或密码错误！");
		return "error";
	}
}
