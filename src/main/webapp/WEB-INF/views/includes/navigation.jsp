<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-header">

        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
            <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
        </button> <a class="navbar-brand" href="/"><img src="/resources/image/icon.jpg" height=100% width=auto></a> 
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

        <c:choose> 
            <c:when test="${person.accessControl ne 'CUSTOMER_REPRESENTATIVE' and person.accessControl ne 'MANAGER'}">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="best-seller">Best Sellers</a>
                    </li>
                    <li>
                        <a href="help">Help</a>
                    </li>
                </ul>
            </c:when>
        </c:choose>
        <!--         <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                        <input type="text" class="form-control" placeholder="Keyword for item">
                </div> 
                <button type="submit" class="btn btn-default">
                        Search
                </button>
        </form> -->

        <ul class="nav navbar-nav navbar-right">
            <c:choose>
                <c:when test="${empty person}">
                    <li><a href="/registration">Register</a></li>
                    <li><%@include file="/WEB-INF/views/includes/login-modal.jsp" %></li>
                </c:when>

                <c:otherwise>
                    <li class="dropdown"><%@include file="/WEB-INF/views/includes/user-setting-dropdown.jsp" %></li>
                </c:otherwise>
            </c:choose>
        </ul> <!-- end nav navbar-nav navbar-right -->

    </div>

</nav>