<#macro login path>
    <form action="${path}" method="post">
        <div><label> Користувач : <input type="text" name="username"/> </label></div>
        <div><label> Пароль : <input type="password" name="password"/> </label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div><input type="submit" value="Ввійти"/></div>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="submit" value="Вийти"/>
    </form>
</#macro>