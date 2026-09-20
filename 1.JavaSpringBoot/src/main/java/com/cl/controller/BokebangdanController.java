package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.cl.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;

import com.cl.entity.BokebangdanEntity;
import com.cl.entity.view.BokebangdanView;

import com.cl.service.BokebangdanService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;
import com.cl.service.StoreupService;
import com.cl.entity.StoreupEntity;

/**
 * 博客榜单
 * 后端接口
 * @author 
 * @email 
 * @date 2024-03-04 11:31:10
 */
@RestController
@RequestMapping("/bokebangdan")
public class BokebangdanController {
    @Autowired
    private BokebangdanService bokebangdanService;

    @Autowired
    private StoreupService storeupService;


    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,BokebangdanEntity bokebangdan,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			bokebangdan.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<BokebangdanEntity> ew = new EntityWrapper<BokebangdanEntity>();

		PageUtils page = bokebangdanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, bokebangdan), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,BokebangdanEntity bokebangdan, 
		HttpServletRequest request){
        EntityWrapper<BokebangdanEntity> ew = new EntityWrapper<BokebangdanEntity>();

		PageUtils page = bokebangdanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, bokebangdan), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( BokebangdanEntity bokebangdan){
       	EntityWrapper<BokebangdanEntity> ew = new EntityWrapper<BokebangdanEntity>();
      	ew.allEq(MPUtil.allEQMapPre( bokebangdan, "bokebangdan")); 
        return R.ok().put("data", bokebangdanService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(BokebangdanEntity bokebangdan){
        EntityWrapper< BokebangdanEntity> ew = new EntityWrapper< BokebangdanEntity>();
 		ew.allEq(MPUtil.allEQMapPre( bokebangdan, "bokebangdan")); 
		BokebangdanView bokebangdanView =  bokebangdanService.selectView(ew);
		return R.ok("查询博客榜单成功").put("data", bokebangdanView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        BokebangdanEntity bokebangdan = bokebangdanService.selectById(id);
		bokebangdan.setClicknum(bokebangdan.getClicknum()+1);
		bokebangdan.setClicktime(new Date());
		bokebangdanService.updateById(bokebangdan);
		bokebangdan = bokebangdanService.selectView(new EntityWrapper<BokebangdanEntity>().eq("id", id));
        return R.ok().put("data", bokebangdan);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        BokebangdanEntity bokebangdan = bokebangdanService.selectById(id);
		bokebangdan.setClicknum(bokebangdan.getClicknum()+1);
		bokebangdan.setClicktime(new Date());
		bokebangdanService.updateById(bokebangdan);
		bokebangdan = bokebangdanService.selectView(new EntityWrapper<BokebangdanEntity>().eq("id", id));
        return R.ok().put("data", bokebangdan);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        BokebangdanEntity bokebangdan = bokebangdanService.selectById(id);
        if(type.equals("1")) {
        	bokebangdan.setThumbsupnum(bokebangdan.getThumbsupnum()+1);
        } else {
        	bokebangdan.setCrazilynum(bokebangdan.getCrazilynum()+1);
        }
        bokebangdanService.updateById(bokebangdan);
        return R.ok("投票成功");
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BokebangdanEntity bokebangdan, HttpServletRequest request){
    	bokebangdan.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(bokebangdan);
        bokebangdanService.insert(bokebangdan);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody BokebangdanEntity bokebangdan, HttpServletRequest request){
    	bokebangdan.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(bokebangdan);
        bokebangdanService.insert(bokebangdan);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody BokebangdanEntity bokebangdan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(bokebangdan);
        bokebangdanService.updateById(bokebangdan);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        bokebangdanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,BokebangdanEntity bokebangdan, HttpServletRequest request,String pre){
        EntityWrapper<BokebangdanEntity> ew = new EntityWrapper<BokebangdanEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicknum");
        params.put("order", "desc");
		PageUtils page = bokebangdanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, bokebangdan), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 按收藏推荐
     */
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,BokebangdanEntity bokebangdan, HttpServletRequest request){
        String userId = request.getSession().getAttribute("userId").toString();
        String inteltypeColumn = "bokefenlei";
        List<StoreupEntity> storeups = storeupService.selectList(new EntityWrapper<StoreupEntity>().eq("type", 1).eq("userid", userId).eq("tablename", "bokebangdan").orderBy("addtime", false));
        List<String> inteltypes = new ArrayList<String>();
        Integer limit = params.get("limit")==null?10:Integer.parseInt(params.get("limit").toString());
        List<BokebangdanEntity> bokebangdanList = new ArrayList<BokebangdanEntity>();
        //去重
        if(storeups!=null && storeups.size()>0) {
            for(StoreupEntity s : storeups) {
                bokebangdanList.addAll(bokebangdanService.selectList(new EntityWrapper<BokebangdanEntity>().eq(inteltypeColumn, s.getInteltype())));
            }
        }
        EntityWrapper<BokebangdanEntity> ew = new EntityWrapper<BokebangdanEntity>();
        params.put("sort", "id");
        params.put("order", "desc");
        PageUtils page = bokebangdanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, bokebangdan), params), params));
        List<BokebangdanEntity> pageList = (List<BokebangdanEntity>)page.getList();
        if(bokebangdanList.size()<limit) {
            int toAddNum = (limit-bokebangdanList.size())<=pageList.size()?(limit-bokebangdanList.size()):pageList.size();
            for(BokebangdanEntity o1 : pageList) {
                boolean addFlag = true;
                for(BokebangdanEntity o2 : bokebangdanList) {
                    if(o1.getId().intValue()==o2.getId().intValue()) {
                        addFlag = false;
                        break;
                    }
                }
                if(addFlag) {
                    bokebangdanList.add(o1);
                    if(--toAddNum==0) break;
                }
            }
        } else if(bokebangdanList.size()>limit) {
            bokebangdanList = bokebangdanList.subList(0, limit);
        }
        page.setList(bokebangdanList);
        return R.ok().put("data", page);
    }







}
