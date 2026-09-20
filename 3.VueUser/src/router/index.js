import { createRouter, createWebHashHistory } from 'vue-router'
import index from '../views'
import home from '../views/pages/home.vue'
import login from '../views/pages/login.vue'
import yonghuList from '@/views/pages/yonghu/list'
import yonghuDetail from '@/views/pages/yonghu/formModel'
import yonghuAdd from '@/views/pages/yonghu/formAdd'
import yonghuRegister from '@/views/pages/yonghu/register'
import yonghuCenter from '@/views/pages/yonghu/center'
import newsList from '@/views/pages/news/list'
import bokefenleiList from '@/views/pages/bokefenlei/list'
import bokefenleiDetail from '@/views/pages/bokefenlei/formModel'
import bokefenleiAdd from '@/views/pages/bokefenlei/formAdd'
import bokebangdanList from '@/views/pages/bokebangdan/list'
import bokebangdanDetail from '@/views/pages/bokebangdan/formModel'
import bokebangdanAdd from '@/views/pages/bokebangdan/formAdd'
import storeupList from '@/views/pages/storeup/list'

const routes = [{
		path: '/',
		redirect: '/index/home'
	},
	{
		path: '/index',
		component: index,
		children: [{
			path: 'home',
			component: home
		}
		, {
			path: 'yonghuList',
			component: yonghuList
		}, {
			path: 'yonghuDetail',
			component: yonghuDetail
		}, {
			path: 'yonghuAdd',
			component: yonghuAdd
		}
		, {
			path: 'yonghuCenter',
			component: yonghuCenter
		}
		, {
			path: 'newsList',
			component: newsList
		}
		, {
			path: 'bokefenleiList',
			component: bokefenleiList
		}, {
			path: 'bokefenleiDetail',
			component: bokefenleiDetail
		}, {
			path: 'bokefenleiAdd',
			component: bokefenleiAdd
		}
		, {
			path: 'bokebangdanList',
			component: bokebangdanList
		}, {
			path: 'bokebangdanDetail',
			component: bokebangdanDetail
		}, {
			path: 'bokebangdanAdd',
			component: bokebangdanAdd
		}
		, {
			path: 'storeupList',
			component: storeupList
		}
		]
	},
	{
		path: '/login',
		component: login
	}
	,{
		path: '/yonghuRegister',
		component: yonghuRegister
	}
]

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes
})

export default router
