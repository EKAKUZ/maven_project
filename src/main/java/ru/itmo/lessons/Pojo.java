package ru.itmo.lessons;

//pojo - классы только с автоматически генерируемыми методами
//в 16 версии реализуется автоматическая генерация POJO классов - record

import java.io.IOException;

/**
 * Класс ru.itmo.lesson.Pojo для...
 * @author Itmo Java @-анотация
 * */
public class Pojo {
    /** Поле id - уникальный идентификатор */
    private final int id;
    /** Поле name - ... */
    private String name;
    //{@link Pojo#id} - ссылка на id класса Pojo
    /**
     * Конструктор принимает на вход значение для поля
     * {@link Pojo#id}
     * которое не может быть изменено после инициализации
     * @param id назначение параметра
     */
    public Pojo(int id) {
        this.id = id;
    }
    /**
     * Описание метода
     * @param name значение параметра
     */
    //@param- указывает, что принимает метод, если несколько аргументов для каждого своя анатация
    public void setName(String name) {

        this.name = name;
    }
    /***/
    @Override
    public String toString() {
        return "Pojo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    //@return- указывает, что возвращает метод
    /**
     * Описание метода
     * @return id значение уникально идентификатора
     */

    public int getId() {
        return id;
    }
    /**
     * Описание метода
     * @return name значение уникально идентификатора
     */
    public String getName() {
        return name;
    }
    /**
     * @throws IOException метод выбрасывает исключение
     * если {@link Pojo#id} меньшу 100
     */
    // @throws - описывает выбрасываемый эксепшен
    public void someVoid() throws IOException{
        if (id>100) throw new IOException();
        System.out.println(id);
    }
}
