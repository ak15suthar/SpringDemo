package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.w3c.dom.ls.LSInput;

import com.bean.SkillBean;

@Repository
public class SkillDao {

	@Autowired
	JdbcTemplate stmt;
	
	public void insertSkill(SkillBean skillBean) {
		stmt.update("insert into skill(skillname) values(?)",skillBean.getSkillName());
		
	}
	
	public List<SkillBean> listSkill() {
		
		List<SkillBean> skillBean =  stmt.query("select * from skill", BeanPropertyRowMapper.newInstance(SkillBean.class));
		
		return skillBean;
	}

	public void deleteSkill(int skillId) {
		stmt.update("delete from skill where skillid = ?",skillId);
	
	}
	
	public void updateSkill(SkillBean skillBean) {
		stmt.update("update skill set skillname = ? where skillid = ?",skillBean.getSkillName(),skillBean.getSkillId());
	}
}
