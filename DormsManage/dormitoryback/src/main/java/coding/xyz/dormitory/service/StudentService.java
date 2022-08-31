package coding.xyz.dormitory.service;

import coding.xyz.dormitory.entity.Student;
import coding.xyz.dormitory.form.SearchForm;
import coding.xyz.dormitory.form.StudentForm;
import coding.xyz.dormitory.vo.PageVO;
import coding.xyz.dormitory.vo.StudentVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-06-25
 */
public interface StudentService extends IService<Student> {
    public Boolean saveStudent(Student student);
    public PageVO listStudent(Integer page, Integer size);
    public PageVO search(SearchForm searchForm);
    public Boolean update(StudentForm studentForm);
    public Boolean deleteById(Integer id);
}
