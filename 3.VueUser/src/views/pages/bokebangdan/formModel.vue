<template>
	<div class="app-contain" :style='{"padding":"20px 20px","margin":"20px auto 40px","alignItems":"flex-start","flexWrap":"wrap","background":"none","display":"flex","width":"1200px","position":"relative","justifyContent":"space-between"}'>
		<div class="back_view">
			<el-button class="back_btn" @click="backClick" type="primary">返回</el-button>
		</div>
		<div class="bread_view">
			<el-breadcrumb separator="/" class="breadcrumb">
				<el-breadcrumb-item class="first_breadcrumb" :to="{ path: '/' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item class="second_breadcrumb" v-for="(item,index) in breadList" :key="index">{{item.name}}</el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div class="detail_view">
			<div class="swiper_view">
				<mySwiper :data="bannerList" :type="3"
				:loop="false"
				:navigation="false"
				:pagination="true"
				:paginationType="1"
				:scrollbar="false"
				:slidesPerView="1"
				:spaceBetween="20"
				:autoHeight="false"
				:centeredSlides="false"
				:freeMode="false"
				:effectType="1"
				:direction="horizontal"
				:autoplay="true"
				:slidesPerColumn="1">
				<template #default="scope">
					<img :style='{"objectFit":"contain","width":"100%","height":"480px"}' :src="scope.row?$config.url + scope.row:''">
				</template>
			</mySwiper>
			</div>
			<div class="thumbs_view">
				<div class="zan" v-if="!crazilyType&&!thumbsupType" @click="thumbsupOrCrazilyClick(21)">
					<i class="iconfont icon-thumb-up-line2"></i>
					<span>赞({{detail.thumbsupnum}})</span>
				</div>
				<div class="zan" v-if="!crazilyType&&!thumbsupType" @click="thumbsupOrCrazilyClick(22)">
					<i class="iconfont icon-thumb-down-line2"></i>
					<span>踩({{detail.crazilynum}})</span>
				</div>
				<div class="zan" v-if="thumbsupType" @click="cancelThumbsupOrCrazilyClick(21)">
					<i class="iconfont iconfontActive icon-thumb-up-fill3"></i>
					<span class="textActive">取消赞({{detail.thumbsupnum}})</span>
				</div>
				<div class="zan" v-if="crazilyType" @click="cancelThumbsupOrCrazilyClick(22)">
					<i class="iconfont iconfontActive icon-thumb-down-fill2"></i>
					<span class="textActive">取消踩({{detail.crazilynum}})</span>
				</div>
			</div>
			
			<div class="info_view">
				<div class="title_view">
					<div class="detail_title">
						{{detail.bokefenlei}}
					</div>
					<div class="follow" v-if="!collectType" @click="collectClick(1)">
						<i class="iconfont icon-likeline1"></i>
						<span>收藏</span>
					</div>
					<div class="follow" v-if="collectType" @click="collectClick(-1)">
						<i class="iconfont iconfontActive icon-likefill1"></i>
						<span class="textActive">取消收藏</span>
					</div>
				</div>
				<div class="info_item">
					<div class="info_label">博客简介</div>
					<div  class="info_text" >{{detail.bokejianjie}}</div>
				</div>
				<div class="info_item">
					<div class="info_label">文章</div>
					<div  class="info_text" >{{detail.wenzhang}}</div>
				</div>
				<div class="info_item">
					<div class="info_label">用户账号</div>
					<div  class="info_text" >{{detail.yonghuzhanghao}}</div>
				</div>
				<div class="info_item">
					<div class="info_label">用户姓名</div>
					<div  class="info_text" >{{detail.yonghuxingming}}</div>
				</div>
				<div class="info_item">
					<div class="info_label">点击次数</div>
					<div  class="info_text" >{{detail.clicknum}}</div>
				</div>
				<div class="info_item">
					<div class="info_label">收藏数量</div>
					<div  class="info_text" >{{detail.storeupnum}}</div>
				</div>
				<div class="btn_view">
					<el-button v-if="centerType&&(detail.ispay=='未支付'||!detail.ispay)&&btnFrontAuth('bokebangdan','支付')" class="approval_btn" @click="payClick">支付</el-button>
					<el-button class="edit_btn" v-if="centerType&&btnAuth('bokebangdan','修改')" type="primary" @click="editClick">修改</el-button>
					<el-button class="del_btn" v-if="centerType&&btnAuth('bokebangdan','删除')" type="danger" @click="delClick">删除</el-button>
				</div>
			</div>
		</div>
		<div class="video_view">
			<video :src="$config.url + detail.shipin" controls autoplay></video>
		</div>
		<el-tabs type="border-card" v-model="activeName" class="tabs_view">
			<el-tab-pane label="评论" name="commentActive">
				<div class="my_comment_view">
					<el-form ref="commentFormRef" :model="commentForm" class="my_comment_form"
						:rules="commentRules">
						<el-form-item prop="content">
							<el-input class="comment_inp" v-model="commentForm.content" type="textarea"
								placeholder="请输入评论内容"></el-input>
						</el-form-item>
					</el-form>
					<div class="comment_btn">
						<el-button class="add_btn" type="primary" @click="commentSave">立即评论</el-button>
						<el-button class="reset_btn" @click="resetForm">重置</el-button>
					</div>
				</div>
				<div class="comment_list">
					<div class="comment" v-for="(item,index) in commentList" :key="index">
						<div class="comment_top">
							<div class="comment_user">
								<div class="comment_user_img">
									<img :src="item.avatarurl?$config.url + item.avatarurl:'../../../assets/avatar.png'" alt="">
								</div>
								<div class="comment_user_info">
									{{item.nickname}}
								</div>
							</div>
							<div class="comment_time">{{item.addtime}}</div>
						</div>
						<div class="comment_bottom">
							<div class="comment_content">{{item.content}}</div>
							<div class="comment_reply" v-if="item.reply">
								回复：{{item.reply}}
							</div>
						</div>
					</div>
				</div>
				<el-pagination
					background 
					:layout="layouts.join(',')"
					:total="commentTotal" 
					:page-size="commentQuery.limit"
					prev-text="上一页"
					next-text="下一页"
					:hide-on-single-page="false"
					:style='{"border":"0px solid #eee","padding":"4px 0","margin":"10px 0 20px","whiteSpace":"nowrap","color":"#333","textAlign":"center","flexWrap":"wrap","background":"none","display":"flex","width":"100%","fontWeight":"500","justifyContent":"center"}'
					@size-change="commentSizeChange"
					@current-change="commentCurrentChange" 
					@prev-click="commentPrevClick"
					@next-click="commentNextClick"  />
			</el-tab-pane>
		</el-tabs>
	</div>
