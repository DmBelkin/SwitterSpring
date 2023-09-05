<#import "parts/common.ftlh" as c>

<@c.page>
Add new user
${message!}
<form action="/registration" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form label"> User Name :</label>
        <div class="col-sm-10">
            <input type="text" name="name" class="form-control" />
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form label"> Password :</label>
        <div class="col-sm-10">
            <input type="password" name="password" class="form-control" />
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form label"> Email :</label>
        <div class="col-sm-10">
            <input type="email" name="email" class="form-control" placeholder="some@some.com" />
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <div><input type="submit" value="Sign In"/></div>
</form>
</@c.page>