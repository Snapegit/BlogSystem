package com.cl.entity.view;

import com.cl.entity.BokebangdanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.cl.utils.EncryptUtil;
 

/**
 * 博客榜单
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2024-03-04 11:31:10
 */
@TableName("bokebangdan")
public class BokebangdanView  extends BokebangdanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public BokebangdanView(){
	}
 
 	public BokebangdanView(BokebangdanEntity bokebangdanEntity){
 	try {
			BeanUtils.copyProperties(this, bokebangdanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