</template>
<script setup>
	import axios from 'axios'
	import {
		ref,
		getCurrentInstance,
		watch,
		onUnmounted,
		onMounted,
		nextTick,
		computed
	} from 'vue';
	import {
		ElMessageBox
	} from 'element-plus'
	import {
		useRoute,
		useRouter
	} from 'vue-router';
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const route = useRoute()
	const router = useRouter()
	//基础信息
	const tableName = 'bokebangdan'
	const formName = '博客榜单'
	//基础信息
	const breadList = ref([{
		name: formName
	}])
	//权限验证
	const btnAuth = (e,a)=>{
		if(centerType.value){
			return context?.$toolUtil.isBackAuth(e,a)
		}else{
			return context?.$toolUtil.isAuth(e,a)
		}
	}
	//查看权限验证
	const btnFrontAuth = (e,a)=>{
		if(centerType.value){
			return context?.$toolUtil.isBackAuth(e,a)
		}else{
			return context?.$toolUtil.isFrontAuth(e,a)
		}
	}
	// 返回
	const backClick = () =>{
		history.back()
	}
	// 轮播图
	const bannerList = ref([])
	// 详情
	const title = ref('')
	const detail = ref({})
    const activeName = ref('first')
	const getDetail = () => {
		context?.$http({
			url: `${tableName}/detail/${route.query.id}`,
			method: 'get'
		}).then(res => {
			title.value = res.data.data.bokefenlei
			bannerList.value = res.data.data.tupian?res.data.data.tupian.split(','):[]
			detail.value = res.data.data
		})
	}
	// 下载文件
	const downClick = (file) => {
		if(!file){
			context?.$toolUtil.message('文件不存在','error')
		}
		let arr = file.replace(new RegExp('file/', "g"), "")
		axios.get((location.href.split(context?.$config.name).length>1 ? location.href.split(context?.$config.name)[0] :'') + context?.$config.name + '/file/download?fileName=' + arr, {
			headers: {
				token: context?.$toolUtil.storageGet('frontToken')
			},
			responseType: "blob"
		}).then(({
			data
		}) => {
			const binaryData = [];
			binaryData.push(data);
			const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
				type: 'application/pdf;chartset=UTF-8'
			}))
			const a = document.createElement('a')
			a.href = objectUrl
			a.download = arr
			// a.click()
			// 下面这个写法兼容火狐
			a.dispatchEvent(new MouseEvent('click', {
				bubbles: true,
				cancelable: true,
				view: window
			}))
			window.URL.revokeObjectURL(data)
		})
	}
	// 判断是否从个人中心跳转
	const centerType = ref(false)
	const init = () => {
		if(route.query.centerType){
			centerType.value = true
		}
		getDetail()
		// 赞踩状态
		getThumbsupOrCrazily()
		// 收藏
		getCollect()
		// 评论
		getCommentList()
	}
	// 赞or踩
	const thumbsupType = ref(false)
	const crazilyType = ref(false)
	const thumbsupOrCrazilyInfo = ref({})
	// 获取赞踩状态
	const getThumbsupOrCrazily = () => {
		if (context?.$toolUtil.storageGet('frontToken')) {
			context?.$http({
				url: 'storeup/list',
				method: 'get',
				params: {
					page: 1,
					limit: 1,
					type: 21,
					refid: route.query.id,
					tablename: tableName,
					userid: context?.$toolUtil.storageGet('userid')
				}
			}).then(res => {
				if (res.data.data.list.length) {
					thumbsupType.value = true
					thumbsupOrCrazilyInfo.value = res.data.data.list[0]
				}else {
					thumbsupType.value = false
					thumbsupOrCrazilyInfo.value = {}
				}
			})
			context?.$http({
				url: 'storeup/list',
				method: 'get',
				params: {
					page: 1,
					limit: 1,
					type: 22,
					refid: route.query.id,
					tablename: tableName,
					userid: context?.$toolUtil.storageGet('userid')
				}
			}).then(res => {
				if (res.data.data.list.length) {
					crazilyType.value = true
					thumbsupOrCrazilyInfo.value = res.data.data.list[0]
				}else {
					crazilyType.value = false
					thumbsupOrCrazilyInfo.value = {}
				}
			})
		}
	}
	// 赞踩按钮
	const thumbsupOrCrazilyClick = (type) => {
		let params = {
			name: title.value,
			picture: bannerList.value[0],
			refid: detail.value.id,
			type: type,
			tablename: tableName,
			userid: context?.$toolUtil.storageGet('userid')
		}
		context?.$http({
			url: 'storeup/add',
			method: 'post',
			data: params
		}).then(res => {
			if (type == 21) detail.value.thumbsupnum += 1
			if (type == 22) detail.value.crazilynum += 1
			context?.$http({
				url: `${tableName}/update`,
				method: 'post',
				data: detail.value
			})
			getThumbsupOrCrazily()
			context?.$toolUtil.message('操作成功', 'success')
		})
	}
	//取消赞踩按钮
	const cancelThumbsupOrCrazilyClick = (type) => {
		let ids = []
		ids.push(thumbsupOrCrazilyInfo.value.id)
		context?.$http({
			url: 'storeup/delete',
			method: 'post',
			data: ids
		}).then(res => {
			if (type == 21) detail.value.thumbsupnum -= 1
			if (type == 22) detail.value.crazilynum -= 1
			context?.$http({
				url: `${tableName}/update`,
				method: 'post',
				data: detail.value
			})
			thumbsupType.value = false
			crazilyType.value = false
			thumbsupOrCrazilyInfo.value = {}
			getThumbsupOrCrazily()
			context?.$toolUtil.message('取消成功', 'success')
		})
	}
	// 收藏
	const collectType = ref(false)
	const collectInfo = ref({})
	const getCollect = () => {
		if (context?.$toolUtil.storageGet('frontToken')) {
			context?.$http({
				url: 'storeup/list',
				method: 'get',
				params: {
					page: 1,
					limit: 1,
					type: 1,
					refid: route.query.id,
					tablename: tableName,
					userid: context?.$toolUtil.storageGet('userid')
				}
			}).then(res => {
				if (res.data.data.list.length) {
					collectType.value = true
					collectInfo.value = res.data.data.list[0]
				}else{
					collectType.value = false
					collectInfo.value = {}
				}
			})
		}
	}
	// 收藏按钮
	const collectClick = (type) => {
		if (type == 1 && !collectType.value) {
			let params = {
				name: title.value,
				picture: bannerList.value[0],
				inteltype: detail.value.bokefenlei,
				refid: detail.value.id,
				type: type,
				tablename: tableName,
				userid: context?.$toolUtil.storageGet('userid')
			}
			context?.$http({
				url: 'storeup/add',
				method: 'post',
				data: params
			}).then(res => {
				detail.value.storeupnum += 1
				context?.$http({
					url: `${tableName}/update`,
					method: 'post',
					data: detail.value
				})
				collectType.value = true
				getCollect()
				context?.$toolUtil.message('收藏成功', 'success')
			})
		}
		else if (type == -1 && collectType.value) {
			let ids = []
			ids.push(collectInfo.value.id)
			context?.$http({
				url: 'storeup/delete',
				method: 'post',
				data: ids
			}).then(res => {
				detail.value.storeupnum -= 1
				context?.$http({
					url: `${tableName}/update`,
					method: 'post',
					data: detail.value
				})
				collectInfo.value = {}
				collectType.value = false
				context?.$toolUtil.message('取消成功', 'success')
			})
		}
	}
	//评论
	const commentForm = ref({
		content: '',
		refid: route.query.id,
		userid: context?.$toolUtil.storageGet('userid'),
		nickname: context?.$toolUtil.storageGet('frontName'),
		avatarurl: context?.$toolUtil.storageGet('headportrait') ? context?.$toolUtil.storageGet('headportrait') : ''
	})
	const commentRules = ref({
		content: [{
			required: true,
			message: '请输入',
			trigger: 'blur'
		}, ]
	})
	const commentQuery = ref({
		page: 1,
		limit: 10,
		refid: route.query.id
	})
	const layouts = ref(["total","prev","pager","next","sizes"])
	const commentList = ref([])
	const commentTotal = ref(0)
	const commentFormRef = ref(null)
	const commentSizeChange = (size) =>{
		commentQuery.value.limit = size
		getCommentList()
	}
	const commentCurrentChange = (page) =>{
		commentQuery.value.page = page
		getCommentList()
	}
	const commentPrevClick = () =>{
		commentQuery.value.page = commentQuery.value.page - 1
		getCommentList()
	}
	const commentNextClick = () =>{
		commentQuery.value.page = commentQuery.value.page + 1
		getCommentList()
	}
	const getCommentList = () => {
		context?.$http({
			url: `discuss${tableName}/list`,
			params: commentQuery.value,
			method: 'get'
		}).then(res => {
			commentList.value = res.data.data.list
			commentTotal.value = res.data.data.total
	
		})
	}
	//提交评论
	const commentSave = () => {
		let sensitiveWords = "";
		let sensitiveWordsArr = [];
		if(sensitiveWords) {
		    sensitiveWordsArr = sensitiveWords.split(",");
		}
		for(var i=0; i<sensitiveWordsArr.length; i++){
		    //全局替换
		    var reg = new RegExp(sensitiveWordsArr[i],"g");
		    //判断内容中是否包括敏感词
		    if (commentForm.value.content.indexOf(sensitiveWordsArr[i]) > -1) {
		        // 将敏感词替换为 **
		        commentForm.value.content = commentForm.value.content.replace(reg,"**");
		    }
		}
		commentFormRef.value.validate((valid) => {
			if (valid) {
				context?.$http({
					url: `discuss${tableName}/add`,
					method: 'post',
					data: commentForm.value
				}).then(res => {
					context?.$toolUtil.message('评论成功', 'success', () => {
						resetForm()
						getCommentList()
					})
				})
			}
		})
	}
	const resetForm = () => {
		commentFormRef.value.resetFields()
	}
	//修改
	const editClick = () => {
		router.push(`/index/${tableName}Add?id=${detail.value.id}&&type=edit`)
	}
	//删除
	const delClick = () => {
		ElMessageBox.confirm(`是否删除此${formName}？`, '提示', {
			confirmButtonText: '是',
			cancelButtonText: '否',
			type: 'warning',
		}).then(()=>{
			context?.$http({
				url: `${tableName}/delete`,
				method: 'post',
				data: [detail.value.id]
			}).then(res=>{
				context?.$toolUtil.message('删除成功','success',()=>{
					history.back()
				})
			})
			
		})
	}
	onMounted(()=>{
		init()
	})
