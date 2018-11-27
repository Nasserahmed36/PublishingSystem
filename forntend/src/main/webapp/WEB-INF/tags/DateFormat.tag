<%@ tag import="java.util.Date" import="java.text.DateFormat"%>
<%@ tag import="java.lang.*" %>
<%@ tag import="java.text.DateFormatSymbols" %>
<%@ attribute name="month" required="true" %>
<%
    out.print(new DateFormatSymbols().getMonths()[Integer.parseInt(month)-1]);
%>