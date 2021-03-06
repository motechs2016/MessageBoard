package com.hack4b.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.JsonObject;
import com.hack4b.model.User;
import com.hack4b.service.SettingsService;
import com.hack4b.util.SettingsUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SettingsAction extends ActionSupport {
	private SettingsService settingsService = null;
	private String webtitle; //网站标题
	private String title1;  //首页一级标题
	private String title2;  //首页二级标题
	private String webbutton;  //首页按钮名
	private String msgnumber;  //首页留言数量
	private String copyright1; //首页一级版权
	private String copyright2;  //首页二级版权
	
	public SettingsService getSettingsService() {
		return settingsService;
	}
	public void setSettingsService(SettingsService settingsService) {
		this.settingsService = settingsService;
	}
	public String getWebtitle() {
		return webtitle;
	}
	public void setWebtitle(String webtitle) {
		this.webtitle = webtitle;
	}
	public String getTitle1() {
		return title1;
	}
	public void setTitle1(String title1) {
		this.title1 = title1;
	}
	public String getTitle2() {
		return title2;
	}
	public void setTitle2(String title2) {
		this.title2 = title2;
	}
	public String getWebbutton() {
		return webbutton;
	}
	public void setWebbutton(String webbutton) {
		this.webbutton = webbutton;
	}
	public String getMsgnumber() {
		return msgnumber;
	}
	public void setMsgnumber(String msgnumber) {
		this.msgnumber = msgnumber;
	}
	public String getCopyright1() {
		return copyright1;
	}
	public void setCopyright1(String copyright1) {
		this.copyright1 = copyright1;
	}
	public String getCopyright2() {
		return copyright2;
	}
	public void setCopyright2(String copyright2) {
		this.copyright2 = copyright2;
	}
	
	/**
	 * 修改网站配置信息
	 * @return
	 */
	public String midifySettings(){
		ActionContext context = ActionContext.getContext();
		User user = (User)context.getSession().get("user");  //获取用户角色
		String role = user.getRole();
		if(!role.equals("超级管理员")){  //如果用户角色不是超级管理员
			context.put("msg", "抱歉，你所在的用户角色无法更改该设置！");
			context.put("isModify", false);
			return "error";
		}
		try {
			settingsService.modifySettings("WebTitle", webtitle);
			settingsService.modifySettings("Title1", title1);
			settingsService.modifySettings("Title2", title2);
			settingsService.modifySettings("WebButton", webbutton);
			settingsService.modifySettings("MsgNumber", msgnumber);
			settingsService.modifySettings("Copyright1", copyright1);
			settingsService.modifySettings("Copyright2", copyright2);
		} catch (Exception e) {
			e.printStackTrace();
			context.put("msg", "修改失败！");
			return "error";
		}
		context.put("msg", "修改成功！");
		return "success";
	}
	
	/**
	 * 获取网站配置信息
	 * @return
	 */
	public String getSettings(){
		ActionContext context = ActionContext.getContext();
		User user = (User)context.getSession().get("user");  //获取用户角色
	    String role = user.getRole();
		if(!role.equals("超级管理员")||role == null||role.equals("")){  //如果用户角色不是超级管理员
			context.put("msg", "抱歉，你所在的用户角色无法更改该设置！");
			context.put("isModify", false);
			return "error";
		}
		Map<String,String> map = SettingsUtil.getInstance().getSettings();
		if(map.size()==0||map==null){
			context.put("msg", "获取配置失败！");
			return "error";
		}
		context.put("map", map);
		return "success";
	}
	
	/**
	 * 获取网站配置信息并返回json数据格式
	 * @return
	 * @throws IOException 
	 */
	public void getSettingsForJson() throws IOException{
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setHeader("Content-Type", "application/json;charset=utf-8");
		resp.setHeader("Access-Control-Allow-Origin", "*");  //允许跨域访问
		Map<String,String> map = SettingsUtil.getInstance().getSettings();
		JsonObject jobj = new JsonObject();
		jobj.addProperty("WebTitle", map.get("WebTitle"));
		jobj.addProperty("Title1", map.get("Title1"));
		jobj.addProperty("Title2", map.get("Title2"));
		jobj.addProperty("WebButton", map.get("WebButton"));
		jobj.addProperty("MsgNumber", map.get("MsgNumber"));
		jobj.addProperty("Copyright1", map.get("Copyright1"));
		jobj.addProperty("Copyright2", map.get("Copyright2"));
		String result = jobj.toString();
		PrintWriter pw = resp.getWriter();
		pw.print(result);
		pw.flush();
		pw.close();
	}
}
