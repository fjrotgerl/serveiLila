package com.esliceu.parser.model.comunication;

import com.esliceu.parser.model.database.*;
import com.esliceu.parser.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataContainer {

    private Iterable<Course> courses;
    private Iterable<Group> groups;
    private Iterable<Professor> professors;
    private Iterable<ProfessorSession> professorSessions;
    private Iterable<Student> students;
    private List studentSessions;
    private Iterable<SchoolRoom> schoolRooms;
    private Iterable<Subject> subjects;
    private Long numberOfStudentSessions;

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
    private SchoolRoomRepository schoolRoomRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Value("${enpoint.pages}")
    String page;

    public DataContainer(){

    }

    public void poblateData(){

        this.courses = courseRepository.findAll();
        this.groups = groupRepository.findAll();
        this.professors = professorRepository.findAll();
        this.professorSessions = sessionProfessorRepository.findAll();
        this.students = studentRepository.findAll();
        this.studentSessions = sessionStudentRepository.findAllByOrderById(PageRequest.of(0,50000));
        this.schoolRooms = schoolRoomRepository.findAll();
        this.subjects = subjectRepository.findAll();
        this.numberOfStudentSessions = sessionStudentRepository.count();

    }

    public Iterable<Course> getCourses() {
        return courses;
    }

    public void setCourses(Iterable<Course> courses) {
        this.courses = courses;
    }

    public Iterable<Group> getGroups() {
        return groups;
    }

    public void setGroups(Iterable<Group> groups) {
        this.groups = groups;
    }

    public Iterable<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(Iterable<Professor> professors) {
        this.professors = professors;
    }

    public Iterable<ProfessorSession> getProfessorSessions() {
        return professorSessions;
    }

    public void setProfessorSessions(Iterable<ProfessorSession> professorSessions) {
        this.professorSessions = professorSessions;
    }

    public Iterable<Student> getStudents() {
        return students;
    }

    public void setStudents(Iterable<Student> students) {
        this.students = students;
    }

    public Iterable<StudentSession> getStudentSessions() {
        return studentSessions;
    }

    public void setStudentSessions(List studentSessions) {
        this.studentSessions = studentSessions;
    }

    public Iterable<SchoolRoom> getSchoolRooms() {
        return schoolRooms;
    }

    public void setSchoolRooms(Iterable<SchoolRoom> schoolRooms) {
        this.schoolRooms = schoolRooms;
    }

    public Iterable<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Iterable<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Long getNumberOfStudentSessions() {
        return numberOfStudentSessions;
    }

    public void setNumberOfStudentSessions(Long numberOfStudentSessions) {
        this.numberOfStudentSessions = numberOfStudentSessions;
    }
}
