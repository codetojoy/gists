<%@page import="java.io.*" %>
<%@page import="java.util.*" %>

<%
class Reader {
    public String read(String filename) {
        String result = "unknown_3";
        Properties props = new Properties();

        try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(filename));
            System.err.println("TRACER start");
            for (String s : props.stringPropertyNames()) {
                System.err.println("TRACER s: " + s);
            }
        } catch(Exception ex) {
            result = "exception: " + ex.getMessage();
        }

        if (props != null) {
            result = "from props: " + props.getProperty("BUILD_TIMESTAMP");
        } else {
            result = "error: null props";
        }
    
        return result;
    }
}

String utilsBuildTimestamp = new Reader().read("autogen_utils_build_timestamp.properties");
String serviceBuildTimestamp = new Reader().read("autogen_service_build_timestamp.properties");
%>

<html>
<body>

<hr/><p>utils   : <%= utilsBuildTimestamp %></p>
<hr/><p>service : <%= serviceBuildTimestamp %></p>

</body>
</html>
