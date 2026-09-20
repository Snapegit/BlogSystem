package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.BokefenleiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.BokefenleiView;


/**
 * 博客分类
 *
 * @author 
 * @email 
 * @date 2024-03-04 11:31:10
 */
public interface BokefenleiService extends IService<BokefenleiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<BokefenleiView> selectListView(Wrapper<BokefenleiEntity> wrapper);
   	
   	BokefenleiView selectView(@Param("ew") Wrapper<BokefenleiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<BokefenleiEntity> wrapper);
   	

}

