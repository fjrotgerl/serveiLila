package com.esliceu.parser.component;

import com.esliceu.parser.model.database.Course;
import com.esliceu.parser.model.database.Group;
import com.esliceu.parser.model.database.Student;
import com.esliceu.parser.model.database.StudentSession;
import com.esliceu.parser.model.database.*;
import com.esliceu.parser.model.xml.Subject;
import com.esliceu.parser.model.xml.*;
import com.esliceu.parser.repository.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.joda.time.format.DateTimeFormatter;

import javax.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class ParseProcessor {

    @Autowired
    private Xmlparse parser;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private SessionProfessorRepository sessionProfessorRepository;

    @Autowired
    private SessionStudentRepository sessionStudentRepository;

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private DateTime dateTime;


    public Xmlparse getParser() {
        return parser;
    }

    public void setParser(Xmlparse parser) {
        this.parser = parser;
    }

    public void init() throws JAXBException {

        //Recogemos el objeto que contiene los datos a guardar
        Center data = parser.getData();

        Map<String, Group> groupsTutor = new HashMap<>();


        // Sacar los cursos, cada uno de los grupos que tienen esos cursos y meterlos en la base de datos
        for (Courses courses : data.getCourses()){

            for (com.esliceu.parser.model.xml.Course course : courses.getCourses()){

                Course courseDB = new Course();
                courseDB.setCode(course.getCodi());
                courseDB.setDescription(course.getDescripcio());
                courseRepository.save(courseDB);

                for (com.esliceu.parser.model.xml.Group group : course.getGroups()){

                    Group groupDb = new Group();
                    groupDb.setCode(group.getCode());
                    groupDb.setName(group.getName());
                    groupDb.setCourse(courseDB);
                    groupDb = groupRepository.save(groupDb);
                    groupsTutor.put(group.getTutor(), groupDb);

                }

            }

            System.out.println("Grupos añadidos");
        }

        System.out.println("Cursos añadidos");

        //Sacar todas las asignaturas y guardarlas en la base de datos

        for (Subjects subjects : data.getSubjects()){
            for (Subject subject : subjects.getSubjects()){
                com.esliceu.parser.model.database.Subject subjectDB = new com.esliceu.parser.model.database.Subject();
                subjectDB.setCode(subject.getCodi());
                subjectDB.setDescription(subject.getDescripcio());
                Optional<Course> course = courseRepository.findById(subject.getCurs());
                course.ifPresent(subjectDB::setCourse);
                subjectRepository.save(subjectDB);


            }
        }

        System.out.println("Asignaturas añadidas");



        //Sacar todos los professores y  guardarlos en la base de datos

        for (Teachers teachers : data.getProfessors()) {
            for (Teacher teacher : teachers.getTeacher()) {

                Professor professor = new Professor();
                professor.setCodi(teacher.getCode());
                professor.setNom(teacher.getName());
                professor.setFirstSurname(teacher.getFirstSurname());
                professor.setSecondSurname(teacher.getFirstSurname());
                professor.setGroup(groupsTutor.get(teacher.getCode()));

                professorRepository.save(professor);

            }

        }

        System.out.println("Profes añadidos");


        //Sacar todos los estudiantes y  guardarlos en la base de datos

        for (int i = 0; i < data.getAlumnes().size(); i++) {
            for (int j = 0; j < data.getAlumnes().get(i).getStudents().size(); j++) {

                Student student = new Student();

                student.setCode(data.getAlumnes().get(i).getStudents().get(j).getCode());
                student.setName(data.getAlumnes().get(i).getStudents().get(j).getName());
                student.setFirstSurname(data.getAlumnes().get(i).getStudents().get(j).getFirstSurname());
                student.setSecondSurname(data.getAlumnes().get(i).getStudents().get(j).getSecondSurname());

                //Recupera el grupo ya creado anteriormente que tenga el mismo numero que el grupo del usuario
                Group auxGroup = groupRepository.findById(data.getAlumnes().get(i).getStudents().get(j).getGroupCode()).get();
                student.setGroup(auxGroup);

                studentRepository.save(student);
            }
        }

        System.out.println("Estudiantes añadidos");


        //Sacar todos las sessiones de professores y  guardarlos en la base de datos

        for (ScheduleTeachers scheduleTeachers : data.getScheduleTeachers()){
            for (TeachersSession teachersSession: scheduleTeachers.getTeachersSessions()){

                ProfessorSession professorSession = new ProfessorSession();
                professorSession.setDay(teachersSession.getDay());

                professorSession.setStartHour(teachersSession.getHour());

                Integer durada = teachersSession.getDurada();
                
                professorSession.setDurada(durada);

                DateTimeFormatter hm = DateTimeFormat.forPattern("HH:mm");
                DateTime startHour = dateTime.parse(teachersSession.getHour(),hm);

                DateTime endHour = startHour.plusMinutes(durada);

                String formatedEndHour = String.format("%d:%02d",(endHour.getHourOfDay()),(endHour.getMinuteOfHour()));;

                professorSession.setEndHour(formatedEndHour);

                Optional<Course> course = courseRepository.findById(teachersSession.getCurs());

                course.ifPresent(professorSession::setCourse);

                Optional<com.esliceu.parser.model.database.Subject> subject = subjectRepository.findById(teachersSession.getSubmateria());

                subject.ifPresent(professorSession::setSubject);

                Optional<Group> group = groupRepository.findById(teachersSession.getGroupCode());

                group.ifPresent(professorSession::setGroup);

                Optional<Professor> professor = professorRepository.findById(teachersSession.getProfessorCode());
                professor.ifPresent(professorSession::setProfessor);

                sessionProfessorRepository.save(professorSession);
            }

        }


        System.out.println("Sessiones de profe añadidos");


        //Sacar todos las sessiones de estudiantes y  guardarlos en la base de datos

        for(ScheduleStudents scheduleStudents : data.getScheduleStudents()){
            for(com.esliceu.parser.model.xml.StudentSession studentSession : scheduleStudents.getStudentSessions()){

                StudentSession studentSessionDB = new StudentSession();
                studentSessionDB.setDay(studentSession.getDay());
                studentSessionDB.setHour(studentSession.getHour());

                Optional<Student> student = studentRepository.findById(studentSession.getStudentCode());
                student.ifPresent(studentSessionDB::setStudent);

                sessionStudentRepository.save(studentSessionDB);

            }
        }

        System.out.println("Sessiones de alumno añadidos");


        //Sacar todas las aulas del centro y guardarlas en la base de datos

        for ( Classrooms classrooms : data.getClassrooms()){
            for( Classroom classroom : classrooms.getClassrooms()){

                SchoolRoom schoolRoom = new SchoolRoom();
                schoolRoom.setCode(classroom.getCodi());
                schoolRoom.setDescription(classroom.getDescripcio());
                aulaRepository.save(schoolRoom);

            }
        }

        System.out.println("Aulas añadidas");

    }
}
