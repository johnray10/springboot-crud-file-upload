package softengineerjay.springbootcrudfileupload.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import softengineerjay.springbootcrudfileupload.Config.FileUploadUtil;
import softengineerjay.springbootcrudfileupload.Models.Student;
import softengineerjay.springbootcrudfileupload.Services.StudentService;

import java.io.IOException;
import java.util.List;


@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/")
    public String showStudent(Model model) {
        List<Student> student = studentService.getStudentList();
        model.addAttribute("students", student);
        return "views/index";
    }

    @GetMapping("/addStudent")
    public String Project2(Model model) {
        // create model attribute to bind form data
        Student student = new Student();
        model.addAttribute("students", student);
        return "views/add";
    }

    @PostMapping("/saveStudent")
    public RedirectView saveStudent(@ModelAttribute("students") Student student,
                                    @RequestParam("image") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        student.setPhotos(fileName);
        Student saveStudent = studentService.saveStudent(student);
        String uploadDir = "student-photos/" + saveStudent.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return new RedirectView("/", true);
    }

    @GetMapping("/editStudent/{id}")
    public String UpdateImage(@PathVariable(value = "id") long id, Model model) {
        // get employee from the service
        Student student = studentService.getStudentById(id);
        // set employee as a model attribute to pre-populate the form
        model.addAttribute("students", student);
        return "views/edit";
    }

    @GetMapping("/deleteStudent/{id}")
    public String deleteProduct(@PathVariable(value = "id") long id) {

        // call delete employee method
        this.studentService.deleteStudentById(id);
        return "redirect:/";
    }
}
