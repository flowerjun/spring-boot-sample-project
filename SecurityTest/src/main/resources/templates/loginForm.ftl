<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Log in</title>
</head>
<body>
<h1>Log in</h1>

<form role="form" action="/loginCheck" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div>
        <label for="email">User ID</label>
        <input type="text" name="username" id="username" required autofocus/>
    </div>
    <div>
        <label for="password">Password</label>
        <input type="password" name="password" id="password" required/>
    </div>
    <div>
        <label for="remember-me">Remember me</label>
        <input type="checkbox" name="remember-me" id="remember-me"/>
    </div>
    <button type="submit">Sign in</button>
	<#if error == "true">
	<p>ID or Password is incorrect</p>
	</#if>
	<#if logout == "true">
	<p>You successfully logout</p>
	</#if>
</form>
<script>
history.pushState(null, "", location.href.split("?")[0]); // remove after ? in address bar
</script>
</body>
</html>