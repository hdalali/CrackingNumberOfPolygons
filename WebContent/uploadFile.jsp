<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
h1{
	font-weight: 1000;
	font-family: 'Titillium Web', sans-serif;
	position: relative;  
	font-size: 16px;
	line-height: 40px;
	padding: 15px 15px 15px 15%;
	margin-left: 350px;
	margin-right: 300px;
	color: red;
	box-shadow: 
		inset 0 0 0 1px rgba(53,86,129, 0.8), 
		inset 0 0 5px rgba(53,86,129, 0.8),
		inset -285px 0 35px #B0F899;
	border-radius: 0 10px 0 10px;
	background:#41E9E4;
}
body {
	background-image:
		url("https://www.rd.com/wp-content/uploads/2014/07/01-summer-night-sky-constellations-sl.jpg");
}
#description {
      top: 0;
      bottom: 0;
      left: 0;
      right: 0;
      position: absolute;
      width: 80%;
      height: 68%;
      margin-top:20px;
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
      width: 55%;
      height: 1%;
      margin: 0 auto;
      top:50%; height:4.5em; margin-top:16em;
      align: "center";
      background-color: #EDF538;
      overflow: auto;
      border: solid black;
    }
.button {
	background-color: #4CAF50;
	border: none;
	color: white;
	padding: 20px 50px;
	margin-left: 60px;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
	border-radius: 50%;
	display:inline-block;
}
h2 {
  font: 70 100px/1.2 'Merienda One', Helvetica, sans-serif;
  font-size:90%
  color: rgba(0,0,0,0.7);
  text-shadow: 3px 3px 3px #fff;
}
</style>
<link href='http://fonts.googleapis.com/css?family=Merienda+One' rel='stylesheet' type='text/css'>


</head>

   <body> 
   <h1>Cracking The Number Of Polygons In A Graph</h1>
   
   <div id="description">
   <h2 align="center">
    Upload a CSV file which consists of Graph information in the format shown below.
    <br>
    <img src="format.png" width="200" height="200">
    <br>
    </h2>
    <h2 align="center"><u><i>You can find following information from the website.</i></u></h2>
    <h2 align="left"><i>No. of Triangles in Directed Graph &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;No. of Triangles in Un-Directed Graph</i></h2> 
    <br>
    <h2 align="left"><i>No. of Quadrilaterals in Un-Directed Graph  &nbsp;&nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;No. of Pentagons in Un-Directed Graph</i></h2>
   </div>
        <div id="submitPos">
            <form action="FileUploadHandler" method="post" enctype="multipart/form-data">
                <input type="file" class="button" name="file" align="right"/>
                 <select name="gtype">
					<option value="ug">Un-Directed Graph</option>
					<option value="dig">Directed Graph</option>
				</select>
                <input type="submit" class="button" value="upload"/>
            </form>          
        </div>
    </body>

</html>