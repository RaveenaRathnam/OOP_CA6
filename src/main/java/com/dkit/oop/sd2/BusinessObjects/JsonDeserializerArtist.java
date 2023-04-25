package com.dkit.oop.sd2.BusinessObjects;



// Class that implements the JsonDeserializer interface defined in Gson library
// and implements the deserialize() method that will be used by Gson parser to
// extract data from the JSON string, and put that data into the desired fields
// in the Java Objects (IssPositionAtTime object).
// This is used when our Java classes do NOT exactly the structure and key names
// used in the JSON data. A mapping myst be performed using code.
//
// This is where we MAP the structure of the JSON String onto the Java object.

import com.dkit.oop.sd2.DTOs.Artist;
import com.google.gson.*;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Create our own Deserializer class that implements the JsonDeserializer for
 * a specific class type (here, the IssPositionAtTime class).
 *
 * The deserialize() method will be called by the Gson parser when it needs to
 * parse a JSON String.  In this method, we 'get' the JSON as an JsonObject, and
 * we use appropriate JsonObject methods to extract the required fields.
 * We then instantiate a new Java object (ISSPositionAtTime) and populate it with
 * the data extracted from the JsonObject.
 *
 * JSON String --> JsonObject --> Java Object
 *
 */


public class JsonDeserializerArtist implements JsonDeserializer<Artist>{

    public Artist deserialize(JsonElement json,
                                         Type typeOfT,
                                         JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject(); // the JSON object representing the Artist

        // Extract the field data from the JSON object into temporary variables
        int id = jsonObject.get("id").getAsInt();
        String name = jsonObject.get("name").getAsString();
        String country = jsonObject.get("country").getAsString();
        String genre = jsonObject.get("genre").getAsString();
        int active_since = jsonObject.get("active_since").getAsInt();
        String biography = jsonObject.get("biography").getAsString();
        double rating=jsonObject.get("rating").getAsDouble();

        // construct a new object using the retrieved values
        Artist artist = new Artist(id,name,country,genre,active_since,biography,rating);

        return artist;
    }
}