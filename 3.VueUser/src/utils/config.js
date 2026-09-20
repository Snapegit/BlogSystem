const config = {
    get() {
        return {
            url : process.env.VUE_APP_BASE_API_URL + process.env.VUE_APP_BASE_API + '/',
            name: process.env.VUE_APP_BASE_API,
			menuList:[
				{
					name: '博客榜单管理',
					icon: '${frontMenu.fontClass}',
					child:[
						{
							name:'博客榜单',
							url:'/index/bokebangdanList'
						},
					]
				},
				{
					name: '公告信息',
					icon: '${frontMenu.fontClass}',
					child:[
						{
							name:'公告信息',
							url:'/index/newsList'
						},
					]
				},
			]
        }
    },
    getProjectName(){
        return {
            projectName: "基于vue与SpringBoot博客系统 "
        } 
    }
}
export default config
