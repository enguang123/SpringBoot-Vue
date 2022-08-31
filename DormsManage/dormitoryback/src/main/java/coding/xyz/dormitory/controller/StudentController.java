package coding.xyz.dormitory.controller;


import coding.xyz.dormitory.entity.DormitoryAdmin;
import coding.xyz.dormitory.entity.Student;
import coding.xyz.dormitory.form.SearchForm;
import coding.xyz.dormitory.form.StudentForm;
import coding.xyz.dormitory.service.StudentService;
import coding.xyz.dormitory.util.ResultVOUtil;
import coding.xyz.dormitory.vo.PageVO;
import coding.xyz.dormitory.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-06-25
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/save")
    public ResultVO save(@RequestBody Student student){
        Boolean aBoolean = this.studentService.saveStudent(student);
        if(!aBoolean) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }

    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page")Integer page, @PathVariable("size")Integer size){
        PageVO pageVO = this.studentService.listStudent(page, size);
        return ResultVOUtil.success(pageVO);
    }

    @GetMapping("/search")
    public ResultVO search(SearchForm searchForm){
        return ResultVOUtil.success(this.studentService.search(searchForm));
    }

    @GetMapping("/findById/{id}")
    public ResultVO find(@PathVariable("id") Integer id){
        Student student = this.studentService.getById(id);
        StudentForm studentForm = new StudentForm();
        BeanUtils.copyProperties(student,studentForm);
        studentForm.setOldDormitory(student.getDormitoryId());
        return ResultVOUtil.success(studentForm);
    }

    @PutMapping("/update")
    public ResultVO update(@RequestBody StudentForm studentForm){
        boolean b = this.studentService.update(studentForm);
        if(!b) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResultVO deleteById(@PathVariable("id")Integer id){
        Boolean aBoolean = this.studentService.deleteById(id);
        if(!aBoolean) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }

}

