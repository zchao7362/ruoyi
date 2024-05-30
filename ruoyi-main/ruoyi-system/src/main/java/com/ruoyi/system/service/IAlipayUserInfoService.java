package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.AlipayUserInfo;

/**
 * 支付宝用户信息Service接口
 * 
 * @author ruoyi
 * @date 2023-08-19
 */
public interface IAlipayUserInfoService 
{
    /**
     * 查询支付宝用户信息
     * 
     * @param id 支付宝用户信息主键
     * @return 支付宝用户信息
     */
    public AlipayUserInfo selectAlipayUserInfoById(Long id);


    public AlipayUserInfo selectAlipayUserInfoByUid(String uid);
    /**
     * 查询支付宝用户信息列表
     * 
     * @param alipayUserInfo 支付宝用户信息
     * @return 支付宝用户信息集合
     */
    public List<AlipayUserInfo> selectAlipayUserInfoList(AlipayUserInfo alipayUserInfo);

    /**
     * 新增支付宝用户信息
     * 
     * @param alipayUserInfo 支付宝用户信息
     * @return 结果
     */
    public int insertAlipayUserInfo(AlipayUserInfo alipayUserInfo);

    /**
     * 修改支付宝用户信息
     * 
     * @param alipayUserInfo 支付宝用户信息
     * @return 结果
     */
    public int updateAlipayUserInfo(AlipayUserInfo alipayUserInfo);

    /**
     * 批量删除支付宝用户信息
     * 
     * @param ids 需要删除的支付宝用户信息主键集合
     * @return 结果
     */
    public int deleteAlipayUserInfoByIds(String ids);

    /**
     * 删除支付宝用户信息信息
     * 
     * @param id 支付宝用户信息主键
     * @return 结果
     */
    public int deleteAlipayUserInfoById(Long id);
}
