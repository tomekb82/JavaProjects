
<jsp:userBean id="beatModel" scope="request" class="com.music.model.BeatModel"/>

<html>
<head>
<title>DJ View</title>
</head>
<body>

<h1>DJ View</h1>

Beats per minute = <jsp:getProperty name="beatModel" property="BPM" />

<br />
<hr>
<br />

<form method="post" action="/djview/servlet/DJView">
BPM: <input type=text name="bpm"
	value="">
</form>

</body>
</html>