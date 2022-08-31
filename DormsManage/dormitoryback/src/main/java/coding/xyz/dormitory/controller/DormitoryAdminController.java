package coding.xyz.dormitory.controller;


import coding.xyz.dormitory.entity.DormitoryAdmin;
import coding.xyz.dormitory.form.ReluForm;
import coding.xyz.dormitory.form.SearchForm;
import coding.xyz.dormitory.service.DormitoryAdminService;
import coding.xyz.dormitory.util.ResultVOUtil;
import coding.xyz.dormitory.vo.PageVO;
import coding.xyz.dormitory.vo.ResultVO;
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
@RequestMapping("/dormitoryAdmin")
public class DormitoryAdminController {

    @Autowired
    private DormitoryAdminService dormitoryAdminService;

    @GetMapping("/login")
    public ResultVO login(ReluForm reluForm){
        ResultVO resultVO = this.dormitoryAdminService.login(reluForm);
        return resultVO;
    }

    @PostMapping("/save")
    public ResultVO save(@RequestBody DormitoryAdmin dormitoryAdmin){
        boolean save = this.dormitoryAdminService.save(dormitoryAdmin);
        if(!save) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }

    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        PageVO pageVO = this.dormitoryAdminService.list(page, size);
        return ResultVOUtil.success(pageVO);
    }

    @GetMapping("/search")
    public ResultVO search(SearchForm searchForm){
        PageVO pageVO = this.dormitoryAdminService.search(searchForm);
        return ResultVOUtil.success(pageVO);
    }

    @GetMapping("/findById/{id}")
    public ResultVO find(@PathVariable("id") Integer id){
        DormitoryAdmin byId = this.dormitoryAdminService.getById(id);
        return ResultVOUtil.success(byId);
    }

    @PutMapping("/update")
    public ResultVO update(@RequestBody DormitoryAdmin dormitoryAdmin){
        boolean b = this.dormitoryAdminService.updateById(dormitoryAdmin);
        if(!b) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResultVO delete(@PathVariable("id") Integer id){
        boolean b = this.dormitoryAdminService.removeById(id);
        if(!b) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }

    @GetMapping("/list")
    public ResultVO list(){
        List<DormitoryAdmin> list = this.dormitoryAdminService.list();
        return ResultVOUtil.success(list);
    }
}

