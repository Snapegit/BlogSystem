package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.DiscussbokebangdanDao;
import com.cl.entity.DiscussbokebangdanEntity;
import com.cl.service.DiscussbokebangdanService;
import com.cl.entity.view.DiscussbokebangdanView;

@Service("discussbokebangdanService")
public class DiscussbokebangdanServiceImpl extends ServiceImpl<DiscussbokebangdanDao, DiscussbokebangdanEntity> implements DiscussbokebangdanService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussbokebangdanEntity> page = this.selectPage(
                new Query<DiscussbokebangdanEntity>(params).getPage(),
                new EntityWrapper<DiscussbokebangdanEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussbokebangdanEntity> wrapper) {
		  Page<DiscussbokebangdanView> page =new Query<DiscussbokebangdanView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<DiscussbokebangdanView> selectListView(Wrapper<DiscussbokebangdanEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussbokebangdanView selectView(Wrapper<DiscussbokebangdanEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
