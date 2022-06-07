package ru.itmo.lessons;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *1
 *
 * */
public class Application {

    /**
     * Описание метода
     * @param args значение параметра
     */
    public static void main(String[] args) {
        /**
         *2
         *
         * */
        Pojo pojo = new Pojo(1);
        System.out.println(pojo);

        //https://my-json-server.typicode.com/typicode/demo/posts
        //https://jsonplaceholder.typicode.com/posts?_limit=10
        CloseableHttpClient httpClient = HttpClients.createDefault(); // создали http клиент необходимо закрыть!!!
        // нужно обязательно закрыть
        // обьект запроса

        HttpUriRequest httpGet = new HttpGet("https://jsonplaceholder.typicode.com/posts?_limit=10");
        // можно отправить get запрос (по http протоколу) HttpGet
        // запрос для получения информации
        // запрос состоит из
        //1. https://jsonplaceholder.typicode.com/posts - строка запроса - обязательно
        //2. заголовки - доп. информация о сообщении
        //3. тело сообщения(может быть пустым)
        // клиент может передавать данные на сервер
        //1. или в строчке запроса после ? в паре имя=значение или ?имя=значение&имя2=значение
        // данные в строке передаются, если это get запрос или delete запрос
        //2. в теле сообщения в паре имя=значение
        // если это post запрос, put запрос, patch запрос
        // get запрос -Для полученияз данных, мксимально безопасен для сервера
        // post запрос - добавление новых данных на сервер (регистрация пользователя) HttpPost
        // put, patch - обновление информации (изменение пароля)
        // delete запрос - удаление (аккаунта)


        //GET
        // обьект ответа
        String jsonGet = null;
        try (CloseableHttpResponse responseGet = httpClient.execute(httpGet))  //IOException  необходимо закрыть!!!
        {
            HttpEntity entityGet = responseGet.getEntity();
            jsonGet = EntityUtils.toString(entityGet);  //IOException

            System.out.println(jsonGet);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // статус ответа, например 200 - хорошо, 404 - ошибка
        // заголовки
        // тело сообщения, пустое или с данными
        // стандартные ответ и запрос для любого http сервера и клиента


        HttpPost httpPost = new HttpPost("https://jsonplaceholder.typicode.com/posts");
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("userId","45")); //одна пара
        params.add(new BasicNameValuePair("title","Java 16"));
        params.add(new BasicNameValuePair("body","Records type"));

        //параметры должны быть закодированы и добавлены в тело сообщения
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // обьект ответа
        String jsonPost = null;

            try (CloseableHttpResponse responsePost = httpClient.execute(httpPost)){
                HttpEntity entityPost = responsePost.getEntity();

                try {
                    jsonPost = EntityUtils.toString(entityPost);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        System.out.println(jsonPost);

        try {
            httpClient.close();
        } catch (IOException e) {
            throw  new RuntimeException(e.getCause());
        }

        // JSON -строчки
        ObjectMapper mapper = new ObjectMapper(); // создает из json строчки обьект и наоборот
        // коллекция обьектов из json строчки
        CollectionType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Post.class);
        ArrayList<Post> posts = null;
        try {
            posts = mapper.readValue(jsonGet,type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(posts);

        Post post = null;
        try {
            post = mapper.readValue(jsonPost, Post.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(post);


        //Json в обьект

        Post newPost = new Post();
        newPost.setId(34);
        newPost.setUserId(12);
        newPost.setTitle("новая запись");
        newPost.setBody("текст записи");

        String jsonStr = null;
        try {
            jsonStr = mapper.writeValueAsString(newPost);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(jsonStr);


        // запись чтение из json файла

        // обьект преобразуется в json строчку и записывает в файл
        try {
            mapper.writeValue(new File("file.json"), newPost);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // читаем файл и преобразуем  json строчку в обьект
        try {
            Post post1 = mapper.readValue(new File("file.json"), Post.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ArrayList<Post> posts1 = mapper.readValue(new File("file.txt"), type);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
