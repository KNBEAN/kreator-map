package data;

import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.ArrayList;

public class JsonsParser {

    /**
     * This method parse json to array list which has specific entity type.
     * Json file has to contain only data of one entity.
     * @param jsonPath Path to json file
     * @param type Represents generic types, example: new TypeToken<List<EdgeEntity>>(){}.getType()
     * @param <T>
     * @return Array list of specific entity type
     * @throws FileNotFoundException
     */
    public static  <T> ArrayList<T> getEntityArrayList(String jsonPath, Type type) throws FileNotFoundException {
        ArrayList<T> arrayList = null;
        try {
            jsonPath = URLDecoder.decode(jsonPath,"UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try (InputStream inputStream = new FileInputStream(jsonPath)) {
            Gson gson = new Gson();
            Reader reader = new InputStreamReader(inputStream);
            arrayList = gson.fromJson(reader, type);
        } catch (IOException e) {
            throw new FileNotFoundException("File not found");
        }

        return arrayList;
    }
}
