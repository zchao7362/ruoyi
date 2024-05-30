package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AlipayUserInfoMapper;
import com.ruoyi.system.domain.AlipayUserInfo;
import com.ruoyi.system.service.IAlipayUserInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 支付宝用户信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-08-19
 */
@Service
public class AlipayUserInfoServiceImpl implements IAlipayUserInfoService 
{
    @Autowired
    private AlipayUserInfoMapper alipayUserInfoMapper;

    /**
     * 查询支付宝用户信息
     * 
     * @param id 支付宝用户信息主键
     * @return 支付宝用户信息
     */
    @Override
    public AlipayUserInfo selectAlipayUserInfoById(Long id)
    {
        return alipayUserInfoMapper.selectAlipayUserInfoById(id);
    }

    /**
     * 查询支付宝用户信息列表
     * 
     * @param alipayUserInfo 支付宝用户信息
     * @return 支付宝用户信息
     */
    @Override
    public List<AlipayUserInfo> selectAlipayUserInfoList(AlipayUserInfo alipayUserInfo)
    {
        return alipayUserInfoMapper.selectAlipayUserInfoList(alipayUserInfo);
    }

    /**
     * 新增支付宝用户信息
     * 
     * @param alipayUserInfo 支付宝用户信息
     * @return 结果
     */
    @Override
    public int insertAlipayUserInfo(AlipayUserInfo alipayUserInfo)
    {
        return alipayUserInfoMapper.insertAlipayUserInfo(alipayUserInfo);
    }

    /**
     * 修改支付宝用户信息
     * 
     * @param alipayUserInfo 支付宝用户信息
     * @return 结果
     */
    @Override
    public int updateAlipayUserInfo(AlipayUserInfo alipayUserInfo)
    {
        return alipayUserInfoMapper.updateAlipayUserInfo(alipayUserInfo);
    }

    /**
     * 批量删除支付宝用户信息
     * 
     * @param ids 需要删除的支付宝用户信息主键
     * @return 结果
     */
    @Override
    public int deleteAlipayUserInfoByIds(String ids)
    {
        return alipayUserInfoMapper.deleteAlipayUserInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除支付宝用户信息信息
     * 
     * @param id 支付宝用户信息主键
     * @return 结果
     */
    @Override
    public int deleteAlipayUserInfoById(Long id)
    {
        return alipayUserInfoMapper.deleteAlipayUserInfoById(id);
    }

    @Override
    public AlipayUserInfo selectAlipayUserInfoByUid(String uid) {
        return alipayUserInfoMapper.selectAlipayUserInfoByUid(uid);
    }
}
