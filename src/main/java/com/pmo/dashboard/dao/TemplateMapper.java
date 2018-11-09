package com.pmo.dashboard.dao;

import java.util.List;

import com.pmo.dashboard.entity.Template;

/**
 * 模板dao
 * @author xuexuan
 * 2018年10月18日 下午1:48:47
 */
public interface TemplateMapper {

    /**
     * 获取所有模板文件
     * @author: xuexuan
     * 2018年10月18日 下午2:01:24
     * @return 
     * List<Template>
     */
    public List<Template> list();

    /**
     * 根据ID集合获取模板
     * @author: xuexuan
     * 2018年10月18日 下午3:45:14
     * @param ids
     * @return 
     * List<Template>
     */
    public List<Template> getByIds(String[] ids);

    /**
     * 根据ID获取模板
     * @author: xuexuan
     * 2018年10月18日 下午3:45:14
     * @param ids
     * @return 
     * List<Template>
     */
    public Template getById(String id);

    /**
     * 根据id更新模板属性
     * @author: xuexuan
     * 2018年10月18日 下午4:16:26
     * @param template 
     * void
     */
    public void update(Template template);

}
