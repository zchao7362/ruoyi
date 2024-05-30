package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysProduct;

/**
 * 商品Service接口
 *
 * @author ruoyi
 * @date 2023-04-12
 */
public interface ISysProductService
{
    /**
     * 查询商品
     *
     * @param id 商品主键
     * @return 商品
     */
    public SysProduct selectSysProductById(Long id);

    /**
     * 查询商品列表
     *
     * @param sysProduct 商品
     * @return 商品集合
     */
    public List<SysProduct> selectSysProductList(SysProduct sysProduct);

    /**
     * 新增商品
     *
     * @param sysProduct 商品
     * @return 结果
     */
    public int insertSysProduct(SysProduct sysProduct);

    /**
     * 修改商品
     *
     * @param sysProduct 商品
     * @return 结果
     */
    public int updateSysProduct(SysProduct sysProduct);

    /**
     * 批量删除商品
     *
     * @param ids 需要删除的商品主键集合
     * @return 结果
     */
    public int deleteSysProductByIds(String ids);

    /**
     * 删除商品信息
     *
     * @param id 商品主键
     * @return 结果
     */
    public int deleteSysProductById(Long id);

    public SysProduct selectSysProductRand();
}
