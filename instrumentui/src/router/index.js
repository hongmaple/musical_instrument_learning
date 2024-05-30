import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: 路由配置项
 *
 * hidden: true                     // 当设置 true 的时候该路由不会再侧边栏出现 如401，login等页面，或者如一些编辑页面/edit/1
 * alwaysShow: true                 // 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
 *                                  // 只有一个时，会将那个子路由当做根路由显示在侧边栏--如引导页面
 *                                  // 若你想不管路由下面的 children 声明的个数都显示你的根路由
 *                                  // 你可以设置 alwaysShow: true，这样它就会忽略之前定义的规则，一直显示根路由
 * redirect: noRedirect             // 当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
 * name:'router-name'               // 设定路由的名字，一定要填写不然使用<keep-alive>时会出现各种问题
 * query: '{"id": 1, "name": "ry"}' // 访问路由的默认传递参数
 * meta : {
    noCache: true                   // 如果设置为true，则不会被 <keep-alive> 缓存(默认 false)
    title: 'title'                  // 设置该路由在侧边栏和面包屑中展示的名字
    icon: 'svg-name'                // 设置该路由的图标，对应路径src/assets/icons/svg
    breadcrumb: false               // 如果设置为false，则不会在breadcrumb面包屑中显示
    activeMenu: '/system/user'      // 当路由设置了该属性，则会高亮相对应的侧边栏。
  }
 */

