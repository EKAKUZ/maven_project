package ru.itmo.lessons;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

// @ - разные в разных библиотеках,
// так же в библиотеках смотрим, как библиотеки работают с датой и временем
@JsonInclude(JsonInclude.Include.NON_NULL) //чтоб в json не включались св-ва null
//@JsonInclude(JsonInclude.Include.NON_DEFAULT) - исключение null, false, пустой char
public class Post {
    @JsonIgnore  // чтоб св-во не включалось в json строчку
    private int userId;
    private int id;
    private String title;
    private String body;
    // обязательно пустой конструктор, геттеры сеттеры, toString - чтоб увидеть обьект

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Post{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
