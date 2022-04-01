package com.codecool.shop.model;

import com.google.gson.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminLog {
    private static String userID;
    private static Object object;
    private static String action;
    private static SimpleDateFormat formatter;
    private static String currentTime;
    private static String currentTime2;
    private static SimpleDateFormat formatter2;
    private static String result = "";


    private static String getJSON(String userID, String action) {
        formatter = new SimpleDateFormat("[dd-MM-yyyy HH:mm:ss]");
        currentTime = formatter.format(new Date());
        setUserID(userID);
        setAction(action);
        String result = "";
        Gson gson = new Gson();
        result += gson.toJson(currentTime);
        result += gson.toJson(userID);
        result += gson.toJson(action);
        System.out.println(result);
        return result;
    }

    private static void getJSON(String userdID, Product object, String action) throws Exception {
        formatter = new SimpleDateFormat("[HH:mm:ss]");
        formatter2 = new SimpleDateFormat("[dd-MM-yyyy]");
        currentTime = formatter.format(new Date());
        currentTime2 = formatter2.format(new Date());
        setUserID(userdID);
        setAction(action);
        String filepath = System.getProperty("user.dir") + String.format("/AdminLog/%s-%s.json", userdID, currentTime2);

        if (validateFile(filepath)) {
            try (Writer writer = new FileWriter(filepath, true)) {
                Gson gson2 = new GsonBuilder().create();
                String json = String.format("{\"Action\": \"%s\" ,\"Product Id\": \"%s\", \"time\": \"%s\"} ", action, object.getId(), currentTime);
                JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
                gson2.toJson(jsonObject, writer);

            } catch (IOException e) {
                throw new IOException(e);
            }
        } else {
            try (Writer writer = new FileWriter(filepath, false)) {
                Gson gson = new Gson();
                String json = String.format("{\"UserID\": \"%s\"}", userdID);
                JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

                String jsonAction = String.format("{\"Action\": \"%s\" ,\"Product Id\": \"%s\", \"time\": \"%s\"} ", action, object.getId(), currentTime);
                JsonObject jsonActionObject = new JsonParser().parse(jsonAction).getAsJsonObject();
                gson.toJson(jsonObject, writer);
                gson.toJson(jsonActionObject, writer);

            } catch (IOException e) {
                throw new IOException(e);
            }
        }
    }

    private static void getJSON(String userdID, BillingAddress bA,ShippingAddress sA, String action) throws Exception {
        formatter = new SimpleDateFormat("[HH:mm:ss]");
        formatter2 = new SimpleDateFormat("[dd-MM-yyyy]");
        currentTime = formatter.format(new Date());
        currentTime2 = formatter2.format(new Date());
        setUserID(userdID);
        setAction(action);
        String filepath = System.getProperty("user.dir") + String.format("/AdminLog/%s-%s.json", userdID, currentTime2);

        try (Writer writer = new FileWriter(filepath, true)) {
            Gson gson2 = new GsonBuilder().create();
            String json = String.format("{\"Action\": \"%s\" ,\"time\": \"%s\"} ", action, currentTime);
            JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
            jsonObject.add("Billing Address", new JsonParser().parse(gson2.toJson(bA)));
            jsonObject.add("Shipping Address", new JsonParser().parse(gson2.toJson(sA)));
            gson2.toJson(jsonObject, writer);

        } catch (IOException e) {
            throw new IOException(e);
        }
    }
    private static void getJSON(String userdID, CreditCard cC, String action) throws Exception {
        formatter = new SimpleDateFormat("[HH:mm:ss]");
        formatter2 = new SimpleDateFormat("[dd-MM-yyyy]");
        currentTime = formatter.format(new Date());
        currentTime2 = formatter2.format(new Date());
        setUserID(userdID);
        setAction(action);
        String filepath = System.getProperty("user.dir") + String.format("/AdminLog/%s-%s.json", userdID, currentTime2);

        try (Writer writer = new FileWriter(filepath, true)) {
            Gson gson2 = new GsonBuilder().create();
            String json = String.format("{\"Action\": \"%s\" ,\"time\": \"%s\"} ", action, currentTime);
            JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
            jsonObject.add("Credit Card", new JsonParser().parse(gson2.toJson(cC)));
            gson2.toJson(jsonObject, writer);

        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    private static boolean validateFile(String filename) {
        return new File(filename).exists();
    }

    public static void saveToJSON(String userdID, Product object, String action) throws Exception {
        getJSON(userdID, object, action);
    }

    public static void saveToJSON(String userdID, BillingAddress bA,ShippingAddress sA, String action) throws Exception {
        getJSON(userdID, bA, sA, action);
    }
    public static void saveToJSON(String userdID, CreditCard cC, String action) throws Exception {
        getJSON(userdID, cC, action);
    }

    public static void setUserID(String userID) {
        AdminLog.userID = userID;
    }

    public static void setAction(String action) {
        AdminLog.action = action;
    }
}
