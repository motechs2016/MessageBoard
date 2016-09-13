package com.hack4b.action;

import java.util.Map;

import com.hack4b.model.User;
import com.hack4b.service.UserService;
import com.hack4b.util.MD5Util;
import com.hack4b.util.SecurityCode;
import com.hack4b.util.SecurityCode.SecurityCodeLevel;
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
	
	/**
	 * 用户退出
	 * @return
	 */
	public String logout(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		session.clear();
		context.put("msg", "你已成功退出！现在你可以安全的关闭浏览器了！");
		return "success";
	}
	
	/**
	 * 用户添加
	 * @return
	 */
	public String addUser(){
		ActionContext context = ActionContext.getContext();
		SecurityCode scd = new SecurityCode();
		//用户密码加盐
		String salt = scd.getSecurityCode(6, SecurityCodeLevel.Hard, false);
		String password = MD5Util.getMd5(user.getPassword()+ salt);
		user.setPassword(password);
		user.setSalt(salt);
		Boolean isSuccess = userService.addUser(user);
		if(isSuccess){
			context.put("msg", "用户添加成功！你可以继续添加。");
			return "success";
		}
		context.put("msg", "用户添加失败！你可以重新添加试一下。");
		return "error";
	}
}
