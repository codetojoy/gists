
package net.codetojoy.v2;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

public class Utils {
    /*
    protected Employee buildEmployee(JsonNode node) throws Exception {
        Employee result = new Employee();

        if (node.get("name") != null) {
            result.setName(node.get("name").asText());
        }
        if (node.get("address") != null) {
            result.setAddress(node.get("address").asText());
        }
        if (node.get("id") != null) {
            result.setId(node.get("id").asInt());
        }
        if (node.get("contacts") != null) {
            List<String> contacts = new ArrayList<>();
            JsonNode contactsNode = node.get("contacts");
            for (JsonNode contactNode : contactsNode) {
                contacts.add(contactNode.asText());
            }
            result.setContacts(contacts);
        }
        if (node.get("reports") != null) {
            List<Employee> reports = new ArrayList<>();
            JsonNode reportsNode = node.get("reports");
            for (JsonNode reportNode : reportsNode) {
                Employee report = buildEmployee(reportNode);
                reports.add(report);
            }
            result.setReports(reports);
        }

        return result;
    }
    */

    public Employee buildEmployee(String inputStr) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Employee result = objectMapper.readValue(inputStr, Employee.class);
        return result;
    }

    public Employee buildEmployee(InputStream inputStream) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Employee result = objectMapper.readValue(inputStream, Employee.class);
        return result;
    }

    public int getFoo() { return 5150; }
}
