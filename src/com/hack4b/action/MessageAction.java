package com.hack4b.action;

import com.hack4b.model.Message;
import com.hack4b.service.MessageService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MessageAction extends ActionSupport implements ModelDriven<Message> {
	
	private Message message = new Message();
	private MessageService messageService = null;
	
	@Override
	public Message getModel() {
		return message;
	}
	
	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	/**
	 * 添加留言，访客身份
	 * @return
	 */
	public String addMsgForVisitor(){
		ActionContext context = ActionContext.getContext();
		String authCode = (String)context.getSession().get("authCode");
		if(!authCode.equals(message.getAuthCode())){  //对比验证码
			context.put("msg", "失败，验证码错误！");
			context.put("message", message);
			return "error";
		}
		message.setIsAdmin(false);
		boolean isSuccess = messageService.addMessage(message);
		if(isSuccess){
			context.put("msg", "成功啦～～");
			return "success";
		}
		context.put("msg", "失败了。。:(");
		return "error";
	}
	
	/**
	 * 留言添加，管理员身份
	 * @return
	 */
	public String addMsgForAdmin(){
		ActionContext context = ActionContext.getContext();
		message.setIsAdmin(true);  //设置管理员身份
		Boolean isSuccess = messageService.addMessage(message);
		if(isSuccess){
			context.put("msg", "添加成功！你可以在下方继续添加！");
			return "success";
		}
		context.put("msg", "添加失败，请重试！");
		return "error";
	}
}
