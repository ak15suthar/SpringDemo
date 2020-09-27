package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.bean.SkillBean;
import com.dao.SkillDao;

@RestController
public class SkillController {
	
	@Autowired
	SkillDao skillDao;
	
	@PostMapping("/skill")
	public ResponseBean<SkillBean> addSkill(SkillBean skillBean){
		
		skillDao.insertSkill(skillBean);
		
		ResponseBean<SkillBean> responseBean = new ResponseBean<>();
		responseBean.setData(skillBean);
		responseBean.setMsg("Skill Added!!");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/listSkill")
	public ResponseBean<List<SkillBean>> listSkill(){
		
		ResponseBean<List<SkillBean>> responseBean = new ResponseBean<>();
		
		List<SkillBean> skillBean = skillDao.listSkill();	
		responseBean.setData(skillBean);
		responseBean.setMsg("Skill List!!");
		responseBean.setStatus(201);
		
		return responseBean;
		
	}
	
	@DeleteMapping("/deleteSkill/{skillid}")
	public ResponseBean<SkillBean> deleteSkill(@PathVariable("skillid") int skillid){
		
		ResponseBean<SkillBean> responseBean = new ResponseBean<>();
		
		skillDao.deleteSkill(skillid);
		responseBean.setMsg("Deleted Skill");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@PutMapping("/updateSkill")
	public ResponseBean<SkillBean> updateSkill(SkillBean skillBean){
		
		ResponseBean<SkillBean> responseBean = new ResponseBean<>();
		
		skillDao.updateSkill(skillBean);
		
		responseBean.setData(skillBean);
		responseBean.setMsg("Update Successfully!!");
		responseBean.setStatus(200);
		return responseBean;
	}
}
