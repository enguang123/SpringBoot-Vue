package coding.xyz.dormitory.service.impl;

import coding.xyz.dormitory.entity.Building;
import coding.xyz.dormitory.entity.Dormitory;
import coding.xyz.dormitory.entity.Student;
import coding.xyz.dormitory.form.SearchForm;
import coding.xyz.dormitory.mapper.BuildingMapper;
import coding.xyz.dormitory.mapper.DormitoryAdminMapper;
import coding.xyz.dormitory.mapper.DormitoryMapper;
import coding.xyz.dormitory.mapper.StudentMapper;
import coding.xyz.dormitory.service.BuildingService;
import coding.xyz.dormitory.vo.BuildingVO;
import coding.xyz.dormitory.vo.PageVO;
import coding.xyz.dormitory.vo.StudentVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-06-25
 */
@Service
public class BuildingServiceImpl extends ServiceImpl<BuildingMapper, Building> implements BuildingService {

    @Autowired
    private BuildingMapper buildingMapper;
    @Autowired
    private DormitoryAdminMapper dormitoryAdminMapper;
    @Autowired
    private DormitoryMapper dormitoryMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageVO listBuilding(Integer page, Integer size) {
        Page<Building> buildingPage = new Page<>(page,size);
        Page<Building> resultPage = this.buildingMapper.selectPage(buildingPage, null);
        //System.out.println("fsdfdsfsdfdsfsdfsdfsdfdsfdsfsdfs");
        //System.out.println(resultPage.getRecords());
        List<Building> buildingList = resultPage.getRecords();
        //VO转换
        List<BuildingVO> buildingVOList = new ArrayList<>();
        for(Building building : buildingList){
            BuildingVO buildingVO = new BuildingVO();
            BeanUtils.copyProperties(building,buildingVO);
            buildingVO.setAdminName(this.dormitoryAdminMapper.selectById(building.getAdminId()).getName());
            buildingVOList.add(buildingVO);
        }
        PageVO pageVO = new PageVO();
        pageVO.setData(buildingVOList);
        pageVO.setTotal(resultPage.getTotal());
        return pageVO;
    }

    @Override
    public PageVO search(SearchForm searchForm) {
        Page<Building> buildingPage = new Page<>(searchForm.getPage(),searchForm.getSize());
        Page<Building> resultPage=null;
        if(searchForm.getValue().equals("")){
            resultPage = this.buildingMapper.selectPage(buildingPage, null);
        }else{
            QueryWrapper<Building> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(searchForm.getKey(),searchForm.getValue());
            resultPage = this.buildingMapper.selectPage(buildingPage, queryWrapper);
        }
        List<Building> buildingList = resultPage.getRecords();
        //VO转换
        List<BuildingVO> buildingVOList = new ArrayList<>();
        for(Building building : buildingList){
            BuildingVO buildingVO = new BuildingVO();
            BeanUtils.copyProperties(building,buildingVO);
            buildingVO.setAdminName(this.dormitoryAdminMapper.selectById(building.getAdminId()).getName());
            buildingVOList.add(buildingVO);
        }
        PageVO pageVO = new PageVO();
        pageVO.setData(buildingVOList);
        pageVO.setTotal(resultPage.getTotal());
        return pageVO;
    }

    @Override
    public Boolean deleteById(Integer id) {
        //找到楼宇中的宿舍
        //找到宿舍中的学生
        //换宿舍
        //删除原来宿舍
        //删除原来的楼宇
        QueryWrapper<Dormitory> dormitoryQueryWrapper = new QueryWrapper<>();
        dormitoryQueryWrapper.eq("building_id",id);
        List<Dormitory> dormitoryList = this.dormitoryMapper.selectList(dormitoryQueryWrapper);
        for (Dormitory dormitory : dormitoryList) {
            QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
            studentQueryWrapper.eq("dormitory_id",dormitory.getId());
            List<Student> studentList = this.studentMapper.selectList(studentQueryWrapper);
            for (Student student : studentList) {
                Integer availableDormitoryId = this.dormitoryMapper.findAvailableDormitoryId();
                student.setDormitoryId(availableDormitoryId);
                try {
                    this.studentMapper.updateById(student);
                    this.dormitoryMapper.subAvailable(availableDormitoryId);
                } catch (Exception e) {
                    return false;
                }
            }
            int i = this.dormitoryMapper.deleteById(dormitory.getId());
            if(i != 1) return false;
        }
        int i = this.buildingMapper.deleteById(id);
        if(i != 1) return false;
        return true;
    }
}
