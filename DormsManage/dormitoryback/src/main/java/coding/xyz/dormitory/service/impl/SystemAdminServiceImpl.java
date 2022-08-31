package coding.xyz.dormitory.service.impl;

import coding.xyz.dormitory.entity.DormitoryAdmin;
import coding.xyz.dormitory.entity.SystemAdmin;
import coding.xyz.dormitory.form.ReluForm;
import coding.xyz.dormitory.mapper.SystemAdminMapper;
import coding.xyz.dormitory.service.SystemAdminService;
import coding.xyz.dormitory.vo.ResultVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-06-25
 */
@Service
public class SystemAdminServiceImpl extends ServiceImpl<SystemAdminMapper, SystemAdmin> implements SystemAdminService {

    @Autowired
    private SystemAdminMapper systemAdminMapper;

    @Override
    public ResultVO login(ReluForm reluForm) {
        //判断用户名
        QueryWrapper<SystemAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",reluForm.getUsername());
        SystemAdmin systemAdmin = this.systemAdminMapper.selectOne(queryWrapper);
        ResultVO resultVO = new ResultVO();
        if(systemAdmin == null){
            resultVO.setCode(-1);
        }else{
            if(!systemAdmin.getPassword().equals(reluForm.getPassword())){
                resultVO.setCode(-2);
            }else{
                resultVO.setCode(0);
                resultVO.setData(systemAdmin);
            }
        }
        return resultVO;
    }
}
