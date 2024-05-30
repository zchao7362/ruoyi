package com.ruoyi.system.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysAlipayConfig;
import com.ruoyi.system.service.ISysAlipayConfigService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * alipayConfigController
 * 
 * @author ruoyi
 * @date 2023-06-10
 */
@Controller
@RequestMapping("/system/alipayConfig")
public class SysAlipayConfigController extends BaseController
{
    private String prefix = "system/alipayConfig";

    @Autowired
    private ISysAlipayConfigService sysAlipayConfigService;

    @RequiresPermissions("system:alipayConfig:view")
    @GetMapping()
    public String alipayConfig()
    {
        return prefix + "/alipayConfig";
    }

    /**
     * 查询alipayConfig列表
     */
    @RequiresPermissions("system:alipayConfig:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysAlipayConfig sysAlipayConfig)
    {
        startPage();
        List<SysAlipayConfig> list = sysAlipayConfigService.selectSysAlipayConfigList(sysAlipayConfig);
        return getDataTable(list);
    }

    /**
     * 导出alipayConfig列表
     */
    @RequiresPermissions("system:alipayConfig:export")
    @Log(title = "alipayConfig", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysAlipayConfig sysAlipayConfig)
    {
        List<SysAlipayConfig> list = sysAlipayConfigService.selectSysAlipayConfigList(sysAlipayConfig);
        ExcelUtil<SysAlipayConfig> util = new ExcelUtil<SysAlipayConfig>(SysAlipayConfig.class);
        return util.exportExcel(list, "alipayConfig数据");
    }

    /**
     * 新增alipayConfig
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存alipayConfig
     */
    @RequiresPermissions("system:alipayConfig:add")
    @Log(title = "alipayConfig", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysAlipayConfig sysAlipayConfig)
    {
        return toAjax(sysAlipayConfigService.insertSysAlipayConfig(sysAlipayConfig));
    }

    /**
     * 修改alipayConfig
     */
    @RequiresPermissions("system:alipayConfig:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SysAlipayConfig sysAlipayConfig = sysAlipayConfigService.selectSysAlipayConfigById(id);
        mmap.put("sysAlipayConfig", sysAlipayConfig);
        return prefix + "/edit";
    }

    /**
     * 修改保存alipayConfig
     */
    @RequiresPermissions("system:alipayConfig:edit")
    @Log(title = "alipayConfig", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysAlipayConfig sysAlipayConfig)
    {
        return toAjax(sysAlipayConfigService.updateSysAlipayConfig(sysAlipayConfig));
    }

    /**
     * 删除alipayConfig
     */
    @RequiresPermissions("system:alipayConfig:remove")
    @Log(title = "alipayConfig", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysAlipayConfigService.deleteSysAlipayConfigByIds(ids));
    }
}
