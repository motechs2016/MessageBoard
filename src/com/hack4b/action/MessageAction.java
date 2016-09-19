package com.hack4b.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;

import org.apache.struts2.ServletActionContext;

import com.hack4b.model.Message;
import com.hack4b.service.MessageService;
import com.hack4b.util.Pager;
import com.hack4b.util.SettingsUtil;
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
		int pageSize = Integer.valueOf(SettingsUtil.getInstance().getSettings().get("MsgNumber"));	//每页显示记录
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
		/**
		 * 按用户名显示留言内容
		 * @return
		 */
	public String getMsgByUserName(){
		ActionContext context = ActionContext.getContext();
		int totalRows = messageService.getNumByUserName(message.getUsername());
		int pageSize = 5;  //每页显示五条记录
		Pager pager = new Pager(this.currentPage,pageSize,totalRows);
		Map<String,Object> page = new HashMap<>();
		page.put("isPrevious", pager.isPrevious());  //是否有上一页
		page.put("isNext", pager.isNext());  //是否有下一页
		page.put("currentPage", currentPage);  //当前页
		page.put("totalPageNum", pager.getTotalPageNum());  //总页数
		page.put("type", "queryMsgByUserName");  //设置页面显示类型，配合前端jsp页面实现动态action
		context.put("page", page);
		List<Message> list = messageService.getMsgByUserName(message.getUsername());
		if(list.size()==0||list==null){
			context.put("msg", "没有记录");
			return "error";
		}
		context.put("list", list);
		return "success";
	}
	
	/**
	 * 按邮箱查询留言
	 * @return
	 */
	public String getMsgByMail(){
		ActionContext context = ActionContext.getContext();
		int totalRows = messageService.getNumByMail(message.getEmail());
		int pageSize = 5;  //每页显示数量
		Pager pager = new Pager(this.currentPage,pageSize,totalRows);
		Map<String,Object> page = new HashMap<>();
		page.put("isPrevious", pager.isPrevious());  //是否有上一页
		page.put("isNext", pager.isNext());  //是否有下一页
		page.put("currentPage", currentPage);  //当前页
		page.put("totalPageNum", pager.getTotalPageNum());  //总页数
		page.put("type", "queryMsgByMail");  //设置页面显示类型，配合前端jsp页面实现动态action
		context.put("page", page);
		List<Message> list = messageService.getMsgByMail(message.getEmail());
		if(list.size()==0||list==null){
			context.put("msg", "没有记录");
			return "error";
		}
//		context.put("list", list);
		ServletActionContext.getRequest().setAttribute("list", list);
		return null;
//		return "success";
	}
	
	/**
	 * 按内容查询留言
	 * @return
	 */
	public String getMsgByContent(){
		ActionContext context = ActionContext.getContext();
		int totalRows = messageService.getNumByContent(message.getContent());
		int pageSize = 5;  //每页显示的记录数
		Pager pager = new Pager(this.currentPage,pageSize,totalRows);
		Map<String,Object> page = new HashMap<>();
		page.put("isPrevious", pager.isPrevious());  //是否有上一页
		page.put("isNext", pager.isNext());  //是否有下一页
		page.put("currentPage", currentPage);  //当前页
		page.put("totalPageNum", pager.getTotalPageNum());  //总页数
		page.put("type", "queryMsgByContent");  //设置页面显示类型，配合前端jsp页面实现动态action
		context.put("page", page);
		List<Message> list = messageService.getMsgByContent(message.getContent());
		if(list.size()==0||list==null){
			context.put("msg", "没有记录");
			return "error";
		}
		context.put("list", list);
		return "success";
	}
	
	/**
	 * 根据ID查找留言
	 * @return
	 */
	public String getMsgById(){
		ActionContext context = ActionContext.getContext();
		Message msg = messageService.getMsgById(message.getId());
		if(msg==null){
			context.put("msg", "没有记录！");
			return "error";
		}
		context.put("msg", msg);
		return "success";
	}
	
	/**
	 * 修改留言信息
	 * @return
	 */
	public String modifyMsg(){
		ActionContext context = ActionContext.getContext();
		boolean isSuccess = messageService.modifyMsg(message);
		if(isSuccess){
			return "success";
		}
		context.put("msg", "修改失败，请重试！");
		return "error";
	}
	
	/**
	 * 删除留言
	 * @return
	 */
	public String deleteMsg(){
		ActionContext context = ActionContext.getContext();
		boolean isSuccess = false;
		isSuccess = messageService.deleteMsg(message.getId());
		if(isSuccess){
			return "success";
		}
		context.put("msg", "删除失败！");
		return "error";
	}
}
