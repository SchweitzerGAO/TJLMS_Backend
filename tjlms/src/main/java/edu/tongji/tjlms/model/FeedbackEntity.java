package edu.tongji.tjlms.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "feedback", schema = "lms", catalog = "")
public class FeedbackEntity {
    private int id;
    private String from;
    private String to;
    private String content;
    private String time;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "from")
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Basic
    @Column(name = "to")
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedbackEntity that = (FeedbackEntity) o;
        return id == that.id && Objects.equals(from, that.from) && Objects.equals(to, that.to) && Objects.equals(content, that.content) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to, content, time);
    }
}
