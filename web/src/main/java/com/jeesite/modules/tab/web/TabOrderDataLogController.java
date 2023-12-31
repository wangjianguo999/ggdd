package com.jeesite.modules.tab.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.tab.entity.TabOrderDataLog;
import com.jeesite.modules.tab.service.TabOrderDataLogService;

/**
 * tab_order_data_logController
 * @author 1
 * @version 2022-05-10
 */
@Controller
@RequestMapping(value = "${adminPath}/tab/tabOrderDataLog")
public class TabOrderDataLogController extends BaseController {

	@Autowired
	private TabOrderDataLogService tabOrderDataLogService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public TabOrderDataLog get(String rowid, boolean isNewRecord) {
		return tabOrderDataLogService.get(rowid, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("tab:tabOrderDataLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(TabOrderDataLog tabOrderDataLog, Model model) {
		model.addAttribute("tabOrderDataLog", tabOrderDataLog);
		return "modules/tab/tabOrderDataLogList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("tab:tabOrderDataLog:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<TabOrderDataLog> listData(TabOrderDataLog tabOrderDataLog, HttpServletRequest request, HttpServletResponse response) {
		tabOrderDataLog.setPage(new Page<>(request, response));
		Page<TabOrderDataLog> page = tabOrderDataLogService.findPage(tabOrderDataLog);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("tab:tabOrderDataLog:view")
	@RequestMapping(value = "form")
	public String form(TabOrderDataLog tabOrderDataLog, Model model) {
		model.addAttribute("tabOrderDataLog", tabOrderDataLog);
		return "modules/tab/tabOrderDataLogForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("tab:tabOrderDataLog:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated TabOrderDataLog tabOrderDataLog) {
		tabOrderDataLogService.save(tabOrderDataLog);
		return renderResult(Global.TRUE, text("保存tab_order_data_log成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("tab:tabOrderDataLog:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(TabOrderDataLog tabOrderDataLog) {
		tabOrderDataLogService.delete(tabOrderDataLog);
		return renderResult(Global.TRUE, text("删除tab_order_data_log成功！"));
	}
	
}