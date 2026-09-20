package com.cl.entity.view;

import com.cl.entity.DiscussbokebangdanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.cl.utils.EncryptUtil;
 

/**
 * 博客榜单评论表
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2024-03-04 11:31:10
 */
@TableName("discussbokebangdan")
public class DiscussbokebangdanView  extends DiscussbokebangdanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public DiscussbokebangdanView(){
	}
 
 	public DiscussbokebangdanView(DiscussbokebangdanEntity discussbokebangdanEntity){
 	try {
			BeanUtils.copyProperties(this, discussbokebangdanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
