package com.hack4b.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hack4b.model.User;
import com.hack4b.service.UserService;
import com.hack4b.tools.Tools;
import com.hack4b.util.MD5Util;
import com.hack4b.util.Pager;
import com.opensymphony.xwork2.Action;
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
		ActionContext context = ActionContext.getContext();
		int totalRows = userService.getTotalUser();
		int pageSize = 5;  //每页显示五条数据
		Pager pager = new Pager(currentPage,pageSize,totalRows); //计算并保存分页数据
		Map<String,Object> page = new HashMap<>();  
		page.put("currentPage", currentPage);
		page.put("totalPageNum", pager.getTotalPageNum());
		page.put("isPrevious", pager.isPrevious());
		page.put("isNext", pager.isNext());
		page.put("type", "queryAllUser");  //添加页面跳转类型，便于jsp页面动态生成翻页
		List<User> list = userService.queryUser(currentPage, pageSize);
		if(list.size()==0||list==null){
			return "error";
		}
		context.put("page", page);
		context.put("list", list);
		return "success";
	}
	
	/**
	 * 按编号查找用户
	 * @return
	 */
	public String queryUserById(){
		ActionContext context = ActionContext.getContext();
		List<User> userList = new ArrayList<>();
		User user1 = userService.queryUserById(user.getId());
		if(user1==null){
			context.put("msg", "没有找到该编号的用户");
			return "error";
		}
		context.getSession().put("queryRole", user1.getRole());  //可能是用户信息修改功能查询的信息，将查询的用户角色放入后端session。
		userList.add(user1);
		context.put("list", userList);
		return "success";
	}
	
	/**
	 * 按用户名查询
	 * @return
	 */
	public String queryUserByName(){
		ActionContext context = ActionContext.getContext();
		int totalRows = userService.queryUserByName(user.getUsername());
		int pageSize = 5;  //每页显示五条数据
		Pager pager = new Pager(this.currentPage,pageSize,totalRows);  //计算分页数据
		Map<String,Object> page = new HashMap<>();
		page.put("isPrevious", pager.isPrevious());
		page.put("isNext", pager.isNext());
		page.put("currentPage", currentPage);
		page.put("totalPageNum", pager.getTotalPageNum());
		page.put("type", "queryUserByName");  //添加页面跳转类型，便于jsp页面动态生成翻页
		List<User> list = userService.queryUserByName(currentPage, pageSize, user.getUsername());
		if(list==null||list.size()==0){
			context.put("msg", "未查询到该用户名");
			return "error";
		}
		context.put("page", page);
		context.put("list", list);
		return "success";
	}
	
	/**
	 * 按用户邮箱查找用户
	 * @return
	 */
	public String queryUserByMail(){
		ActionContext context = ActionContext.getContext();
		int totalRows = userService.queryUserByMail(user.getEmail());
		int pageSize = 5;  //每页显示五条记录
		Pager pager = new Pager(currentPage,pageSize,totalRows);
		Map<String,Object> page = new HashMap<>();
		page.put("isPrevious", pager.isPrevious());
		page.put("isNext", pager.isNext());
		page.put("currentPage", currentPage);
		page.put("totalPageNum", pager.getTotalPageNum());
		page.put("type", "queryUserByMail");  //添加页面跳转类型，便于jsp页面动态生成翻页
		List<User> list = userService.queryUserByMail(currentPage, pageSize, user.getEmail());
		if(list==null||list.size()==0){
			context.put("msg", "未查询到该邮箱");
			return "error";
		}
		context.put("page", page);
		context.put("list", list);
		return "success";
	}
	
	/**
	 * 按用户ID删除用户
	 * @return
	 */
	public String deleteUserById(){
		ActionContext context = ActionContext.getContext();
		Boolean isSuccess = userService.deleteUserById(user.getId());
		if(isSuccess){
			context.put("msg", "用户删除成功！");
			return "success";
		}
		context.put("msg", "用户删除失败！不过你可以重试一下。");
		return "error";
	}
	
	/**
	 * 修改用户信息
	 * @return
	 */
	public String modifyUserById(){
		ActionContext context = ActionContext.getContext();
		String role = (String) context.getSession().get("queryRole");  //为了安全性，用户角色放在session后端。
		user.setRole(role);
		boolean isSuccess = userService.modifyUserById(user);
		if(isSuccess){
			context.put("msg", "修改成功！");
			return "success";
		}
		return "error";
	}
}
