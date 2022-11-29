package softengineerjay.springbootcrudfileupload.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softengineerjay.springbootcrudfileupload.Models.Student;

@Repository
public interface StudentDao extends JpaRepository<Student, Long> {
}
