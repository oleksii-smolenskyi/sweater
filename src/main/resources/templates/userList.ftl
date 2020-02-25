<#import "parts/common.ftl" as c>

<@c.page>
    Список користувачів
    <table>
        <thread>
            <tr>
                <th>Ім'я</th>
                <th>Роль</th>
                <th></th>
            </tr>
        </thread>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a href="/user/${user.id}">ред.</a></td>
            </tr>
        </#list>
        </tbody>
    </table>



</@c.page>