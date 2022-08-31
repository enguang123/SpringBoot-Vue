package coding.xyz.dormitory.service;

import coding.xyz.dormitory.entity.SystemAdmin;
import coding.xyz.dormitory.form.ReluForm;
import coding.xyz.dormitory.vo.ResultVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-06-25
 */
public interface SystemAdminService extends IService<SystemAdmin> {
    public ResultVO login(ReluForm reluForm);
}
