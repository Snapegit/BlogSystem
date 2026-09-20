package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.BokebangdanEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.BokebangdanView;


/**
 * 博客榜单
 *
 * @author 
 * @email 
 * @date 2024-03-04 11:31:10
 */
public interface BokebangdanService extends IService<BokebangdanEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<BokebangdanView> selectListView(Wrapper<BokebangdanEntity> wrapper);
   	
   	BokebangdanView selectView(@Param("ew") Wrapper<BokebangdanEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<BokebangdanEntity> wrapper);
   	

}

