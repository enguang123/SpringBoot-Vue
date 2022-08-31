package coding.xyz.dormitory.controller;

import coding.xyz.dormitory.entity.Absent;
import coding.xyz.dormitory.entity.Dormitory;
import coding.xyz.dormitory.entity.Student;
import coding.xyz.dormitory.form.SearchForm;
import coding.xyz.dormitory.service.AbsentService;
import coding.xyz.dormitory.service.BuildingService;
import coding.xyz.dormitory.service.DormitoryService;
import coding.xyz.dormitory.service.StudentService;
import coding.xyz.dormitory.util.ResultVOUtil;
import coding.xyz.dormitory.vo.PageVO;
import coding.xyz.dormitory.vo.ResultVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-06-25
 */
@RestController
@RequestMapping("/absent")
public class AbsentController {

    @Autowired
    public AbsentService absentService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    public DormitoryService dormitoryService;

    @Autowired
    public StudentService studentService;

    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page")Integer page, @PathVariable("size")Integer size){
        PageVO pageVO = this.absentService.listAbsent(page, size);
        return ResultVOUtil.success(pageVO);
    }
    @GetMapping("/search")
    public ResultVO search(SearchForm searchForm){
        return ResultVOUtil.success(this.absentService.search(searchForm));
    }

    @GetMapping("/buildingList")
    public ResultVO buildingList(){
        return ResultVOUtil.success(this.buildingService.list());
    }

    @GetMapping("/findDormitoryByBuildingId/{id}")
    public ResultVO findDormitoryByBuildingId(@PathVariable("id")Integer id){
        QueryWrapper<Dormitory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("building_id",id);
        List<Dormitory> list = this.dormitoryService.list(queryWrapper);
        return ResultVOUtil.success(list);
    }

    @GetMapping("/findStudentByDormitoryId/{id}")
    public ResultVO findStudentByDormitoryId(@PathVariable("id")Integer id){
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dormitory_id",id);
        List<Student> list = this.studentService.list(queryWrapper);
        return ResultVOUtil.success(list);
    }

    @PostMapping("/save")
    public ResultVO save(@RequestBody Absent absent){
        String createDate = absent.getCreateDate();
        createDate = createDate.substring(0,10);
        absent.setCreateDate(createDate);
        boolean save = this.absentService.save(absent);
        if(!save) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }
}

