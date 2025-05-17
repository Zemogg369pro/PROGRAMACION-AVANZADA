package util;

import model.AttributeDefinition;
import model.AttributeType;
import model.Entity;
import model.EntityList;
import model.Materia;
import model.MateriaList;
import model.Profesor;
import model.ProfesorList;

import java.io.*;
import java.util.*;
import java.lang.reflect.Field;

/**
 * Service for persisting data to JSON.
 * Implements the Singleton pattern.
 */
public class JsonPersistenceService {
    private static JsonPersistenceService instance;
    private final String defaultFilePath;
    private final String dataDirectory;

    /**
     * Private constructor for Singleton pattern.
     */
    private JsonPersistenceService() {
        this.dataDirectory = "src" + File.separator + "json";
        this.defaultFilePath = dataDirectory + File.separator + "entities.json";
        ensureDataDirectoryExists();
    }

    /**
     * Ensures that the data directory exists.
     */
    private void ensureDataDirectoryExists() {
        File directory = new File(dataDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    /**
     * Gets the singleton instance.
     *
     * @return The singleton instance
     */
    public static synchronized JsonPersistenceService getInstance() {
        if (instance == null) {
            instance = new JsonPersistenceService();
        }
        return instance;
    }

    /**
     * Saves the entity list to the default JSON file.
     *
     * @param entityList The entity list to save
     * @throws IOException If an I/O error occurs
     */
    public void saveToJson(EntityList entityList) throws IOException {
        saveToJson(entityList, defaultFilePath);
    }

    /**
     * Gets the full path for a file in the data directory.
     *
     * @param fileName The file name
     * @return The full path
     */
    private String getFullPath(String fileName) {
        // If the fileName already contains the data directory, return it as is
        if (fileName.startsWith(dataDirectory + File.separator)) {
            return fileName;
        }
        return dataDirectory + File.separator + fileName;
    }

    /**
     * Saves an object to a JSON file.
     *
     * @param object The object to save
     * @param filePath The file path
     * @throws IOException If an I/O error occurs
     */
    public void saveToJson(Object object, String filePath) throws IOException {
        ensureDataDirectoryExists();
        String fullPath = getFullPath(filePath);

        String jsonString = convertToJson(object);

        // Save to JSON file
        try (FileWriter writer = new FileWriter(fullPath)) {
            writer.write(jsonString);
        }

        // Also save to TXT file with the same name but .txt extension
        String txtFilePath = fullPath.replaceAll("\\.json$", ".txt");
        try (FileWriter txtWriter = new FileWriter(txtFilePath)) {
            txtWriter.write(jsonString);
        }
    }

    /**
     * Converts an object to a JSON string.
     *
     * @param object The object to convert
     * @return The JSON string
     */
    private String convertToJson(Object object) {
        if (object == null) {
            return "null";
        }

        if (object instanceof EntityList) {
            return convertEntityListToJson((EntityList) object);
        } else if (object instanceof ProfesorList) {
            return convertProfesorListToJson((ProfesorList) object);
        } else if (object instanceof MateriaList) {
            return convertMateriaListToJson((MateriaList) object);
        } else if (object instanceof Collection) {
            return convertCollectionToJson((Collection<?>) object);
        } else if (object instanceof Map) {
            return convertMapToJson((Map<?, ?>) object);
        } else if (object instanceof String) {
            return "\"" + escapeJsonString((String) object) + "\"";
        } else if (object instanceof Number || object instanceof Boolean) {
            return object.toString();
        } else {
            return convertObjectToJson(object);
        }
    }

    /**
     * Escapes special characters in a JSON string.
     *
     * @param str The string to escape
     * @return The escaped string
     */
    private String escapeJsonString(String str) {
        if (str == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            switch (ch) {
                case '\"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    sb.append(ch);
            }
        }
        return sb.toString();
    }

    /**
     * Converts an EntityList to a JSON string.
     *
     * @param entityList The EntityList to convert
     * @return The JSON string
     */
    private String convertEntityListToJson(EntityList entityList) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        // Add attribute definitions
        sb.append("\"attributeDefinitions\":");
        sb.append(convertCollectionToJson(entityList.getAttributeDefinitions()));

        // Add entities
        sb.append(",\"entities\":");
        sb.append(convertCollectionToJson(entityList.getAll()));

        sb.append("}");
        return sb.toString();
    }

    /**
     * Converts a ProfesorList to a JSON string.
     *
     * @param profesorList The ProfesorList to convert
     * @return The JSON string
     */
    private String convertProfesorListToJson(ProfesorList profesorList) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        // Add profesores
        sb.append("\"profesores\":");
        sb.append(convertCollectionToJson(profesorList.getAll()));

        sb.append("}");
        return sb.toString();
    }

