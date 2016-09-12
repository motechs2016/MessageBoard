package com.hack4b.action;

import java.io.ByteArrayInputStream;
import java.util.Map;

import com.hack4b.util.SecurityCode;
import com.hack4b.util.SecurityImage;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 提供有关HTTP服务的工具控制器
 * @author lejie
 *
 */
public class ToolAction extends ActionSupport{
	private ByteArrayInputStream ins = null;

	public ByteArrayInputStream getIns() {
		return ins;
	}
	public void setIns(ByteArrayInputStream ins) {
		this.ins = ins;
	}
	
	/**
	 * 生成验证码
	 * @return
	 */
	public String authCode(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		SecurityCode sCode = new SecurityCode();
		String code = sCode.getSecurityCode();
		SecurityImage sImage = new SecurityImage();
		this.ins = sImage.getImageAsInputStream(code);
		session.put("authCode", code);
		return "success";
	}
}
