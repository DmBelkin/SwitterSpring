<#import "parts/common.ftlh" as c>


<@c.page>
List of users
<table>
    <thead>
    <th>Name</th>
    <th>Role</th>
    <th></th>
    </thead>
    <tbody>
    <#list users as user>
    <tr>
        <td>${user.username!}</td>
        <td><#list user.role as role>${role}<#sep>, </#list></td>
        <td><a href="/user/${user.id}">edit</a></td>
    </tr>
    </#list>
    </tbody>
</table>
</@c.page>