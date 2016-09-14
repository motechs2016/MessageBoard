package com.hack4b.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hack4b.model.User;
import com.hack4b.service.UserService;
import com.hack4b.tools.Tools;
import com.hack4b.util.MD5Util;
import com.hack4b.util.Pager;
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
	private int currentPage = 1;  //当前页码
	
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
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * 用户登录
	 * @return
	 */
	public String login(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		String authcodeSe = session.get("authCode").toString();	//获取session中的验证码	
		String authCodeIn = user.getAuthcode();	//获取用户输入的验证码
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
		Boolean isSuccess = userService.addUser(user);
		if(isSuccess){
			context.put("msg", "用户添加成功！你可以继续添加。");
			return "success";
		}
		context.put("msg", "用户添加失败！你可以重新添加试一下。");
		return "error";
	}
	
	/**
	 * 查询所有用户，并分页显示
	 * @return
	 */
	public String queryAllUser(){
		//TODO 分页显示BUG
		ActionContext context = ActionContext.getContext();
		int totalRows = userService.getTotalUser();
		int pageSize = 5;  //每页显示五条数据
		Pager pager = new Pager(currentPage,pageSize,totalRows); //计算并保存分页数据
		Map<String,Object> page = new HashMap<>();  
		page.put("currentPage", currentPage);
		page.put("totalPageNum", pager.getTotalPageNum());
		page.put("isPrevious", pager.isPrevious());
		page.put("isNext", pager.isNext());
		List<User> list = userService.queryUser(currentPage, pageSize);
		if(list.size()==0||list==null){
			return "error";
		}
		context.put("page", page);
		context.put("list", list);
		return "success";
	}
}
