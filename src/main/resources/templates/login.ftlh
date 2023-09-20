<#import "parts/common.ftlh" as c>

<@c.page>
<div class="mb-2">Login page</div>
${message!}
<form action="/login" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form label"> User Name :</label>
        <div class="col-sm-6">
        <input type="text" name="username" class="form-control" placeholder="name" />
    </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form label"> Password :</label>
        <div class="col-sm-6">
            <input type="password" name="password" class="form-control" placeholder="password" />
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <a href="/registration">Add new user</a>
    <button class="btn btn-primary" type="submit">Sign in</button>
</form>
</@c.page>
