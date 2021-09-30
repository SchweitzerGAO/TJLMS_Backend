package edu.tongji.tjlms.domain;

/**
 * @author Charles Gao
 * @Description Test class
 * @date 2021/9/11
 */
public class Test {
    // id
    private Long id;

    // name
    private String name;

    // password
    private String password;

    // getters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    // setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
