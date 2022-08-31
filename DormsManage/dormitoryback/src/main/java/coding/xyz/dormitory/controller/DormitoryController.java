package coding.xyz.dormitory.controller;


import coding.xyz.dormitory.entity.Dormitory;
import coding.xyz.dormitory.entity.Student;
import coding.xyz.dormitory.form.SearchForm;
import coding.xyz.dormitory.form.StudentForm;
import coding.xyz.dormitory.service.DormitoryService;
import coding.xyz.dormitory.util.ResultVOUtil;
import coding.xyz.dormitory.vo.PageVO;
import coding.xyz.dormitory.vo.ResultVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("/dormitory")
public class DormitoryController {

    @Autowired
    private DormitoryService dormitoryService;

    @GetMapping("/availableList")
    public ResultVO availableList(){
        QueryWrapper<Dormitory> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("available", 0);
        List<Dormitory> list = this.dormitoryService.list(queryWrapper);
        return ResultVOUtil.success(list);
    }

    @PostMapping("/save")
    public ResultVO  save(@RequestBody Dormitory dormitory){
        dormitory.setAvailable(dormitory.getType());
        boolean save = this.dormitoryService.save(dormitory);
        if(!save) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }

    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page")Integer page, @PathVariable("size")Integer size){
        PageVO pageVO = this.dormitoryService.listDormitory(page, size);
        return ResultVOUtil.success(pageVO);
    }

    @GetMapping("/search")
    public ResultVO search(SearchForm searchForm){
        return ResultVOUtil.success(this.dormitoryService.search(searchForm));
    }

    @GetMapping("/findById/{id}")
    public ResultVO find(@PathVariable("id") Integer id){
        return ResultVOUtil.success(this.dormitoryService.getById(id));
    }

    @PutMapping("/update")
    public ResultVO update(@RequestBody Dormitory dormitory){
        boolean b = this.dormitoryService.updateById(dormitory);
        if(!b) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResultVO deleteById(@PathVariable("id") Integer id){
        boolean b = this.dormitoryService.deleteById(id);
        if(!b) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }
}

