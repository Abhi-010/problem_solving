package ibm;

import java.util.*;

public class SimplePrototypeService {
    /*
        Question :
        Implement a simple prototype service to find the difference between two JSON objects.
        To keep the prototype simple, a json will only contain key value pairs and no nested objects or arrays in it.
        Given Two JSON Objects json1, json2 find the list of keys for which the values are different.
        if a key is present only one of the jsons then it should not be considered in the result. The list of keys should be
        sorted in lexicographically ascending order.
     */

    public static String[] getJSONDiff(String json1, String json2) {

        // Convert JSON strings to maps
        Map<String, String> map1 = parseJSON(json1);
        Map<String, String> map2 = parseJSON(json2);

        // Find keys with different values
        List<String> diffKeys = new ArrayList<>();

        for (String key : map1.keySet()) {
            if (map2.containsKey(key) && !map1.get(key).equals(map2.get(key))) {
                diffKeys.add(key);
            }
        }

        // Convert list to array and sort
        Collections.sort(diffKeys);
        return diffKeys.toArray(new String[0]);
    }

    private static Map<String, String> parseJSON(String json) {

        Map<String, String> map = new HashMap<>();

        // Remove leading and trailing curly braces and split by comma
        String[] keyValuePairs = json.substring(1, json.length() - 1).split(",");

        for (String pair : keyValuePairs) {
            // Split key-value pair by colon
            String[] parts = pair.split(":");
            // Remove leading and trailing quotes from key and value
            String key = parts[0].trim().replaceAll("\"", "");
            String value = parts[1].trim().replaceAll("\"", "");
            map.put(key, value);
        }
        return map;
    }

    public static void main(String[] args) {
        String json1 = "{\"hello\":\"world\",\"hi\":\"hello\",\"you\":\"me\"}";
        String json2 = "{\"hello\":\"world\",\"hi\":\"helloo\",\"you\":\"me\"}";

        String[] result = getJSONDiff(json1, json2);
        System.out.println(Arrays.toString(result)); // Output: [hi]
    }

}
