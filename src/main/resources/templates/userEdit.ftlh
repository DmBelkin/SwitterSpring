<#import "parts/common.ftlh" as c>

<@c.page>
User editor
<form action="/user" method="post">
<input type="text" name="username" value="${user.username}" />
<#list roles as role>
<div>
    <label><input type="checkbox" name="${role}" ${user.role?seq_contains(role)?string("checked", "")}>${role}</label>
</div>
</#list>
<input type="hidden" value="${user.id}" name="id"  />
<input type="hidden" value="${_csrf.token}" name="_csrf"  />
<button type="submit">Save</button>
</form>
</@c.page>