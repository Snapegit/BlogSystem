package com.cl.dao;

import com.cl.entity.DiscussbokebangdanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscussbokebangdanView;


/**
 * 博客榜单评论表
 * 
 * @author 
 * @email 
 * @date 2024-03-04 11:31:10
 */
public interface DiscussbokebangdanDao extends BaseMapper<DiscussbokebangdanEntity> {
	
	List<DiscussbokebangdanView> selectListView(@Param("ew") Wrapper<DiscussbokebangdanEntity> wrapper);

	List<DiscussbokebangdanView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussbokebangdanEntity> wrapper);
	
	DiscussbokebangdanView selectView(@Param("ew") Wrapper<DiscussbokebangdanEntity> wrapper);
	

}
