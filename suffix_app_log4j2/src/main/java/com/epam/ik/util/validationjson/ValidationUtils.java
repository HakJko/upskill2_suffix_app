package com.epam.ik.util.validationjson;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

public class ValidationUtils
{
    private static final String JSON_V4_SCHEMA_IDENTIFIER = System.getProperty("schemaJSONv4");
    private static final String JSON_SCHEMA_IDENTIFIER_ELEMENT = System.getProperty("schemaJSONv4_Id_elem");

    public static JsonNode getJsonNode(File jsonFile)
            throws IOException
    {
        return JsonLoader.fromFile(jsonFile);
    } // getJsonNode(File) ends

    public static JsonSchema _getSchemaNode(File schemaFile)
            throws IOException, ProcessingException
    {
        final JsonNode schemaNode = getJsonNode(schemaFile);
        return _getSchemaNode(schemaNode);
    } // getSchemaNode(File) ends


    public static boolean isJsonValid(JsonSchema jsonSchemaNode, JsonNode jsonNode)
            throws ProcessingException
    {
        ProcessingReport report = jsonSchemaNode.validate(jsonNode);
        return report.isSuccess();
    } // validateJson(Node) ends

    public static boolean isJsonValid(File schemaFile, File jsonFile)
            throws ProcessingException, IOException
    {
        final JsonSchema schemaNode = _getSchemaNode(schemaFile);
        final JsonNode jsonNode = getJsonNode(jsonFile);
        return isJsonValid(schemaNode, jsonNode);
    } // validateJson(Node) ends

    private static JsonSchema _getSchemaNode(JsonNode jsonNode)
            throws ProcessingException
    {
        final JsonNode schemaIdentifier = jsonNode.get(JSON_SCHEMA_IDENTIFIER_ELEMENT);
        if (null == schemaIdentifier){
            ((ObjectNode) jsonNode).put(JSON_SCHEMA_IDENTIFIER_ELEMENT, JSON_V4_SCHEMA_IDENTIFIER);
        }

        final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        return factory.getJsonSchema(jsonNode);
    } // _getSchemaNode() ends
}
