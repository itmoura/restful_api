package br.com.b2ml.restful_api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "class")
public class ClassT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition="int")
    private long id;

    @ManyToOne
	@JoinColumn(name = "teacher_id",columnDefinition="int")
	private Teacher teacherId;

    @Column(name = "code")
    private String code;

    @Column(name = "room")
    private String room;

    @Column(name = "date_oppening", columnDefinition="date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOppening;

    @Column(name = "date_ending")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateEnding;

    public ClassT(){
		this(new Long(0));
    }
    
	public ClassT(Long id){
		this.id = id;
        this.code = "";
        this.room = "";
        this.dateOppening = new Date();
        this.dateEnding = new Date();
        this.teacherId = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Teacher getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Teacher teacherId) {
        this.teacherId = teacherId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Date getDateOppening() {
        return dateOppening;
    }

    public void setDateOppening(Date dateOppening) {
        this.dateOppening = dateOppening;
    }

    public Date getDateEnding() {
        return dateEnding;
    }

    public void setDateEnding(Date dateEnding) {
        this.dateEnding = dateEnding;
    }

}