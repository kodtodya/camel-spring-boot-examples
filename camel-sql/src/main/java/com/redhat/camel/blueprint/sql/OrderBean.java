package com.redhat.camel.blueprint.sql;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.google.gson.Gson;


/**
 * Bean that generates and process orders.
 */
public class OrderBean {

    private int counter;
    private Random ran = new Random();
    private Gson gson = new Gson();

    /**
     * Generates a new order structured as a {@link Map}
     */
    public Map<String, Object> generateOrder() {
        Map<String, Object> answer = new HashMap<String, Object>();
        answer.put("id", counter++);
        answer.put("item", counter % 2 == 0 ? 111 : 222);
        answer.put("amount", ran.nextInt(10) + 1);
        answer.put("description", counter % 2 == 0 ? "Camel in Action" : "ActiveMQ in Action");
        return answer;
    }

    /**
     * Processes the order
     *
     * @param data  the order as a {@link Map}
     * @return the transformed order
     */
    public String processOrder(Map<String, Object> data) {
        return "Processed order id " + data.get("id") + " item " + data.get("item") + " of " + data.get("amount") + " copies of " + data.get("description");
    }
    
    public String printOrders(Map<String, Object> data){
    	return gson.toJson(data);        
    }
}