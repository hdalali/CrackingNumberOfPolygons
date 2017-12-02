<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Graph Display</title>
<script src="${pageContext.request.contextPath}/js/src/sigma.core.js"></script>
<script src="${pageContext.request.contextPath}/js/src/conrad.js"></script>
<script src="${pageContext.request.contextPath}/js/src/utils/sigma.utils.js"></script>
<script src="${pageContext.request.contextPath}/js/src/utils/sigma.polyfills.js"></script>
<script src="${pageContext.request.contextPath}/js/src/sigma.settings.js"></script>
<script src="${pageContext.request.contextPath}/js/src/classes/sigma.classes.dispatcher.js"></script>
<script src="${pageContext.request.contextPath}/js/src/classes/sigma.classes.configurable.js"></script>
<script src="${pageContext.request.contextPath}/js/src/classes/sigma.classes.graph.js"></script>
<script src="${pageContext.request.contextPath}/js/src/classes/sigma.classes.camera.js"></script>
<script src="${pageContext.request.contextPath}/js/src/classes/sigma.classes.quad.js"></script>
<script src="${pageContext.request.contextPath}/js/src/classes/sigma.classes.edgequad.js"></script>
<script src="${pageContext.request.contextPath}/js/src/captors/sigma.captors.mouse.js"></script>
<script src="${pageContext.request.contextPath}/js/src/captors/sigma.captors.touch.js"></script>
<script src="${pageContext.request.contextPath}/js/src/renderers/sigma.renderers.canvas.js"></script>
<script src="${pageContext.request.contextPath}/js/src/renderers/sigma.renderers.webgl.js"></script>
<script src="${pageContext.request.contextPath}/js/src/renderers/sigma.renderers.svg.js"></script>
<script src="${pageContext.request.contextPath}/js/src/renderers/sigma.renderers.def.js"></script>
<script src="${pageContext.request.contextPath}/js/src/renderers/webgl/sigma.webgl.nodes.def.js"></script>
<script src="${pageContext.request.contextPath}/js/src/renderers/webgl/sigma.webgl.nodes.fast.js"></script>
<script src="${pageContext.request.contextPath}/js/src/renderers/webgl/sigma.webgl.edges.def.js"></script>
<script src="${pageContext.request.contextPath}/js/src/renderers/webgl/sigma.webgl.edges.fast.js"></script>
<script src="${pageContext.request.contextPath}/js/src/renderers/webgl/sigma.webgl.edges.arrow.js"></script>
<script src="${pageContext.request.contextPath}/js/src/renderers/canvas/sigma.canvas.labels.def.js"></script>
<script src="${pageContext.request.contextPath}/js/src/renderers/canvas/sigma.canvas.hovers.def.js"></script>
<script src="${pageContext.request.contextPath}/js/src/renderers/canvas/sigma.canvas.nodes.def.js"></script>
<script src="${pageContext.request.contextPath}/js/src/renderers/canvas/sigma.canvas.edges.def.js"></script>
<script src="${pageContext.request.contextPath}/js/src/renderers/canvas/sigma.canvas.edges.curve.js"></script>
<script src="${pageContext.request.contextPath}/js/src/renderers/canvas/sigma.canvas.edges.arrow.js"></script>
<script src="${pageContext.request.contextPath}/js/src/renderers/canvas/sigma.canvas.edges.curvedArrow.js"></script>
<script src="${pageContext.request.contextPath}/js/src/renderers/canvas/sigma.canvas.edgehovers.def.js"></script>
<script src="${pageContext.request.contextPath}/js/src/renderers/canvas/sigma.canvas.edgehovers.curve.js"></script>
<script src="${pageContext.request.contextPath}/js/src/renderers/canvas/sigma.canvas.edgehovers.arrow.js"></script>
<script src="${pageContext.request.contextPath}/js/src/renderers/canvas/sigma.canvas.edgehovers.curvedArrow.js"></script>
<script src="${pageContext.request.contextPath}/js/src/renderers/canvas/sigma.canvas.extremities.def.js"></script>
<script src="${pageContext.request.contextPath}/js/src/renderers/svg/sigma.svg.utils.js"></script>
<script src="${pageContext.request.contextPath}/js/src/renderers/svg/sigma.svg.nodes.def.js"></script>
<script src="${pageContext.request.contextPath}/js/src/renderers/svg/sigma.svg.edges.def.js"></script>
<script src="${pageContext.request.contextPath}/js/src/renderers/svg/sigma.svg.edges.curve.js"></script>
<script src="${pageContext.request.contextPath}/js/src/renderers/svg/sigma.svg.labels.def.js"></script>
<script src="${pageContext.request.contextPath}/js/src/renderers/svg/sigma.svg.hovers.def.js"></script>
<script src="${pageContext.request.contextPath}/js/src/middlewares/sigma.middlewares.rescale.js"></script>
<script src="${pageContext.request.contextPath}/js/src/middlewares/sigma.middlewares.copy.js"></script>
<script src="${pageContext.request.contextPath}/js/src/misc/sigma.misc.animation.js"></script>
<script src="${pageContext.request.contextPath}/js/src/misc/sigma.misc.bindEvents.js"></script>
<script src="${pageContext.request.contextPath}/js/src/misc/sigma.misc.bindDOMEvents.js"></script>
<script src="${pageContext.request.contextPath}/js/src/misc/sigma.misc.drawHovers.js"></script>


<script src="${pageContext.request.contextPath}/js/plugins/sigma.parsers.json/sigma.parsers.json.js"></script>
<link rel="stylesheet" href="css/body.css" type="text/css" />

<style>


#graph-container {
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	height: 800 px;
	width: auto;
	 border-style: solid;
    border-width: 5px;
    border-color: red; 
    	position: absolute;
}
	
#submit{
	top: 805px;
	position: absolute;
}
</style>
</head>

<body>
<%
	 session = request.getSession();
%>
<!-- A placeholder for the graph -->
<div id="container">
  <div id="graph-container"></div>
  <%if(session == null) {
	  System.out.println("session is  null ");
  }%>
</div>

	<div id="submit" align = "center">
		<form action="FileParser" method="post" enctype="multipart/form-data">
			<input type="file" accept=".csv" name="File"> 
			<input	type="submit" name="fileSubmit">
		</form>
		
		<form action="BFS">
			<input type ="button" value = "BaseCase">
			<input type = "text" name ="start" placeholder = "start"> 
			<input type = "text" name = "end" placeholder = "end">
		</form>
		
		<form action="Dijkstra">
			<input type ="button" value = "Dijkstra">
			<input type = "text" name ="start" placeholder = "start"> 
			<input type = "text" name = "end" placeholder = "end">
						
		</form>
		
		<form action="CenterCity">
			<input type ="button" value = "Center">	
			<input type = "text" name ="start" placeholder = "start"> 
			<input type = "text" name = "end" placeholder = "end">
					
		</form>
			
	</div>


</body>
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
</html>