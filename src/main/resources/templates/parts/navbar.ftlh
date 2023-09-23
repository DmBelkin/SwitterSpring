<#include "security.ftlh" >

<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">SwitterSpring</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" href="/">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="/main">Messages</a>
                </li>
                <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link active" href="/user">User List</a>
                </li>
                </#if>
            </ul>
            <#if isAuthorized>
            <form action="/logout" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button class="btn btn-primary" type="submit">Sign Out</button>
            </form>
            </#if>
            <div class="navbar-text">${username}</div>
        </div>
    </div>
</nav>