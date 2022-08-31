package coding.xyz.dormitory.service.impl;

import coding.xyz.dormitory.entity.DormitoryAdmin;
import coding.xyz.dormitory.form.ReluForm;
import coding.xyz.dormitory.form.SearchForm;
import coding.xyz.dormitory.mapper.DormitoryAdminMapper;
import coding.xyz.dormitory.service.DormitoryAdminService;
import coding.xyz.dormitory.vo.PageVO;
import coding.xyz.dormitory.vo.ResultVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public class DormitoryAdminServiceImpl extends ServiceImpl<DormitoryAdminMapper, DormitoryAdmin> implements DormitoryAdminService {

    @Autowired
    private  DormitoryAdminMapper dormitoryAdminMapper;

    @Override
    public ResultVO login(ReluForm reluForm) {
        //判断用户名
        QueryWrapper<DormitoryAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",reluForm.getUsername());
        DormitoryAdmin dormitoryAdmin = this.dormitoryAdminMapper.selectOne(queryWrapper);
        ResultVO resultVO = new ResultVO();
        if(dormitoryAdmin == null){
            resultVO.setCode(-1);
        }else{
            if(!dormitoryAdmin.getPassword().equals(reluForm.getPassword())){
                resultVO.setCode(-2);
            }else{
                resultVO.setCode(0);
                resultVO.setData(dormitoryAdmin);
            }
        }
        return resultVO;
    }

    @Override
    public PageVO list(Integer page, Integer size) {
        Page<DormitoryAdmin> dormitoryAdminPage = new Page<>(page,size);
        Page<DormitoryAdmin> resultPage = this.dormitoryAdminMapper.selectPage(dormitoryAdminPage, null);
        PageVO pageVO= new PageVO();
        pageVO.setData(resultPage.getRecords());
        pageVO.setTotal(resultPage.getTotal());
        return pageVO;
    }

    @Override
    public PageVO search(SearchForm searchForm) {
        //模糊查询加分页
        Page<DormitoryAdmin> dormitoryAdminPage = new Page<>(searchForm.getPage(),searchForm.getSize());
        Page<DormitoryAdmin> resultPage = null;
        if(searchForm.getValue().equals("")){
            resultPage = this.dormitoryAdminMapper.selectPage(dormitoryAdminPage, null);
        }else{
            QueryWrapper<DormitoryAdmin> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(searchForm.getKey(),searchForm.getValue());
            resultPage = this.dormitoryAdminMapper.selectPage(dormitoryAdminPage, queryWrapper);
        }
        PageVO pageVO= new PageVO();
        pageVO.setData(resultPage.getRecords());
        pageVO.setTotal(resultPage.getTotal());
        return pageVO;
    }
}
