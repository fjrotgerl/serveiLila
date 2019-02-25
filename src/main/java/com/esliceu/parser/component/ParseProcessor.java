package com.esliceu.parser.component;

import com.esliceu.parser.model.database.*;
import com.esliceu.parser.model.xml.Center;
import com.esliceu.parser.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;

@Component
public class ParseProcessor {

    @Autowired
    private Xmlparse parser;

    @Autowired
    private Student student;

    @Autowired
    private Professor professor;

    @Autowired
    private ProfessorSession professorSession;

    @Autowired
    private StudentSession studentSession;

    @Autowired
    private Group group;

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

    public Xmlparse getParser() {
        return parser;
    }

    public void setParser(Xmlparse parser) {
        this.parser = parser;
    }

    public void init() throws JAXBException {
        // save a couple of customer


        //Prueba de funcionamiento
        Center data = parser.getData();

        //Sacar todos los grupos y  guardarlos en la base de datos
        for (int i = 0; i < data.getCourses().size(); i++) {
            for (int j = 0; j < data.getCourses().get(i).getStudentSessions().size(); j++) {
                for (int k = 0; k < data.getCourses().get(i).getStudentSessions().get(j).getGroups().size(); k++) {
                    group.setCode(data.getCourses().get(i).getStudentSessions().get(j).getGroups().get(k).getCode());
                    groupRepository.save(group);
                }
            }
        }
        //Sacar todos los estudiantes y  guardarlos en la base de datos

        for (int i = 0; i < data.getAlumnes().size(); i++) {
            for (int j = 0; j < data.getAlumnes().get(i).getAlumnes().size(); j++) {

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

        //Sacar todos los professores y  guardarlos en la base de datos
        for (int i = 0; i < data.getProfessors().size(); i++) {
            for (int j = 0; j < data.getProfessors().get(i).getTeacher().size(); j++) {
                professor.setCodi(data.getProfessors().get(i).getTeacher().get(j).getCode());
                professor.setNom(data.getProfessors().get(i).getTeacher().get(j).getName());
                professor.setFirstSurname(data.getProfessors().get(i).getTeacher().get(j).getFirstSurname());
                professor.setSecondSurname(data.getProfessors().get(i).getTeacher().get(j).getFirstSurname());
                professorRepository.save(professor);
            }
        }

        //Sacar todos las sessiones de professores y  guardarlos en la base de datos

        for (int i = 0; i < data.getScheduleTeachers().size(); i++) {
            for (int j = 0; j < data.getScheduleTeachers().get(i).getTeachersSessions().size(); j++) {
                /*Aunque el id sea autogenerado si no le hacemos un set de algo se vuelve loco y nos hace
                todos con la misma ID y de esa forma
                 sobreescribe la misma sesion en la base de datos.*/
                professorSession.setId((long) 1);
                professorSession.setDay(data.getScheduleTeachers().get(i).getTeachersSessions().get(j).getDay());
                professorSession.setHour(data.getScheduleTeachers().get(i).getTeachersSessions().get(j).getHour());
                professorSession.setGroup(groupRepository.findById(data.getScheduleTeachers().get(i).getTeachersSessions().get(j).getGroupCode()).get());
                professorSession.setProfessor(professorRepository.findById(data.getScheduleTeachers().get(i).getTeachersSessions().get(j).getProfessorCode()).get());


            }
        }

        //Sacar todos las sessiones de estudiantes y  guardarlos en la base de datos

        for (int i = 0; i < data.getScheduleStudents().size(); i++) {
            for (int j = 0; j < data.getScheduleStudents().get(i).getStudentSessions().size(); j++) {
                /*Aunque el id sea autogenerado si no le hacemos un set de algo se vuelve loco y nos hace
                todos con la misma ID y de esa forma
                 sobreescribe la misma sesion en la base de datos.*/
                studentSession.setId((long) 1);
                studentSession.setDay(data.getScheduleStudents().get(i).getStudentSessions().get(j).getDay());
                studentSession.setHour(data.getScheduleStudents().get(i).getStudentSessions().get(j).getHour());
                studentSession.setStudent(studentRepository.findById(data.getScheduleStudents().get(i).getStudentSessions().get(j).getStudentCode()).get());
                sessionStudentRepository.save(studentSession);
            }
        }
    }
}