    /**
     * Converts a MateriaList to a JSON string.
     *
     * @param materiaList The MateriaList to convert
     * @return The JSON string
     */
    private String convertMateriaListToJson(MateriaList materiaList) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        // Add materias
        sb.append("\"materias\":");
        sb.append(convertCollectionToJson(materiaList.getAll()));

        sb.append("}");
        return sb.toString();
    }

    /**
     * Converts a Collection to a JSON string.
     *
     * @param collection The Collection to convert
     * @return The JSON string
     */
    private String convertCollectionToJson(Collection<?> collection) {
        if (collection == null || collection.isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        boolean first = true;
        for (Object item : collection) {
            if (!first) {
                sb.append(",");
            }
            sb.append(convertToJson(item));
            first = false;
        }

        sb.append("]");
        return sb.toString();
    }

    /**
     * Converts a Map to a JSON string.
     *
     * @param map The Map to convert
     * @return The JSON string
     */
    private String convertMapToJson(Map<?, ?> map) {
        if (map == null || map.isEmpty()) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{");

        boolean first = true;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (!first) {
                sb.append(",");
            }
            sb.append("\"").append(escapeJsonString(entry.getKey().toString())).append("\":");
            sb.append(convertToJson(entry.getValue()));
            first = false;
        }

        sb.append("}");
        return sb.toString();
    }

    /**
     * Converts an Object to a JSON string.
     *
     * @param object The Object to convert
     * @return The JSON string
     */
    private String convertObjectToJson(Object object) {
        if (object == null) {
            return "null";
        }

        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        StringBuilder sb = new StringBuilder();
        sb.append("{");

        boolean first = true;
        for (Field field : fields) {
            field.setAccessible(true);

            try {
                Object value = field.get(object);

                if (!first) {
                    sb.append(",");
                }

                sb.append("\"").append(field.getName()).append("\":");
                sb.append(convertToJson(value));

                first = false;
            } catch (IllegalAccessException e) {
                // Skip this field
            }
        }

        sb.append("}");
        return sb.toString();
    }

    /**
     * Loads the entity list from the default JSON file.
     *
     * @return The loaded entity list, or a new entity list if the file doesn't exist
     * @throws IOException If an I/O error occurs
     */
    public EntityList loadFromJson() throws IOException {
        return (EntityList) loadFromJson(defaultFilePath, new EntityList());
    }

    /**
     * Loads an object from a JSON file.
     *
     * @param filePath The file path
     * @param defaultObject The default object to return if the file doesn't exist
     * @return The loaded object, or the default object if the file doesn't exist
     * @throws IOException If an I/O error occurs
     */
    public Object loadFromJson(String filePath, Object defaultObject) throws IOException {
        String fullPath = getFullPath(filePath);
        File file = new File(fullPath);
        if (!file.exists()) {
            return defaultObject;
        }

        StringBuilder jsonString = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
        }

        if (jsonString.length() == 0 || jsonString.toString().equals("{}")) {
            return defaultObject;
        }

        try {
            if (defaultObject instanceof EntityList) {
                return parseEntityList(jsonString.toString());
            } else if (defaultObject instanceof ProfesorList) {
                return parseProfesorList(jsonString.toString());
            } else if (defaultObject instanceof MateriaList) {
                return parseMateriaList(jsonString.toString());
            } else {
                // For other types, return the default object
                return defaultObject;
            }
        } catch (Exception e) {
            throw new IOException("Error parsing JSON", e);
        }
    }

    /**
     * Parses a JSON string into an EntityList.
     *
     * @param jsonString The JSON string
     * @return The EntityList
     */
    private EntityList parseEntityList(String jsonString) {
        EntityList entityList = new EntityList();

        // For now, just return an empty EntityList
        // In a real implementation, we would parse the JSON and populate the EntityList

        return entityList;
    }

    /**
     * Parses a JSON string into a ProfesorList.
     *
     * @param jsonString The JSON string
     * @return The ProfesorList
     */
    private ProfesorList parseProfesorList(String jsonString) {
        ProfesorList profesorList = new ProfesorList();

        // For now, just return an empty ProfesorList
        // In a real implementation, we would parse the JSON and populate the ProfesorList

        return profesorList;
    }

    /**
     * Parses a JSON string into a MateriaList.
     *
     * @param jsonString The JSON string
     * @return The MateriaList
     */
    private MateriaList parseMateriaList(String jsonString) {
        MateriaList materiaList = new MateriaList();

        // For now, just return an empty MateriaList
        // In a real implementation, we would parse the JSON and populate the MateriaList

        return materiaList;
    }
}
