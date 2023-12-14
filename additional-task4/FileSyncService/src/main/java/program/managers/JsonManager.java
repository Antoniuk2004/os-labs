package program.managers;

import org.json.JSONArray;
import org.json.JSONObject;
import program.params.JsonParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonManager {
    public JSONObject createJsonObject(JsonParams jsonParams) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("uuid", jsonParams.getUuid());
        jsonObject.put("type", jsonParams.getType());
        jsonObject.put("rootPath", jsonParams.getRootPath());
        jsonObject.put("objectPath", jsonParams.getObjectPath());
        jsonObject.put("isDirectory", jsonParams.getIsDirectory());
        jsonObject.put("hasContent", jsonParams.getHasContent());
        jsonObject.put("content", jsonParams.getContent());

        return jsonObject;
    }

    public List<String> jsonToList(JSONObject jsonObject, String listName){
        String directoriesStr = String.valueOf(jsonObject.get(listName));

        JSONArray jsonArray = new JSONArray(directoriesStr);

        List<String> directories = new ArrayList<>();
        for (Object element : jsonArray) {
            directories.add(String.valueOf(element));
        }

        return directories;
    }

    public Map<String, String> jsonToMap(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        Map<String, String> resultMap = new HashMap<>();

        for (String key : jsonObject.keySet()) {
            resultMap.put(key, jsonObject.getString(key));
        }

        return resultMap;
    }
}
