package com.vinsguru.reactivemongo.utils;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class JsonArrayUtil extends JSONArray {

    private String jsonOne;

    private JsonArrayUtil() {
        super();
    }

    private JsonArrayUtil(String jsonArray) throws JSONException {
        super(jsonArray);
    }

    public static JsonArrayUtil parse(String json) {
        JsonArrayUtil jsonArrayUtil = null;
        try {
            jsonArrayUtil = new JsonArrayUtil(json);
        } catch (JSONException ex) {
            jsonArrayUtil = new JsonArrayUtil();
            jsonArrayUtil.jsonOne = json;
        }

        return jsonArrayUtil;
    }

    public List<DBObject> toDocuments() {
        return isBlank(jsonOne)
                ? getDBObjectArray()
                : getDBObjectOne();
    }

    private List<DBObject> getDBObjectArray() {
        return IntStream.range(0, length())
                .mapToObj(this::getDBObject)
                .collect(Collectors.toList());
    }

    private List<DBObject> getDBObjectOne() {
        return Stream.of(jsonOne)
                .map(BasicDBObject::parse)
                .collect(Collectors.toList());
    }

    private DBObject getDBObject(int index) {
        BasicDBObject dbObject = null;
        try {
            var jsonObject = super.getString(index);
            dbObject = BasicDBObject.parse(jsonObject);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return dbObject;
    }
}
