<#import "parts/common.ftl" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col md-6">
        <form method="get" action="/main" class="form-inline">
            <input type="text" name="filter" value="${filter?ifExists}" class="form-control" placeholder="Пошукова фраза"/>
            <button type="submit" class="btn btn-primary ml-2">Шукати</button>
        </form>
        </div>
    </div>

    <#include "parts/messageEdit.ftl" />

    <#include "parts/messageList.ftl" />

</@c.page>