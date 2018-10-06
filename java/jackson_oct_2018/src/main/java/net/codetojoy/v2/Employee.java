
package net.codetojoy.v2;

import java.util.*;

public class Employee {
    private String name;
    private String address;
    private Integer id;
    private List<String> contacts;
    private List<Employee> reports;

    // ----------- getter / setter

    public List<String> getContacts() { return contacts; }
    public void setContacts(List<String> contacts) { this.contacts = contacts; }

    public List<Employee> getReports() { return reports; }
    public void setReports(List<Employee> reports) { this.reports = reports; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}
