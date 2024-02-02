<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Live Matches</title>
<style>
body {
	font-family: 'Arial', sans-serif;
	background-color: #f5f5f5;
	margin: 0;
	padding: 0;
	display: flex;
	flex-direction: column;
	align-items: flex-start;
}

.live-heading {
	animation: blink 1.5s infinite;
	font-size: 2rem;
	text-align: center;
	text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
	color: #333;
	margin-top: 20px;
}

@keyframes blink { 0%, 100% {
	opacity: 1;
}

50%{
opacity
:
0;
}
}
.matches-container {
	width: 100%;
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
	padding: 20px;
}

.match-card {
	width: 400px;
	border: 2px solid #ddd;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
	transition: background-color 0.3s ease-in-out;
	cursor: pointer;
	margin: 20px;
	background-color: #e6f7ff;
	padding: 20px;
	hover: border-b-orange-300;
}

.match-card:hover {
	background-color: #1E90FF;
}

h1 {
	font-size: 1.2rem;
	font-weight: bold;
	margin-bottom: 8px;
	color: #004080;
}

p {
	font-size: 1rem;
	color: #333;
	margin-bottom: 8px;
}

.flex {
	display: flex;
	justify-content: space-between;
}

.text-red {
	color: #ff3333;
}

.text-green {
	color: #007f00;
}
</style>
</head>

<body>


	<div class="flex py-3 px-10 justify-between">
		<h1 class="text-gray-600 font-extrabold text-4xl live-heading">
			Live Cricket Score</h1>
	</div>

	<div class="matches-container">
		<c:forEach var="data" items="${matches}">
			<div class="match-card">
				<div class="py-2 border-b-2">
					<h1 class="font-bold">${data.teamHeading}</h1>
					<p class="font-light">${data.matchNumberVenue}</p>
				</div>
				<div class="flex justify-between">
					<h1 class="font-bold text-gray-600">${data.battingTeam}</h1>
					<h1 class="font-bold">${data.battingTeamScore}</h1>
				</div>
				<div class="flex justify-between">
					<h1 class="font-bold text-gray-600">${data.bowlTeam}</h1>
					<h1 class="font-bold">${data.bowlTeamScore}</h1>
				</div>
				<div class="flex justify-between">
					<p class="text-red">${data.liveText}</p>
					<p class="text-green">${data.textComplete}</p>
				</div>
			</div>
		</c:forEach>
	</div>

	<script>
		setInterval(function() {
			document.querySelector('.live-heading').classList.toggle('blinking');
		}, 1000);
	</script>

	<script>
		setInterval(function() {
			location.reload();
		}, 10000);
	</script>
</body>

</html>
