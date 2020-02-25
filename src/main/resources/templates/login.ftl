<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
Вхід
<@l.login "/login" />
<a href="/registration"> Зареєструватися</a>
</@c.page>