package coding.xyz.dormitory.service;

import coding.xyz.dormitory.entity.DormitoryAdmin;
import coding.xyz.dormitory.form.ReluForm;
import coding.xyz.dormitory.form.SearchForm;
import coding.xyz.dormitory.vo.PageVO;
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
public interface DormitoryAdminService extends IService<DormitoryAdmin> {
    public ResultVO login(ReluForm reluForm);
    public PageVO list(Integer page,Integer size);
    public PageVO search(SearchForm searchForm);
}
