<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Game</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div class="container">
    <div class="score">
        <h4> Your Gold: </h4>
        <label> <c:out value="${totalGold}"></c:out> </label>
    </div>
    <div class="forms">
        <div class="forms-submit">
            <h3>Farm</h3>
            <p>(earns 10-20 golds)</p>
            <form action="/process_money" method="post">
                <input type="hidden" name="building" value="farm">
                <input type="submit" value="Find Gold!" class="submit-button">
            </form>
        </div>
        <div class="forms-submit">
            <h3>Cave</h3>
            <p>(earns 5-10 golds)</p>
            <form action="/process_money" method="post">
                <input type="hidden" name="building" value="cave">
                <input type="submit" value="Find Gold!" class="submit-button">
            </form>
        </div>
        <div class="forms-submit">
            <h3>House</h3>
            <p>(earns 2-5 golds)</p>
            <form action="/process_money" method="post">
                <input type="hidden" name="building" value="house">
                <input type="submit" value="Find Gold!" class="submit-button">
            </form>
        </div>
        <div class="forms-submit">
            <h3>Casino</h3>
            <p>(earns/takes 0-50 golds)</p>
            <form action="/process_money" method="post">
                <input type="hidden" name="building" value="casino">
                <input type="submit" value="Find Gold!" class="submit-button">
            </form>
        </div>
    </div>
    <br>
    <br>
    <br>
    <h4>Activities:</h4>
    <div class="Activities">
        <ul>
            <c:forEach var="activity" items="${activities}" varStatus="status">
                <li class="${colorClass[status.index]}"> <c:out value="${activity}"></c:out></li>
            </c:forEach>
        </ul>
    </div>
    <div class="score">
        <h5> Your Number of tries: </h5>
        <label> <c:out value="${numPlay}"></c:out> </label>
    </div>
    <form action="/destroy_session" method="post">
        <button class="button-reset" >Reset Game!</button>
    </form>
</div>
</body>
</html>