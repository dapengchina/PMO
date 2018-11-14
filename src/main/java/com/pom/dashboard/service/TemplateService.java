package com.pom.dashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.entity.Template;

/**
 * 模板操作接口
 * @author xuexuan
 * 2018年10月18日 下午1:51:14
 */
public interface TemplateService {

    /**
     * 获取所有模板
     * @author: xuexuan
     * 2018年10月18日 下午1:51:59
     * @return 
     * List<Template>
     * 
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
