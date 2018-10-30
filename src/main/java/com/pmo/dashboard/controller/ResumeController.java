package com.pmo.dashboard.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmo.dashboard.entity.Resume;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.util.Utils;
import com.pom.dashboard.service.ResumeService;

/**
 * 候选人的信息的Controller
 * 
 * @author dilu
 * @version 1.0 2017-8-25 15:09:30
 * @param <updateResume>
 */
@Controller
@RequestMapping(value="/resume")
public class ResumeController<updateResume> {
	
	@Resource
	@Autowired
	private ResumeService resumeService;
	

	
	/**
	 * 修改候选人的方法
	 * 
	 * @param candidateId
	 * @param model
	 * @return
	 */
	@RequestMapping("/toUpdateResume")
	public String updateResume(String candidateId,Model model,HttpServletRequest request){
//		String  candidateId = req.getParameter("candidateId");
//		req.setAttribute("candidateId", candidateId);
		boolean result = true;
		List<Resume> list =resumeService.queryResumeInfoById(candidateId);
/*		for(Resume resume: list){
			if(resume.getId().equals(candidateId)){
			model.addAttribute("resume",resume);
			return "/editresume/updateResume";
			}
		}*/
		if(list!=null&&list.size()>0){
			model.addAttribute("resume",list.get(0));
			
			return "/editresume/updateResume";
		}
	

		return null;

	}
	
	
	@RequestMapping("/toUpdateResumeNew")
	@ResponseBody
	public boolean toUpdateResumeNew(final HttpServletRequest request,
            final HttpServletResponse response,HttpSession session ){
		
		String id = request.getParameter("id");
		String candidateName = request.getParameter("candidateName");
		String age = request.getParameter("age");
		String gender= request.getParameter("gender");
		String tel = request.getParameter("tel");
		String education = request.getParameter("education");
		String college = request.getParameter("college");
		String major = request.getParameter("major");
		String experience_years = request.getParameter("experience_years");
		String skill =request.getParameter("skill");
		String graduate_date = request.getParameter("graduate_date");
		String English_level = request.getParameter("English_level");
		String candidate_status = request.getParameter("candidate_status");
		String create_date = request.getParameter("create_date");
		String source = request.getParameter("source");
		String role = request.getParameter("role");
		String entry_date = request.getParameter("entry_date");
		String email = request.getParameter("email");
		String expected_salary = request.getParameter("expected_salary");
		String real_salary = request.getParameter("real_salary");
		String interview_status = request.getParameter("interview_status");
		String remark = request.getParameter("remark");
		String old_company = request.getParameter("old_company");
		
		String oldPath =  resumeService.queryResumeInfoById(id).get(0).getResume_path();
		if(oldPath != null && !"".equals(oldPath)){
		    File oldFile = new File(oldPath);
	        if(oldFile.exists()){
	            //删除旧简历
	            oldFile.delete();
	        }
		}
		
		String resume_path = request.getParameter("resume_path");
		
		if(resume_path == null || "".equals(resume_path)){
		    resume_path = oldPath;
		}
		
		String url=(String) session.getAttribute("url");
        MultipartFile file=(MultipartFile) session.getAttribute("file");
        if(file != null){
            try
            {
                file.transferTo(new File(url));
            }
            catch (IllegalStateException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
		
		Resume resume = new Resume(id,candidateName,age,gender,tel,education,
				college,major,experience_years,skill,graduate_date,English_level,
				candidate_status,create_date,source,role,entry_date,email,expected_salary,
				real_salary,interview_status, remark, old_company,resume_path);
		boolean resultFlag = resumeService.upd(resume);
		
		return resultFlag;
	}
	
	
	
//	@RequestMapping("/toUpdateResumeNew")
//	@ResponseBody
//	public String toUpdateResumeNew(final HttpServletRequest request,
//           Resume resume, Model model){
//		return "true";
//
//	}

	   @RequestMapping("/queryResumeInfoById")
	    @ResponseBody
	    public Object queryResumeInfoById(final HttpServletRequest request,
	            final HttpServletResponse response)
	    {
	        String candidateId = request.getParameter("candidateId");
	        
	        List<Resume> resume = resumeService.queryResumeInfoById(candidateId);
	        
	        return resume.get(0);
	     }

	/**
	 * 跳转到录入信息的页面
	 * 
	 * @return 信息录入页面
	 */
	@RequestMapping("/input")
	public String input(){
		
		return "resume/add";
	}
	
	/**
	 * 根据手机号查询候选人的方法
	 * 
	 * @param tel
	 * @return
	 */
	@RequestMapping("/checkTel")
	@ResponseBody
	public String checkTel(String tel){
		boolean result = true;
		Resume c=resumeService.searchTel(tel);
		if(c!=null){
			result = false;
		}
		Map<String, Boolean> map = new HashMap<>();
        map.put("valid", result);
        ObjectMapper mapper = new ObjectMapper();
        String resultString = "";
        try {
            resultString = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultString;
	}
	
	/**
	 * 录入候选人的方法
	 * 
	 * @param resume
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/add")
	@ResponseBody
	public String add(Resume resume,Model model,HttpSession session ) throws Exception{
		
			//设置候选人id
			String uuid = Utils.getUUID();
			resume.setId(uuid);
			
			//设置候选人的HR
			User user = (User) session.getAttribute("loginUser");
			String userId = user.getUserId();
			resume.setHr(userId);
			//设置创建人
			resume.setCreate_user(userId);
			
			//设置创建时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String todayDate = df.format(new Date());
			resume.setCreate_date(todayDate);
			//将当前时间转换成毫秒
			long nt = df.parse(todayDate).getTime();
			//获取毕业时间
			String graduate_date = resume.getGraduate_date();
			//将毕业时间转换成毫秒
			long gt = df.parse(graduate_date).getTime();
			//计算出差异
			long cha = nt-gt;
			//将差异转换为年数,向下取整 
			int year = (int) Math.floor(cha/ 1000 / 60 / 60 / 24/ 30 /12);
			// 设置工作年限
			resume.setExperience_years(String.valueOf(year));
			
			String url=(String) session.getAttribute("url");
			MultipartFile file=(MultipartFile) session.getAttribute("file");
			file.transferTo(new File(url));
			
		try{
			//调用service层  执行保存操作
			resumeService.add(resume);
			return "0";
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "1";
		}
		
	}
}
