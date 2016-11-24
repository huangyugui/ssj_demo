<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<body>
<h2>Hello World!</h2>
<sec:authorize url="/user/save/false">aa</sec:authorize>
<sec:authorize url="/user/list">bb</sec:authorize>
<sec:authentication property="principal.username"/>
<a href="/j_spring_security_logout">logout</a>
<form action="">

<sec:csrfInput/>
</form>
</body>
</html>
