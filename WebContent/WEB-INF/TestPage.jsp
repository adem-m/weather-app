<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<!-- <link rel="stylesheet" href="style.css" type="text/css"> -->
<link
	href="https://fonts.googleapis.com/css2?family=Roboto&display=swap"
	rel="stylesheet">
<style>
body {
	background-color: rgb(71, 71, 71);
	font-family: "Roboto", sans-serif;
}

p {
	margin-block-start: 0;
	margin-block-end: 0;
}

h1 {
	margin-block-start: 0;
	margin-block-end: 0;
	font-weight: 100;
	font-size: 3em;
}

h4 {
	margin-block-start: 0;
	margin-block-end: 0;
	font-size: 1em;
}

.app {
	width: 900px;
	max-width: 90vw;
	margin: 2em auto;
	padding: 1em;
	border-radius: 20px;
	background-color: white;
	display: flex;
	flex-flow: column;
	text-align: center;
	box-shadow: 2px 2px 3px;
}

.cityName {
	text-align: left;
	padding: 0.5em;
}

.currentWeather {
	display: flex;
	align-items: center;
	justify-content: center;
}

.conditions {
	max-width: 200px;
}

.otherInfos {
	color: rgb(126, 126, 126);
}

.temperature {
	font-size: 4em;
}

.nextDays {
	display: flex;
	align-self: center;
	justify-content: center;
	flex-wrap: wrap;
	padding: 2em 0 1em 0;
	flex-wrap: wrap;
}

.eachDay {
	margin: 1em 0.3em 0 0.3em;
	background-color: rgb(230, 230, 230);
	border-radius: 5px;
	padding: 0.5em 0;
}

.min {
	color: rgb(126, 126, 126);
}

.form {
	display: flex;
	width: 400px;
	margin: auto;
}

.form input {
	margin: 0 0.3em;
}
</style>
<title>Météo</title>
</head>

<body>
	<div class="app">
		<div class="cityName">
			<h1>${ !empty city.name ? city.name : 'Veuillez entrer une ville'}</h1>
			<c:if test="${ !error }">
				<h4>Météo du ${ date } à ${ hour }</h4>
		</div>
		<div class="currentWeather">
			<img src="${ city.icon }" width="200px">
			<div>
				<div class="temperature">${ city.temperature }°C</div>
				<p class="conditions">${ city.conditions }</p>
			</div>
		</div>
		<p class="otherInfos">Vitesse du vent : ${ city.wind } km/h</p>
		<p class="otherInfos">Probabilité de précipitations : ${ city.probaRain }%</p>
		<div class="nextDays">
			<c:forEach items="${ city.nextDays }" var="nextDay">
				<div class="eachDay">
					<p>${ nextDay['date'] }</p>
					<p>
						<img src="${ nextDay['icon'] }" height="80px">
					</p>
					<p class="max">${ nextDay['max'] }°C</p>
					<p class="min">${ nextDay['min'] }°C</p>
				</div>
			</c:forEach>
		</div>
		</c:if>
		<form method=get class="form">
			<input type="text" class="form-control"
				placeholder="Saisissez le nom d'une ville" name="city" required
				minlength="2">
			<button class="btn btn-secondary" type="submit">
				<img
					src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/bd/VisualEditor_-_Icon_-_Search-big_-_white.svg/1200px-VisualEditor_-_Icon_-_Search-big_-_white.svg.png"
					height="22px">
			</button>
		</form>
	</div>
</body>

</html>