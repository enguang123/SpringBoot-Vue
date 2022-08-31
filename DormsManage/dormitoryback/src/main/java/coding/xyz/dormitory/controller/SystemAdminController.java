package coding.xyz.dormitory.controller;


import coding.xyz.dormitory.form.ReluForm;
import coding.xyz.dormitory.service.SystemAdminService;
import coding.xyz.dormitory.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-06-25
 */
@RestController
@RequestMapping("/systemAdmin")
public class SystemAdminController {

    @Autowired
    private SystemAdminService systemAdminService;

    @GetMapping("/login")
    public ResultVO login(ReluForm reluForm){
        ResultVO resultVO = this.systemAdminService.login(reluForm);
        return resultVO;
    }
}

