package coding.xyz.dormitory.controller;


import coding.xyz.dormitory.entity.Building;
import coding.xyz.dormitory.entity.Student;
import coding.xyz.dormitory.form.SearchForm;
import coding.xyz.dormitory.form.StudentForm;
import coding.xyz.dormitory.service.BuildingService;
import coding.xyz.dormitory.util.ResultVOUtil;
import coding.xyz.dormitory.vo.PageVO;
import coding.xyz.dormitory.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-06-25
 */
@RestController
@RequestMapping("/building")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @PostMapping("/save")
    public ResultVO save(@RequestBody Building building){
        System.out.println("dfwef");
        boolean save = this.buildingService.save(building);
        if(!save) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }

    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page")Integer page, @PathVariable("size")Integer size){
        return ResultVOUtil.success(this.buildingService.listBuilding(page, size));
    }

    @GetMapping("/search")
    public ResultVO search(SearchForm searchForm){
        return ResultVOUtil.success(this.buildingService.search(searchForm));
    }

    @GetMapping("/findById/{id}")
    public ResultVO findById(@PathVariable("id") Integer id){
        return ResultVOUtil.success(this.buildingService.getById(id));
    }

    @PutMapping("/update")
    public ResultVO update(@RequestBody Building building){
        boolean b = this.buildingService.updateById(building);
        if(!b) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResultVO deleteById(@PathVariable("id") Integer id){
        Boolean aBoolean = this.buildingService.deleteById(id);
        if(!aBoolean) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }

    @GetMapping("/list")
    public ResultVO list(){
        return ResultVOUtil.success(this.buildingService.list());
    }
}

