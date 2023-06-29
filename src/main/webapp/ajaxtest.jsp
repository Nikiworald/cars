a
<form>
<input type="submit" value="test" onclick="loadDoc()">
</form>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js">

function loadDoc() {
  const xhttp = new XMLHttpRequest();
  xhttp.onload = function() {
    <dir>aa</dir>
    }
  xhttp.open("GET", "AjaxTestServlet", true);
  xhttp.send();
}


</script>