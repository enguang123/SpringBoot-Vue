import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import SystemAdmin from '../views/SystemAdmin.vue'
import DormitoryAdminAdd from '../views/DormitoryAdminAdd.vue'
import DormitoryAdminManager from '../views/DormitoryAdminManager.vue'
import DormitoryAdminUpdate from '../views/DormitoryAdminUpdate.vue'
import StudentAdd from '../views/StudentAdd.vue'
import StudentManager from '../views/StudentManager.vue'
import StudentUpdate from '../views/StudentUpdate.vue'
import BuildingAdd from '../views/BuildingAdd.vue' 
import BuildingManager from '../views/BuildingManager.vue' 
import BuildingUpdate from '../views/BuildingUpdate.vue' 
import DormitoryAdd from '../views/DormitoryAdd.vue'
import DormitoryManager from '../views/DormitoryManager.vue'
import DormitoryUpdate from '../views/DormitoryUpdate.vue'
import MoveoutRegister from '../views/MoveoutRegister.vue'
import MoveoutRecord from '../views/MoveRecord.vue'
import AbsentRecord from '../views/AbsentRecord.vue'
import AbsentRegister from '../views/AbsentRegister.vue'
import DormitoryAdmin from '../views/DormitoryAdmin.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    component: Login
  },
  {
    path: '/dormitoryAdmin',
    name: '系宿舍管理员',
    component: DormitoryAdmin,
    redirect:"/absentRecord2",
    children: [
      {
        path: '/absentRecord2',
        name: '缺寝记录',
        component: AbsentRecord
      },
      {
        path: '/absentRegister',
        name: '缺寝登记',
        component: AbsentRegister
      }
    ]
  },
  {
    path: '/systemAdmin',
    name: '系统管理员',
    component: SystemAdmin,
    redirect:"/dormitoryAdminAdd",
    children: [
      {
        path: '/dormitoryAdminManager',
        name: '宿管管理',
        component: DormitoryAdminManager
      },
      {
        path: '/dormitoryAdminAdd',
        name: '添加宿管',
        component: DormitoryAdminAdd
      },
      {
        path: '/dormitoryAdminUpdate',
        name: '修改宿管',
        component: DormitoryAdminUpdate
      },
      {
        path: '/studentAdd',
        name: '添加学生',
        component: StudentAdd
      },
      {
        path: '/studentManager',
        name: '学生管理',
        component: StudentManager
      },
      {
        path: '/studentUpdate',
        name: '修改学生',
        component: StudentUpdate
      },
      {
        path: '/buildingAdd',
        name: '添加楼宇',
        component: BuildingAdd
      },
      {
        path: '/buildingManager',
        name: '楼宇管理',
        component: BuildingManager
      },
      {
        path: '/buildingUpdate',
        name: '更新楼宇',
        component: BuildingUpdate
      },
      {
        path: '/dormitoryManager',
        name: '宿舍管理',
        component: DormitoryManager
      },
      {
        path: '/dormitoryAdd',
        name: '添加宿舍',
        component: DormitoryAdd
      },
      {
        path: '/dormitoryUpdate',
        name: '修改宿舍',
        component: DormitoryUpdate
      },
      {
        path: '/moveoutRegister',
        name: '迁出登记',
        component: MoveoutRegister
      },
      {
        path: '/moveoutRecord',
        name: '迁出记录',
        component: MoveoutRecord
      },
      {
        path: '/absentRecord',
        name: '缺寝记录',
        component: AbsentRecord
      },
      {
        path: '/absentRegister',
        name: '缺寝登记',
        component: AbsentRegister
      }
    ]
  },
  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
