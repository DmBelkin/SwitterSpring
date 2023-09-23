<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh" >


<@c.page>
<#if isAuthorized>
<h5>Hello, ${username}</h5>
<#else>
<h5>Hello, guest</h5>
</#if>
<div>This is a simple clone off Twitter</div>
</@c.page>


