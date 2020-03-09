package br.com.b2ml.restful_api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition="int")
    private long id;

    @ManyToOne
	@JoinColumn(name = "class_id",columnDefinition="int")
	private ClassT classId;

    @Column(name = "name")
    private String name;

    @Column(name = "registration", columnDefinition="int")
    private Integer registration;

    public Student(){
		this(new Long(0));
    }

	public Student(Long id){
		this.id = id;
        this.name = "";
        this.registration = 0;
        this.classId = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRegistration() {
        return registration;
    }

    public void setRegistration(Integer registration) {
        this.registration = registration;
    }

    public ClassT getClassId() {
        return classId;
    }

    public void setClassId(ClassT classId) {
        this.classId = classId;
    }

}