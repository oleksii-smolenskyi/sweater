<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
Додати нового користувача
   ${message!ifExists}
<@l.login "/registration" />
</@c.page>