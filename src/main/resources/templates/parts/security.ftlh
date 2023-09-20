<#assign
    known = SPRING_SECURITY_CONTEXT??
>

<#if known>
  <#assign
     user = SPRING_SECURITY_CONTEXT.authentication.principal
     username = user.getName()
     isAdmin = user.isAdmin()
  ><#else>
   <#assign
       username = "unknown"
       isAdmin = false
   >
</#if>

