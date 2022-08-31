package coding.xyz.dormitory.controller;

import coding.xyz.dormitory.form.SearchForm;
import coding.xyz.dormitory.service.MoveoutService;
import coding.xyz.dormitory.util.ResultVOUtil;
import coding.xyz.dormitory.vo.PageVO;
import coding.xyz.dormitory.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @since 2022-06-25
 */
@RestController
@RequestMapping("/moveout")
public class MoveoutController {
    @Autowired
    private MoveoutService moveoutService;

    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page")Integer page, @PathVariable("size")Integer size){
        PageVO pageVO = this.moveoutService.list(page, size);
        return ResultVOUtil.success(pageVO);
    }

    @GetMapping("/search")
    public ResultVO search(SearchForm searchForm){
        return ResultVOUtil.success(this.moveoutService.search(searchForm));
    }

    @PutMapping("/moveout/{id}/{reason}")
    public ResultVO moveout(@PathVariable("id")Integer id, @PathVariable("reason")String reason){
        Boolean moveout = this.moveoutService.moveout(id, reason);
        if(!moveout) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }

    @GetMapping("/moveoutList/{page}/{size}")
    public ResultVO moveoutList(@PathVariable("page")Integer page, @PathVariable("size")Integer size){
        PageVO pageVO = this.moveoutService.moveoutList(page, size);
        return ResultVOUtil.success(pageVO);
    }

    @GetMapping("/moveoutSearch")
    public ResultVO moveoutSearch(SearchForm searchForm){
        return ResultVOUtil.success(this.moveoutService.moveoutSearch(searchForm));
    }
}

