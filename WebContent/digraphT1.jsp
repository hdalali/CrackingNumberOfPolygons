<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="http://sigmajs.org/assets/js/sigma.min.js"></script>
    <script src="http://sigmajs.org/assets/js/sigma.parsers.gexf.min.js"></script>
    <script src="graph.gexf"></script>
    <style>
    #content {
      top: 0;
      bottom: 0;
      left: 0;
      right: 0;
      position: absolute;
    }
    </style>
</head>
<body>
	<div id="content"></div>
    <h1>ygb</h1>
    <script>
      s = new sigma({
        renderer: {
            container: document.getElementById('content'),
            type: 'canvas'
        },
        settings : {
             maxEdgeSize: 5
        }
    });
    sigma.parsers.gexf('graph.gexf', s, function() {
        s.graph.edges().forEach(function(edge){ 
          edge.type = "arrow";
        });
        s.refresh();
    });
    </script>
</body>
</html>