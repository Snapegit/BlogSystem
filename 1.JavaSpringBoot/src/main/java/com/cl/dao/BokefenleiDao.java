package com.cl.dao;

import com.cl.entity.BokefenleiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.BokefenleiView;


/**
 * 博客分类
 * 
 * @author 
 * @email 
 * @date 2024-03-04 11:31:10
 */
public interface BokefenleiDao extends BaseMapper<BokefenleiEntity> {
	
	List<BokefenleiView> selectListView(@Param("ew") Wrapper<BokefenleiEntity> wrapper);

	List<BokefenleiView> selectListView(Pagination page,@Param("ew") Wrapper<BokefenleiEntity> wrapper);
	
	BokefenleiView selectView(@Param("ew") Wrapper<BokefenleiEntity> wrapper);
	

}
