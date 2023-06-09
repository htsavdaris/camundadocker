package com.example.camundadocker.service;

import org.springframework.web.client.RestTemplate;


public class camProcessDefinition {
    
    public static String get() {
        final String uri = "http://localhost:8080/engine-rest/process-definition";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        System.out.println(result);
        return result;

    }

    public static String getId(String id) {
        final String uri = "http://localhost:8080/engine-rest/process-definition/" + id;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        System.out.println(result);
        return result;
    }

    //Returns only the latest version of the defined workflow
    public static String getKey(String key) {
        final String uri = "http://localhost:8080/engine-rest/process-definition/key/" + key;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        System.out.println(result);
        return result;
    }

    
    // public static void startProcessInstance(int id){
    //     final String uri = "http://localhost:8080/engine-rest/process-definition/1/start"; 
    //     RestTemplate restTemplate = new RestTemplate();
    //     //String result = restTemplate.postForEntity(uri, String.class);
    //     System.out.println(result);
    // }

    
}
