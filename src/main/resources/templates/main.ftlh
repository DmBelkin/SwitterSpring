<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
<div class="form-row">
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="Sign out"/>
    </form>
    <form method="get" action="/main" class="form-inline">
        <input type="text" name="filter" class="form-control" value="${filter!}" placeholder="Search by tag"/>
        <button type="submit" class="btn btn-primary">Search</button>
    </form>
</div>
<div>
    <form method="post" enctype="multipart/form-data">
        <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}" name="text"
               value= "<#if message??>${message.text}</#if>" placeholder="Введите сообщение"/>
        <#if textError??>
        <div class="invalid feedback">
            ${textError}
        </div>
    </#if>
    <input type="text" name="tag" placeholder="тэг">
    <input type="file" name="file">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit">Добавить</button>
    </form>
</div>
<#list messages as message>
<div>
    <b>${message.id}</b>
    <span>${message.text}</span>
    <i>${message.tag}</i>
    <strong>${message.authorName}</strong>
    <div>
        <#if message.filename??>
        <img src="/img/${message.filename}">
    </#if>
</div>
<#else>
No messages
</#list>
</@c.page>