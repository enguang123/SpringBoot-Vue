package coding.xyz.dormitory.service;

import coding.xyz.dormitory.entity.Absent;
import coding.xyz.dormitory.form.SearchForm;
import coding.xyz.dormitory.vo.PageVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-06-25
 */
public interface AbsentService extends IService<Absent> {
    public PageVO listAbsent(Integer page, Integer size);
    public PageVO search(SearchForm searchForm);
}