// 公共路由
export const constantRoutes = [{
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [{
      path: '/redirect/:path(.*)',
      component: (resolve) => require(['@/views/redirect'], resolve)
    }]
  },
  {
    path: '/cms',
    component: (resolve) => require(['@/views/cms/index'], resolve),
    hidden: true,
    redirect: '/cms/main/cmsIndex',
    children: [
      {
        path: 'main',
        component: (resolve) => require(['@/views/cms/main'], resolve),
        hidden: true,
        children: [
          {
            path: 'cmsIndex',
            component: (resolve) => require(['@/views/cms/components/cmsIndex'], resolve),
            name: 'cmsIndex',
            meta: {
              title: '首页',
            }
          },
          {
            path: 'cmsForumIndex',
            component: (resolve) => require(['@/views/cms/forum/cmsForumIndex'], resolve),
            name: 'cmsForumIndex',
            meta: {
              title: '论坛',
            }
          },
          {
            path: 'essay',
            component: (resolve) => require(['@/views/cms/components/cmsEssay'], resolve),
            name: 'essay',
            meta: {
              title: '大家的随笔',
            }
          },
          {
            path: 'message',
            component: (resolve) => require(['@/views/cms/components/cmsMessage'], resolve),
            name: 'message',
            meta: {
              title: '留言',
            }
          },
          {
            path: 'blog',
            component: (resolve) => require(['@/views/cms/components/cmsBlog'], resolve),
            name: 'blog',
            meta: {
              title: '博客详情',
            }
          },
          {
            path: 'forum',
            component: (resolve) => require(['@/views/cms/forum/info'], resolve),
            name: 'forum',
            meta: {
              title: '论坛详情',
            }
          }
        ]
      },
      {
        path: 'doucument',
        component: (resolve) => require(['@/views/cms/components/cmsDoucument'], resolve),
        hidden: true,
        name: '文档',
      }
    ]
  },
  {
    path: '/cmsLogin',
    component: (resolve) => require(['@/views/cms/cmslogin'], resolve),
    hidden: true,
  },
  {
    path: '/cmsRegister',
    component: (resolve) => require(['@/views/cms/cmsRegister'], resolve),
    hidden: true
  },
  {
    path: '/admin',
    redirect: '/login',
    hidden: true
  },
  {
    path: '/login',
    component: (resolve) => require(['@/views/login'], resolve),
    hidden: true
  },
  {
    path: '/register',
    component: (resolve) => require(['@/views/register'], resolve),
    hidden: true
  },
  {
    path: '/teacherRegister',
    component: (resolve) => require(['@/views/teacherRegister'], resolve),
    hidden: true
  },
  {
    path: '/404',
    component: (resolve) => require(['@/views/error/404'], resolve),
    hidden: true
  },
  {
    path: '/401',
    component: (resolve) => require(['@/views/error/401'], resolve),
    hidden: true
  },
  {
    path: '',
    component: Layout,
    redirect: '/cms',
    children: [{
      path: 'index',
      component: (resolve) => require(['@/views/index_v2'], resolve),
      name: 'Index',
      meta: {
        title: '首页',
        icon: 'dashboard',
        affix: true
      }
    }]
  },
  {
    path: '/user',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [{
      path: 'profile',
      component: (resolve) => require(['@/views/system/user/profile/index'], resolve),
      name: 'Profile',
      meta: {
        title: '个人中心',
        icon: 'user'
      }
    }]
  },
  {
    path: '/system/user-auth',
    component: Layout,
    hidden: true,
    children: [{
      path: 'role/:userId(\\d+)',
      component: (resolve) => require(['@/views/system/user/authRole'], resolve),
      name: 'AuthRole',
      meta: {
        title: '分配角色',
        activeMenu: '/system/user'
      }
    }]
  },
  {
    path: '/system/role-auth',
    component: Layout,
    hidden: true,
    children: [{
      path: 'user/:roleId(\\d+)',
      component: (resolve) => require(['@/views/system/role/authUser'], resolve),
      name: 'AuthUser',
      meta: {
        title: '分配用户',
        activeMenu: '/system/role'
      }
    }]
  },
  {
    path: '/system/dict-data',
    component: Layout,
    hidden: true,
    children: [{
      path: 'index/:dictId(\\d+)',
      component: (resolve) => require(['@/views/system/dict/data'], resolve),
      name: 'Data',
      meta: {
        title: '字典数据',
        activeMenu: '/system/dict'
      }
    }]
  },
  {
    path: '/monitor/job-log',
    component: Layout,
    hidden: true,
    children: [{
      path: 'index',
      component: (resolve) => require(['@/views/monitor/job/log'], resolve),
      name: 'JobLog',
      meta: {
        title: '调度日志',
        activeMenu: '/monitor/job'
      }
    }]
  },
  {
    path: '/tool/gen-edit',
    component: Layout,
    hidden: true,
    children: [{
      path: 'index',
      component: (resolve) => require(['@/views/tool/gen/editTable'], resolve),
      name: 'GenEdit',
      meta: {
        title: '修改生成配置',
        activeMenu: '/tool/gen'
      }
    }]
  },
  {
    path: '/curriculum/curriculum-details',
    component: Layout,
    hidden: true,
    children: [{
      path: 'index/:curriculumId(\\d+)',
      component: (resolve) => require(['@/views/music/curriculumDetails'], resolve),
      name: 'curriculum-details',
      meta: {
        title: '课程列表',
        activeMenu: '/music/curriculum'
      }
    }]
  },
  {
    path: '/curriculum',
    component: (resolve) => require(['@/views/curriculum/index'], resolve),
    hidden: true,
    children: [

      {
        path: 'main',
        component: (resolve) => require(['@/views/curriculum/main'], resolve),
        hidden: true,
        children: [
          {
            path: 'list',
            component: (resolve) => require(['@/views/curriculum/list'], resolve),
            name: 'curriculumList',
            meta: {
              title: '课程列表'
            }
          },
          {
            path: 'info',
            component: (resolve) => require(['@/views/curriculum/info'], resolve),
            name: 'info',
            meta: {
              title: '课程详情'
            }
          },
          {
            path: 'instrumentList',
            component: (resolve) => require(['@/views/music/instrument/instrument'], resolve),
            name: 'instrumentList',
            meta: {
              title: '乐器介绍'
            }
          },
          {
            path: 'goodsList',
            component: (resolve) => require(['@/views/mall/goods/goodsList'], resolve),
            name: 'goodsList',
            meta: {
              title: '购物中心'
            }
          },
          {
            path: 'recognition',
            component: (resolve) => require(['@/views/music/recognition/recognition'], resolve),
            name: 'recognition',
            meta: {
              title: '乐器识别'
            }
          },
          {
            path: 'instrumentInfo',
            component: (resolve) => require(['@/views/music/instrument/instrumentInfo'], resolve),
            name: 'instrumentInfo',
            meta: {
              title: '乐器详情'
            }
          },
        ]
      }
    ]
  },
  {
    path: '/mall/orderDetail/my-list',
    component: Layout,
    hidden: true,
    children: [{
      path: 'index/:orderId(\\d+)',
      component: (resolve) => require(['@/views/mall/mallDetail/index'], resolve),
      name: 'myOrderDetailList',
      meta: {
        title: '我的订单详情',
        activeMenu: '/mall/mallDetail'
      }
    }]
  },
  {
    path: '/mall/orderDetail',
    component: Layout,
    hidden: true,
    children: [{
      path: 'index/:orderId(\\d+)',
      component: (resolve) => require(['@/views/mall/mallDetail/index'], resolve),
      name: 'myOrderDetailList',
      meta: {
        title: '订单详情',
        activeMenu: '/mall/mallDetail'
      }
    }]
  },
]

export default new Router({
  mode: 'history', // 去掉url中的#
  scrollBehavior: () => ({
    y: 0
  }),
  routes: constantRoutes
})
