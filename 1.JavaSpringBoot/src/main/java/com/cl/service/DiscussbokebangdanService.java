package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.DiscussbokebangdanEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscussbokebangdanView;


/**
 * 博客榜单评论表
 *
 * @author 
 * @email 
 * @date 2024-03-04 11:31:10
 */
public interface DiscussbokebangdanService extends IService<DiscussbokebangdanEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussbokebangdanView> selectListView(Wrapper<DiscussbokebangdanEntity> wrapper);
   	
   	DiscussbokebangdanView selectView(@Param("ew") Wrapper<DiscussbokebangdanEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussbokebangdanEntity> wrapper);
   	

}

