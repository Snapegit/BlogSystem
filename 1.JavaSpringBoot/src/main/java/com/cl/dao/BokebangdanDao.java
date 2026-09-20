package com.cl.dao;

import com.cl.entity.BokebangdanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.BokebangdanView;


/**
 * 博客榜单
 * 
 * @author 
 * @email 
 * @date 2024-03-04 11:31:10
 */
public interface BokebangdanDao extends BaseMapper<BokebangdanEntity> {
	
	List<BokebangdanView> selectListView(@Param("ew") Wrapper<BokebangdanEntity> wrapper);

	List<BokebangdanView> selectListView(Pagination page,@Param("ew") Wrapper<BokebangdanEntity> wrapper);
	
	BokebangdanView selectView(@Param("ew") Wrapper<BokebangdanEntity> wrapper);
	

}
