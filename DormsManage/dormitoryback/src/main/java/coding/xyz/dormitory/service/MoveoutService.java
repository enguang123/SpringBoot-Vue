package coding.xyz.dormitory.service;

import coding.xyz.dormitory.entity.Moveout;
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
public interface MoveoutService extends IService<Moveout> {
    public PageVO list(Integer page,Integer size);
    public PageVO search(SearchForm searchForm);
    public Boolean moveout(Integer id,String reason);
    public PageVO moveoutList(Integer page,Integer size);
    public PageVO moveoutSearch(SearchForm searchForm);
}
