<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
h1 {
	font-weight: 1000;
	font-family: 'Titillium Web', sans-serif;
	position: relative;
	font-size: 16px;
	line-height: 40px;
	padding: 15px 15px 15px 15%;
	margin-left: 250px;
	margin-right: 350px;
	color: #355681;
	box-shadow: inset 0 0 0 1px rgba(53, 86, 129, 0.8), inset 0 0 5px
		rgba(53, 86, 129, 0.8), inset -285px 0 35px #B0F899;
	border-radius: 0 10px 0 10px;
	background: #41E9E4;
}

#graph-container {
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	position: absolute;
	width: 90%;
	height: 65%;
	margin-top: 20px;
	padding: 10px;
	margin: auto;
	vertical-align: middle;
	align: "center";
	background-color: #D5EAF5;
	overflow: auto;
	border: solid black;
}

#submitPos {
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	position: absolute;
	width: 44%;
	height: 20%;
	margin: 0 auto;
	top: 50%;
	height: 5.5em;
	margin-top: 15em;
	align: "center";
	background-color: #EDF538;
	overflow: auto;
	border: solid black;
}

body {
	background-image:
		url("https://www.rd.com/wp-content/uploads/2014/07/01-summer-night-sky-constellations-sl.jpg");
}
</style>
<script src="sigma.core.js"></script>
<script src="conrad.js"></script>
<script src="utils/sigma.utils.js"></script>
<script src="utils/sigma.polyfills.js"></script>
<script src="sigma.settings.js"></script>
<script src="classes/sigma.classes.dispatcher.js"></script>
<script src="classes/sigma.classes.configurable.js"></script>
<script src="classes/sigma.classes.graph.js"></script>
<script src="classes/sigma.classes.camera.js"></script>
<script src="classes/sigma.classes.quad.js"></script>
<script src="classes/sigma.classes.edgequad.js"></script>
<script src="captors/sigma.captors.mouse.js"></script>
<script src="captors/sigma.captors.touch.js"></script>
<script src="renderers/sigma.renderers.canvas.js"></script>
<script src="renderers/sigma.renderers.webgl.js"></script>
<script src="renderers/sigma.renderers.svg.js"></script>
<script src="renderers/sigma.renderers.def.js"></script>
<script src="renderers/webgl/sigma.webgl.nodes.def.js"></script>
<script src="renderers/webgl/sigma.webgl.nodes.fast.js"></script>
<script src="renderers/webgl/sigma.webgl.edges.def.js"></script>
<script src="renderers/webgl/sigma.webgl.edges.fast.js"></script>
<script src="renderers/webgl/sigma.webgl.edges.arrow.js"></script>
<script src="renderers/canvas/sigma.canvas.labels.def.js"></script>
<script src="renderers/canvas/sigma.canvas.hovers.def.js"></script>
<script src="renderers/canvas/sigma.canvas.nodes.def.js"></script>
<script src="renderers/canvas/sigma.canvas.edges.def.js"></script>
<script src="renderers/canvas/sigma.canvas.edges.curve.js"></script>
<script src="renderers/canvas/sigma.canvas.edges.arrow.js"></script>
<script src="renderers/canvas/sigma.canvas.edges.curvedArrow.js"></script>
<script src="renderers/canvas/sigma.canvas.edgehovers.def.js"></script>
<script src="renderers/canvas/sigma.canvas.edgehovers.curve.js"></script>
<script src="renderers/canvas/sigma.canvas.edgehovers.arrow.js"></script>
<script src="renderers/canvas/sigma.canvas.edgehovers.curvedArrow.js"></script>
<script src="renderers/canvas/sigma.canvas.extremities.def.js"></script>
<script src="renderers/svg/sigma.svg.utils.js"></script>
<script src="renderers/svg/sigma.svg.nodes.def.js"></script>
<script src="renderers/svg/sigma.svg.edges.def.js"></script>
<script src="renderers/svg/sigma.svg.edges.curve.js"></script>
<script src="renderers/svg/sigma.svg.labels.def.js"></script>
<script src="renderers/svg/sigma.svg.hovers.def.js"></script>
<script src="middlewares/sigma.middlewares.rescale.js"></script>
<script src="middlewares/sigma.middlewares.copy.js"></script>
<script src="misc/sigma.misc.animation.js"></script>
<script src="misc/sigma.misc.bindEvents.js"></script>
<script src="misc/sigma.misc.bindDOMEvents.js"></script>
<script src="misc/sigma.misc.drawHovers.js"></script>

<script src="sigma.parsers.json.js"></script>
</head>

<body>

<%
	 session = request.getSession();
%>

<%if(session == null) {
	  System.out.println("session is  null ");
  }%>
	<h1>Cracking The Number Of Polygons In A Graph</h1>

	<div id="graph-container">
		
		<!--  
		<script>
	sigma.parsers.json('myGraphjson1.json', {
		  container: 'graph-container',
		  settings: {
			    maxNodeSize: 10,
			    minNodeSize: 1,
			    minEdgeSize: 2,
			    maxEdgeSize: 5,
			    minArrowSize: 3
			}
		});
</script>
-->
<script>


var g = {
	nodes : [],
	edges : []
}; 

<%if(session != null){%>
	g = <%= session.getAttribute("data")%>;
<%}%>

// Create new Sigma instance in graph-container div (use your div name here) 
s = new sigma({
	graph : g,
	container : 'graph-container',
	renderer : {
		container : document.getElementById('graph-container'),
		type : 'canvas'
	},
	settings : {
		minNodeSize : 0,
		maxNodeSize : 6
	}
});

</script>
	</div>

	<div id="submitPos">
		<div align="center">
			<h4>
				<i>${requestScope["answer"]}</i>
			</h4>
		</div>
	</div>

</body>

</html>