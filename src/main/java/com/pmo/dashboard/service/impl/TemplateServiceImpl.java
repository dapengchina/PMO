package com.pmo.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.TemplateMapper;
import com.pmo.dashboard.entity.Template;
import com.pom.dashboard.service.TemplateService;

@Service
public class TemplateServiceImpl implements TemplateService {

    @Resource
    private TemplateMapper templateMapper;

    @Override
    public List<Template> list() {
        return templateMapper.list();
    }

    @Override
    public List<Template> getByIds(String[] ids) {
        return templateMapper.getByIds(ids);
    }

    @Override
    public void update(Template template) {
        templateMapper.update(template);
    }

    @Override
    public Template getById(String id) {
        return templateMapper.getById(id);
    }

}
