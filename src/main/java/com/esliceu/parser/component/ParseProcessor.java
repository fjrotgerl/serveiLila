package com.esliceu.parser.component;

import com.esliceu.parser.model.database.*;
import com.esliceu.parser.model.database.Group;
import com.esliceu.parser.model.database.Student;
import com.esliceu.parser.model.database.StudentSession;
import com.esliceu.parser.model.xml.*;
import com.esliceu.parser.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class ParseProcessor {

    @Autowired
    private Xmlparse parser;

    @Autowired
    private StudentSession studentSession;

    @Autowired
    private Group group;

    @Autowired
    private Aula aula;

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


    private Long incrementalID = 0L;

    public Xmlparse getParser() {
        return parser;
    }

    public void setParser(Xmlparse parser) {
        this.parser = parser;
    }

    public void init() throws JAXBException {

        //Recogemos el objeto que contiene los datos a guardar
        Center data = parser.getData();

        Map<String,Group> groupsTutor = new HashMap<>();

        for (Courses courses : data.getCourses()) {
            for (Course studentSessions : courses.getStudentSessions()) {
                for (com.esliceu.parser.model.xml.Group group : studentSessions.getGroups()) {

                    Group groupDb = new Group();
                    groupDb.setCode(group.getCode());
                    groupDb = groupRepository.save(groupDb);

                    groupsTutor.put(group.getTutor(),groupDb);

                }
            }
        }


        System.out.println("Grupos añadidos");

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
            for (int j = 0; j < data.getAlumnes().get(i).getAlumnes().size(); j++) {

                Student student = new Student();

                student.setCode(data.getAlumnes().get(i).getAlumnes().get(j).getCode());
                student.setName(data.getAlumnes().get(i).getAlumnes().get(j).getName());
                student.setFirstSurname(data.getAlumnes().get(i).getAlumnes().get(j).getFirstSurname());
                student.setSecondSurname(data.getAlumnes().get(i).getAlumnes().get(j).getSecondSurname());

                //Recupera el grupo ya creado anteriormente que tenga el mismo numero que el grupo del usuario
                Group auxGroup = groupRepository.findById(data.getAlumnes().get(i).getAlumnes().get(j).getGroupCode()).get();
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
                professorSession.setHour(teachersSession.getHour());

                Optional<Group> group = groupRepository.findById(teachersSession.getGroupCode());

                group.ifPresent(professorSession::setGroup);

                Optional<Professor> professor = professorRepository.findById(teachersSession.getProfessorCode());
                professor.ifPresent(professorSession::setProfessor);

                sessionProfessorRepository.save(professorSession);
            }

        }


        System.out.println("Sessiones de profe añadidos");

        //Sacar todos las sessiones de estudiantes y  guardarlos en la base de datos

        for (int i = 0; i < data.getScheduleStudents().size(); i++) {
            for (int j = 0; j < data.getScheduleStudents().get(i).getStudentSessions().size(); j++) {
                studentSession.setId(incrementalID);
                incrementalID++;
                studentSession.setDay(data.getScheduleStudents().get(i).getStudentSessions().get(j).getDay());
                studentSession.setHour(data.getScheduleStudents().get(i).getStudentSessions().get(j).getHour());
                studentSession.setStudent(studentRepository.findById(data.getScheduleStudents().get(i).getStudentSessions().get(j).getStudentCode()).orElse(null));

                sessionStudentRepository.save(studentSession);
            }
        }

        System.out.println("Sessiones de alumno añadidos");


        //Sacar todas las aulas del centro y guardarlas en la base de datos

        /*for (int i = 0; i< data.getClassrooms().size(); i++){
            for (int j = 0; j< data.getClassrooms().get(i).getClassrooms().size(); j++){

                aula.setCode(data.getClassrooms().get(i).getClassrooms().get(j).getCodi());
                aula.setDescription(data.getClassrooms().get(i).getClassrooms().get(j).getDescripcio());
                aulaRepository.save(aula);
            }
        }

        System.out.println("Aulas añadidas");*/



    }
}
