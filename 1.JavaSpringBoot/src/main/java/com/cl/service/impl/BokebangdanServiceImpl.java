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


import com.cl.dao.BokebangdanDao;
import com.cl.entity.BokebangdanEntity;
import com.cl.service.BokebangdanService;
import com.cl.entity.view.BokebangdanView;

@Service("bokebangdanService")
public class BokebangdanServiceImpl extends ServiceImpl<BokebangdanDao, BokebangdanEntity> implements BokebangdanService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BokebangdanEntity> page = this.selectPage(
                new Query<BokebangdanEntity>(params).getPage(),
                new EntityWrapper<BokebangdanEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<BokebangdanEntity> wrapper) {
		  Page<BokebangdanView> page =new Query<BokebangdanView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<BokebangdanView> selectListView(Wrapper<BokebangdanEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public BokebangdanView selectView(Wrapper<BokebangdanEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
