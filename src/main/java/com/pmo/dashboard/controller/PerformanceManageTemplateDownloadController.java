package com.pmo.dashboard.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.runners.Parameterized.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.pmo.dashboard.entity.Template;
import com.pmo.dashboard.util.Constants;
import com.pmo.dashboard.util.FileUtils;
import com.pom.dashboard.service.TemplateService;

/**
 * Management模块 -- 模板上传/下载控制器
 * @author xuexuan
 * 2018年10月16日 上午10:40:25
 * 
 */
@Controller
@RequestMapping(value = "/performanceTemplate")
public class PerformanceManageTemplateDownloadController {
    private static Logger   logger = LoggerFactory.getLogger(FileUtils.class);

    @Resource
    private TemplateService templateService;

    /**
     * 单模板下载
     * @author: xuexuan
     * 2018年10月17日 下午7:09:02
     * @param request
     * @param response
     * @return 
     * ResponseEntity<byte[]>
     * 
     */
    @RequestMapping("/download")
    @ResponseBody
    public ResponseEntity<byte[]> download(@RequestParam String templateId) {
        Template template = templateService.getById(templateId);
        BufferedInputStream br;
        byte[] body = null;
        try {
            br = new BufferedInputStream(new FileInputStream(new File(template.getUrl())));
            body = new byte[br.available()];
            br.read(body);
        } catch (FileNotFoundException e1) {
            logger.error("下载文件不存在！文件路径：" + template.getUrl());
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileName = null;
        try {
            fileName = new String((template.getName() + template.getUrl().substring(template.getUrl().indexOf("."))).getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        headers.setContentDispositionFormData("attachment", fileName);// 文件名称

        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(body, headers, HttpStatus.CREATED);

        return responseEntity;

    }

    /**
     * 多模板下载
     * @author: xuexuan
     * 2018年10月17日 下午7:09:02
     * @param request
     * @param response
     * @return 
     * ResponseEntity<byte[]>
     * 
     */
    @RequestMapping("/downloads")
    @ResponseBody
    public ResponseEntity<byte[]> downloads(@RequestParam String templateIds) {
        String[] templateIdsAry = templateIds.split(",");
        List<Template> list = templateService.getByIds(templateIdsAry);
        List<File> fileList = new ArrayList<>();
        for (Template template : list) {
            fileList.add(new File(template.getUrl()));
        }

        // ---------------------------压缩文件处理-------------------------------
        ByteArrayOutputStream byteOutPutStream = new ByteArrayOutputStream();
        // 压缩文件
        FileUtils.batchFileToZIP(fileList, byteOutPutStream);
        // ---------------------------压缩文件处理-------------------------------
        String fileName = null;
        try {
            fileName = new String("模板.zip".getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        headers.setContentDispositionFormData("attachment", fileName);// 文件名称

        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(byteOutPutStream.toByteArray(), headers, HttpStatus.CREATED);

        return responseEntity;

    }

    /**
     * 上传模板
     * @author: Song_Lee
     * 2018年10月17日 下午7:09:16
     * @param file
     * @param request
     * @param response
     * @param session
     * @throws Exception 
     * void
     */
    @RequestMapping(value = "/upload.html")
    @ResponseBody
    public String upload(@RequestParam(value = "upload") MultipartFile file, @RequestParam String templateId) throws Exception {
        // 根据id查找模板
        Template template = templateService.getById(templateId);

        // 将文件从内存写入磁盘        文件重命名
        File dir = new File(Constants.TEMPLATE_PATH);
        if (!dir.exists()) {//文件路径不存在时，创建保存文件所需要的路径
            dir.mkdirs();
        }
        String newFileName = template.getName() + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
        file.transferTo(new File(Constants.TEMPLATE_PATH + newFileName));

        // 更新数据库
        template.setUrl(Constants.TEMPLATE_PATH + newFileName);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        template.setTime(sf.format(new Date()));
        templateService.update(template);
        return "success";
    }
}
