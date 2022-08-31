package coding.xyz.dormitory.service.impl;

import coding.xyz.dormitory.entity.Building;
import coding.xyz.dormitory.entity.Dormitory;
import coding.xyz.dormitory.entity.Student;
import coding.xyz.dormitory.form.SearchForm;
import coding.xyz.dormitory.mapper.BuildingMapper;
import coding.xyz.dormitory.mapper.DormitoryMapper;
import coding.xyz.dormitory.mapper.StudentMapper;
import coding.xyz.dormitory.service.BuildingService;
import coding.xyz.dormitory.service.DormitoryService;
import coding.xyz.dormitory.vo.DormitoryVO;
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
public class DormitoryServiceImpl extends ServiceImpl<DormitoryMapper, Dormitory> implements DormitoryService {

    @Autowired
    private DormitoryMapper dormitoryMapper;

    @Autowired
    private BuildingMapper buildingMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageVO listDormitory(Integer page, Integer size) {
        Page<Dormitory> studentPage = new Page<>(page,size);
        Page<Dormitory> resultPage = this.dormitoryMapper.selectPage(studentPage, null);
        List<Dormitory> dormitoryList = resultPage.getRecords();
        //VO转换
        List<DormitoryVO> dormitoryVOList = new ArrayList<>();

        for(Dormitory dormitory : dormitoryList){
            DormitoryVO dormitoryVO = new DormitoryVO();
            BeanUtils.copyProperties(dormitory,dormitoryVO);

            Building building = this.buildingMapper.selectById(dormitory.getBuildingId());
            dormitoryVO.setBuildingName(building.getName());
            dormitoryVOList.add(dormitoryVO);
        }

        PageVO pageVO = new PageVO();
        pageVO.setData(dormitoryVOList);
        pageVO.setTotal(resultPage.getTotal());
        return pageVO;
    }

    @Override
    public PageVO search(SearchForm searchForm) {
        Page<Dormitory> dormitoryPage = new Page<>(searchForm.getPage(),searchForm.getSize());
        Page<Dormitory> resultPage=null;
        if(searchForm.getValue().equals("")){
            resultPage = this.dormitoryMapper.selectPage(dormitoryPage,null);
        }else{
            QueryWrapper<Dormitory> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(searchForm.getKey(),searchForm.getValue());
            resultPage = this.dormitoryMapper.selectPage(dormitoryPage, queryWrapper);
        }
        List<Dormitory> dormitoryList = resultPage.getRecords();
        //VO转换
        List<DormitoryVO> dormitoryVOList = new ArrayList<>();

        for(Dormitory dormitory : dormitoryList){
            DormitoryVO dormitoryVO = new DormitoryVO();
            BeanUtils.copyProperties(dormitory,dormitoryVO);

            Building building = this.buildingMapper.selectById(dormitory.getBuildingId());
            dormitoryVO.setBuildingName(building.getName());
            dormitoryVOList.add(dormitoryVO);
        }

        PageVO pageVO = new PageVO();
        pageVO.setData(dormitoryVOList);
        pageVO.setTotal(resultPage.getTotal());
        return pageVO;

    }

    @Override
    public Boolean deleteById(Integer id) {
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("dormitory_id",id);
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
        int i = this.dormitoryMapper.deleteById(id);
        if(i != 1) return false;
        return true;
    }
}