</script>
<style lang="scss" scoped>
	// 返回盒子
	.back_view {
		border-radius: 0px;
		padding: 0 20px;
		margin: 0;
		background: none;
		display: inline-block;
		width: 100%;
		text-align: right;
		// 返回按钮
		.back_btn {
			border: 1px solid #eee;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 20px;
			color: #666;
			background: #fff;
			width: auto;
			font-size: 14px;
			height: 24px;
		}
		// 返回按钮-悬浮
		.back_btn:hover {
		}
	}
	// 面包屑盒子
	.bread_view {
		border-radius: 0px;
		padding: 0px 20px 10px;
		margin: 0px auto;
		background: none;
		width: calc(100% - 0px);
		border-color: #66666650;
		border-width: 0 0 1px;
		position: relative;
		border-style: solid;
		:deep(.breadcrumb) {
			font-size: 14px;
			line-height: 1;
			.el-breadcrumb__separator {
				margin: 0 9px;
				color: #333;
				font-weight: 500;
			}
			.first_breadcrumb {
				.el-breadcrumb__inner {
					color: #333;
					display: inline-block;
				}
			}
			.second_breadcrumb {
				.el-breadcrumb__inner {
					color: #333;
					display: inline-block;
				}
			}
		}
	}
	
	.detail_view{
		border-radius: 0;
		padding: 20px 0;
		background: none;
		display: flex;
		width: 100%;
		border-color: #27bacc30;
		border-width: 0px;
		justify-content: space-between;
		position: relative;
		border-style: solid;
		flex-wrap: wrap;
		// 轮播图
		.swiper_view {
			padding: 0;
			margin: 0;
			background: none;
			width: 480px;
			float: left;
			height: 480px;
		}
		
		// 赞踩盒子
		.thumbs_view {
			padding: 0;
			margin: 10px auto 0;
			display: flex;
			width: 100%;
			justify-content: center;
			align-items: center;
			order: 3;
			.zan {
				border: 1px solid #ddd;
				cursor: pointer;
				border-radius: 20px;
				padding: 4px 20px;
				margin: 0 20px;
				color: #999;
				background: #fff;
				display: flex;
				width: auto;
				align-items: center;
				box-sizing: border-box;
		
				.iconfont {
					padding: 0 10px 0 0;
					color: #666;
					font-size: 24px;
				}
				.iconfontActive {
					margin: 0 4px 0 0;
					color: #21a5be;
					font-size: 24px;
				}
				span {
					color: #666;
				}
				.textActive {
					color: #21a5be;
				}
			}
			.zan:hover {
			}
			.zan:active {
				transform: scale(0.8);
			}
		}
		// 文字区
		.info_view {
			border: 0px solid #eee;
			padding: 0 20px;
			margin: 20px 0 20px;
			background: none;
			width: calc(100% - 500px);
			box-sizing: border-box;
			order: 2;
		
			.title_view {
				padding: 0 0 0 16px;
				margin: 0 0 12px;
				background: none;
				display: flex;
				width: 100%;
				border-color: #66666650;
				border-width: 0 0 1px;
				line-height: 40px;
				align-items: center;
				border-style: solid;
		
				.detail_title {
					color: #333;
					font-weight: 600;
					font-size: 18px;
				}
				// 收藏盒子
				.follow {
					border: 0px solid #ffffff50;
					cursor: pointer;
					padding: 4px 10px;
					color: #333;
					display: flex;
					line-height: 1;
					right: 30px;
					border-radius: 4px;
					background: none;
					width: auto;
					justify-content: center;
					align-items: center;
					position: absolute;
					.iconfont {
						margin: 0 4px 0 0;
						color: #f60;
						font-size: 24px;
					}
					.iconfontActive {
						margin: 0 4px 0 0;
						color: #f60;
						font-size: 24px;
					}
					span {
						color: #f60;
						font-size: 15px;
					}
					.textActive {
						color: #f60;
						font-size: 16px;
					}
				}
				.follow:hover {
				}
				.follow:active {
					transform: scale(0.9);
				}
			}
		
			.info_item {
					border-radius: 0px;
					padding: 0px;
					margin: 0 2% 10px 0;
					background: none;
					display: inline-block;
					width: 30%;
					border-color: #eee;
					border-width: 0px;
					align-items: center;
					border-style: solid;
		
				.info_label {
					margin: 0 12px 10px 0;
					color: #666;
					font-weight: 600;
					width: auto;
				}
				.info_text {
				}
			}
			.btn_view {
				padding: 0;
				margin: 20px 0;
				display: flex;
				flex-wrap: wrap;
				// 修改-按钮
				.edit_btn {
					border: none;
					padding: 0 10px;
					margin: 0 10px 10px 0;
					color: #fff;
					background: #1fc273;
					line-height: 32px;
					height: 32px;
				}
				// 悬浮
				.edit_btn:hover {
				}
				// 删除-按钮
				.del_btn {
					border: none;
					padding: 0 10px;
					margin: 0 10px 10px 0;
					color: #fff;
					background: #c21f30;
					line-height: 32px;
					height: 32px;
				}
				// 悬浮
				.del_btn:hover {
				}
			}
		}
	}
	
	//视频盒子
	.video_view {
		border: 0px solid #ddd;
		border-radius: 0px;
		padding: 0px;
		margin: 20px 0;
		background: #fff;
		width: 100%;
		order: 11;
	
		video {
			border: 0px solid #ddd;
			border-radius: 0px;
			padding: 0px;
			margin: 0px auto;
			outline: none;
			background: #f8f8f8;
			display: block;
			width: 100%;
		}
	}

	//底部盒子
	.tabs_view {
		border: 0px solid #ddd;
		border-radius: 0px;
		padding: 0 0px;
		box-shadow: none;
		margin: 20px auto;
		background: none;
		width: 100%;
		:deep(.el-tabs__header) {
			background: transparent;
			border: none;
		}
		// 头部
		:deep(.el-tabs__nav-scroll) {
			padding: 0px 0px;
			margin: 0;
			background: #fff;
			border-color: #ddd;
			border-width: 1px;
			border-style: solid;
			height: 46px;
			.el-tabs__nav {
				.el-tabs__item {
					border: 0;
					padding: 0 30px;
					margin: 0 10px;
					color: #333;
					font-weight: 500;
					display: inline-block;
					font-size: 14px;
					line-height: 46px;
					transition: all 0s;
					background: none;
					position: relative;
					list-style: none;
					text-align: center;
					min-width: 60px;
					height: 46px;
				}
				.el-tabs__item:hover {
					border: 0;
					border-radius: 0;
					color: #fff;
					background: #ff9900;
					line-height: 46px;
					height: 46px;
				}
				.is-active {
					border: 0;
					border-radius: 0;
					padding: 0 30px;
					color: #fff;
					background: #ff9900;
					line-height: 46px;
					text-align: center;
					min-width: 60px;
					height: 46px;
				}
			}
		}
		// 内容区
		:deep(.el-tabs__content) {
			border-radius: 0px;
			padding: 20px;
			color: #666;
			background: none;
			font-size: 14px;
			border-color: #eee;
			border-width: 0;
			border-style: solid;
		}
		//评论
		//我的评论
		.my_comment_view {
			border: 0px solid #eee;
			border-radius: 4px;
			box-shadow: none;
			padding: 0px;
			margin: 0px;
			background: none;
			width: 100%;
		
			.my_comment_form {
				border: 0px solid #eee;
				border-radius: 0px;
				padding: 0px;
				box-shadow: none;
				background: #fff;
				width: 100%;
				box-sizing: border-box;
				// 输入框
				:deep(.el-textarea__inner) {
					border: 1px solid #eee;
					border-radius: 0;
					padding: 12px;
					box-shadow: none;
					color: #333;
					width: 100%;
					font-size: 14px;
					min-height: 120px;
				}
			}
			// 按钮盒子
			.comment_btn {
				margin: 20px 0 0;
				display: flex;
				width: 100%;
				justify-content: center;
				align-items: center;
				// 评论按钮
				.add_btn {
					border: 0px solid #dc647060;
					border-radius: 0px;
					padding: 0 20px;
					margin: 0 20px 0 0;
					color: #fff;
					background: rgba(255, 153, 0,1);
					line-height: 36px;
					height: 40px;
				}
				// 悬浮
				.add_btn:hover {
				}
				// 重置按钮
				.reset_btn {
					border: 0px solid #ccc;
					border-radius: 0px;
					padding: 0 20px;
					color: #fff;
					background: rgba(136, 179, 1,.8);
					line-height: 36px;
					min-width: 100px;
					height: 40px;
				}
				// 悬浮
				.reset_btn:hover {
				}
			}
		}
		
		//评论列表
		.comment_list {
			border: 0px solid #eee;
			border-radius: 4px;
			padding: 0px;
			margin: 30px auto;
			background: none;
			display: flex;
			width: 100%;
			justify-content: space-between;
			flex-wrap: wrap;
		
			.comment {
				border: 1px solid #ddd;
				border-radius: 4px;
				padding: 0 0 10px;
				margin: 0 0 20px;
				background: #fff;
				width: 49%;
				box-sizing: border-box;
				.comment_top {
					border-radius: 4px;
					padding: 0 0 6px;
					background: #fff;
					display: flex;
					width: 100%;
					font-size: 14px;
					border-color: #c21f3090;
					border-width: 0 0 0px;
					justify-content: space-between;
					align-items: center;
					border-style: inset;
		
					.comment_user {
						border-radius: 4px;
						padding: 4px 30px 4px 10px;
						display: block;
						align-items: center;
						.comment_user_img {
							margin: 0 10px 0 0;
							font-size: 0;
							float: left;
		
							img {
								border-radius: 50%;
								width: 40px;
								height: 40px;
							}
						}
						.comment_user_info {
							color: #333;
							font-weight: 600;
							font-size: 14px;
							line-height: 40px;
							float: left;
						}
					}
					.comment_time{
						border: 0px solid #eee;
						border-radius: 4px;
						padding: 0px 20px;
						color: #999;
						font-size: 14px;
						float: right;
					}
				}
		
				.comment_bottom {
					border: 0px solid #eee;
					border-radius: 4px;
					padding: 0px 10px;
					margin: 10px 0 0;
					flex-direction: column;
					display: flex;
					width: 100%;
					align-items: flex-start;
					.comment_content {
						color: #666;
						font-weight: 600;
						font-size: 14px;
					}
					.comment_reply {
						padding: 10px 0 0;
						flex-direction: column;
						color: #999;
						text-indent: 2rem;
						display: flex;
						width: 100%;
						font-size: 14px;
						align-items: flex-start;
					}
				}
			}
		}
		// 分页器
		.el-pagination {
			// 总页码
			:deep(.el-pagination__total) {
				margin: 0 10px 0 0;
				color: #666;
				font-weight: 400;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 24px;
				height: 24px;
			}
			// 上一页
			:deep(.btn-prev) {
				border: 0px solid #ddd;
				border-radius: 4px;
				padding: 0 4px;
				margin: 0 2px;
				color: #666;
				background: none;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 24px;
				min-width: 24px;
				height: 24px;
			}
			// 下一页
			:deep(.btn-next) {
				border: 0px solid #ddd;
				border-radius: 4px;
				padding: 0 4px;
				margin: 0 2px;
				color: #666;
				background: none;
				display: inline-block;
				vertical-align: top;
				font-size: 14px;
				line-height: 24px;
				min-width: 24px;
				height: 24px;
			}
			// 上一页禁用
			:deep(.btn-prev:disabled) {
				border: 0px solid #ddd;
				cursor: not-allowed;
				padding: 0 4px;
				margin: 0 2px;
				color: #C0C4CC;
				display: inline-block;
				vertical-align: top;
				font-size: 14px;
				line-height: 24px;
				border-radius: 4px;
				background: none;
				min-width: 24px;
				height: 24px;
			}
			// 下一页禁用
			:deep(.btn-next:disabled) {
				border: 0px solid #ddd;
				cursor: not-allowed;
				padding: 0 4px;
				margin: 0 2px;
				color: #C0C4CC;
				display: inline-block;
				vertical-align: top;
				font-size: 14px;
				line-height: 24px;
				border-radius: 4px;
				background: none;
				min-width: 24px;
				height: 24px;
			}
			// 页码
			:deep(.el-pager) {
				padding: 0;
				margin: 0;
				display: inline-block;
				vertical-align: top;
				// 数字
				.number {
					cursor: pointer;
					padding: 0 4px;
					margin: 0 5px;
					color: #666;
					display: inline-block;
					vertical-align: top;
					font-size: 13px;
					line-height: 24px;
					border-radius: 0;
					background: #fff;
					text-align: center;
					min-width: 24px;
					height: 24px;
				}
				// 数字悬浮
				.number:hover {
					cursor: pointer;
					padding: 0 4px;
					margin: 0 5px;
					color: #fff;
					display: inline-block;
					vertical-align: top;
					font-size: 13px;
					line-height: 24px;
					border-radius: 0;
					background: #ff9900;
					text-align: center;
					min-width: 24px;
					height: 24px;
				}
				// 选中
				.number.is-active {
					cursor: default;
					padding: 0 4px;
					margin: 0 5px;
					color: #fff;
					display: inline-block;
					vertical-align: top;
					font-size: 13px;
					line-height: 24px;
					border-radius: 0;
					background: #ff9900;
					text-align: center;
					min-width: 24px;
					height: 24px;
				}
			}
			// sizes
			:deep(.el-pagination__sizes) {
				box-shadow: none;
				margin: 0 0 0 5px;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 24px;
				height: 24px;
				.el-select {
					border: 0px solid #DCDFE6;
					cursor: pointer;
					border-radius: 2px;
					padding: 0;
					color: #606266;
					background: #fff;
					display: inline-block;
					width: 100%;
					font-size: 13px;
					line-height: 24px;
					text-align: center;
					height: 24px;
				//去掉默认样式
				.select-trigger{
					height: 100%;
					.el-input{
						height: 100%;
						.el-input__wrapper{
							border: none;
							box-shadow: none;
							background: none;
							border-radius: 0;
							height: 100%;
							padding: 0;
						}
						.is-focus {
							box-shadow: none !important;
						}
					}
				}
				}
			}
			// 跳页
			:deep(.el-pagination__jump) {
				margin: 0 0 0 24px;
				color: #606266;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				height: 28px;
				// 输入框
				.el-input {
					border: 1px solid #DCDFE6;
					cursor: pointer;
					padding: 0 3px;
					margin: 0 6px;
					color: #606266;
					display: inline-block;
					font-size: 14px;
					line-height: 24px;
					border-radius: 3px;
					outline: 0;
					background: #FFF;
					width: 38px;
					text-align: center;
					height: 24px;
					//去掉默认样式
					.el-input__wrapper{
						border: none;
						box-shadow: none;
						background: none;
						border-radius: 0;
						height: 100%;
						padding: 0;
					}
					.is-focus {
						box-shadow: none !important;
					}
				}
			}
		}
	}
	


</style>