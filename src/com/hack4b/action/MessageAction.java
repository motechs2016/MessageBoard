package com.hack4b.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hack4b.model.Message;
import com.hack4b.service.MessageService;
import com.hack4b.util.Pager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MessageAction extends ActionSupport implements ModelDriven<Message> {
	
	private Message message = new Message();
	private MessageService messageService = null;
	private int currentPage = 1;  //当前页码
	
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
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
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
	
	/**
	 * 查询所有留言内容
	 * @return
	 */
	public String queryAllMsg(){
		ActionContext context = ActionContext.getContext();
		int totalRows = messageService.getNumForMsg();
		int pageSize = 5;	//每页显示五条记录
		Pager pager = new Pager(currentPage,pageSize,totalRows);
		Map<String,Object> page = new HashMap<>();
		page.put("isPrevious", pager.isPrevious());  //是否有上一页
		page.put("isNext", pager.isNext());  //是否有下一页
		page.put("currentPage", currentPage);  //当前页
		page.put("totalPageNum", pager.getTotalPageNum());  //总页数
		page.put("type", "queryAllMsg");  //设置页面显示类型，配合前端jsp页面实现动态action
		context.put("page", page);
		List<Message> list = messageService.getAllMessage(currentPage, pageSize);
		if(list.size()==0||list==null){
			context.put("msg", "没有记录");
			return "error";
		}
		context.put("list", list);
		return "success";
	}
}
