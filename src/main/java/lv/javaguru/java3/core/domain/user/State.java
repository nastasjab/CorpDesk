package lv.javaguru.java3.core.domain.user;

import lv.javaguru.java3.core.domain.Generic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "states")
public class State extends Generic {

    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "state")
    private String state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
