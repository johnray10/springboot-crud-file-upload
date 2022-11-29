package softengineerjay.springbootcrudfileupload.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softengineerjay.springbootcrudfileupload.Models.Student;
import softengineerjay.springbootcrudfileupload.Repository.StudentDao;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;

    public List<Student> getStudentList(){
        return studentDao.findAll();
    }

    public Student saveStudent(Student student){
        return this.studentDao.save(student);
    }

    public Student getStudentById(long id) {
        Optional<Student> optional = studentDao.findById(id);
        Student student = null;
        if (optional.isPresent()) {
            student = optional.get();
        } else {
            throw new RuntimeException(" Student not found for id :: " + id);
        }
        return student;
    }

    public void deleteStudentById(long id) {
        this.studentDao.deleteById(id);
    }
}
