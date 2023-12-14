package program.managers;

import org.json.JSONObject;
import program.params.JsonParams;

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
}
