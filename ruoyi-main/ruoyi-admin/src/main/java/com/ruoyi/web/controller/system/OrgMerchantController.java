package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.system.domain.OrgPayChannel;
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
import com.ruoyi.system.domain.OrgMerchant;
import com.ruoyi.system.service.IOrgMerchantService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商户Controller
 *
 * @author ruoyi
 * @date 2023-03-08
 */
@Controller
@RequestMapping("/system/merchant")
public class OrgMerchantController extends BaseController
{
    private String prefix = "system/merchant";

    @Autowired
    private IOrgMerchantService orgMerchantService;

    @RequiresPermissions("system:merchant:view")
    @GetMapping()
    public String merchant()
    {
        return prefix + "/merchant";
    }

    /**
     * 查询商户列表
     */
    @RequiresPermissions("system:merchant:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OrgMerchant orgMerchant)
    {
        startPage();
        List<OrgMerchant> list = orgMerchantService.selectOrgMerchantList(orgMerchant);
        return getDataTable(list);
    }

    /**
     * 导出商户列表
     */
    @RequiresPermissions("system:merchant:export")
    @Log(title = "商户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OrgMerchant orgMerchant)
    {
        List<OrgMerchant> list = orgMerchantService.selectOrgMerchantList(orgMerchant);
        ExcelUtil<OrgMerchant> util = new ExcelUtil<OrgMerchant>(OrgMerchant.class);
        return util.exportExcel(list, "商户数据");
    }

    /**
     * 新增商户
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存商户
     */
    @RequiresPermissions("system:merchant:add")
    @Log(title = "商户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OrgMerchant orgMerchant)
    {
        return toAjax(orgMerchantService.insertOrgMerchant(orgMerchant));
    }

    /**
     * 修改商户
     */
    @RequiresPermissions("system:merchant:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OrgMerchant orgMerchant = orgMerchantService.selectOrgMerchantById(id);
        mmap.put("orgMerchant", orgMerchant);
        return prefix + "/edit";
    }

    /**
     * 修改保存商户
     */
    @RequiresPermissions("system:merchant:edit")
    @Log(title = "商户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OrgMerchant orgMerchant)
    {
        return toAjax(orgMerchantService.updateOrgMerchant(orgMerchant));
    }

    /**
     * 删除商户
     */
    @RequiresPermissions("system:merchant:remove")
    @Log(title = "商户", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(orgMerchantService.deleteOrgMerchantByIds(ids));
    }


    @RequiresPermissions("system:merchant:changeStatus")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(OrgMerchant orgMerchant)
    {
        return toAjax(orgMerchantService.updateOrgMerchant(orgMerchant));
    }
}
