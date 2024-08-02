<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,org.firebirdsql.management.*"%>

<html>
   <head>
      <title>pruebaConexion.jsp</title>
      <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
   </head>

<body bgcolor="#CCCCFF" text="#000000">
<h1>Prueba conexión</H1>

<%
   String DB_SERVER_URL = "localhost";
   int DB_SERVER_PORT = 3050;

   String DB_PATH = "C:/DAI";

   String DB_NAME = "AGENCIA.FDB";
   String DB_USER = "SYSDBA";
   String DB_PASSWORD = "masterkey";
   String DB_CONNECTION_STRING = "jdbc:firebirdsql:"+DB_SERVER_URL+"/"+DB_SERVER_PORT+":"+DB_PATH+"/"+DB_NAME;

   String ciudad= request.getParameter("ciudad");

   FBManager fbManager = new FBManager();

   fbManager.setServer(DB_SERVER_URL);
   fbManager.setPort(DB_SERVER_PORT);

   fbManager.start();

   Class.forName("org.firebirdsql.jdbc.FBDriver");
%>

<p>Abriendo conexión: <%=DB_CONNECTION_STRING%></p>

<%
   Connection connRSFind = DriverManager.getConnection(DB_CONNECTION_STRING, DB_USER, DB_PASSWORD);
%>

<p>El parámetro introducido es: <%=ciudad%><p>

<%
   String sqlString = "SELECT * FROM CIUDAD WHERE CIUDAD = \'" + ciudad +"\'";
%>

<p>La cadena SQL es: <%=sqlString%></p>

<%
   PreparedStatement StatementRSFind = null;
   ResultSet RSFind = null;
   boolean resultException = false;
   boolean rsReady = false;

   try{
      StatementRSFind = connRSFind.prepareStatement(sqlString);
      RSFind = StatementRSFind.executeQuery();
      rsReady = RSFind.next();
   }catch(SQLException e1){
      resultException = true;

%>
<p>Error: <%=e1.getMessage()%></p>
<%
}

int i = 0;

if (rsReady){
   boolean done = false;
   while (!done){
      i++;
      String campoCiudad = (String) RSFind.getObject("CIUDAD");
      String campoIdCiudad = RSFind.getString("IDCIUDAD");

%>
<p>Name <%=i%>: <%=campoIdCiudad%> <%=campoCiudad%></p>
<%
   done = !RSFind.next();
} //End while loop

RSFind.close();
}
else{
%>
<p>The result set was empty. Check to be sure database is running and settings in search.jsp are correct.</p>
<%

}
if (StatementRSFind != null)
StatementRSFind.close();

if (connRSFind != null)
connRSFind.close();

%>

</body>
</html>