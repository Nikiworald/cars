<input type="text" id="tId" name="tId" value="${tId}" onchange="ajaxd()"> to update press enter or wait 2 sec
<script  src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
 <p id="text"></p>
<script>
$(document).ready(function() {
ajaxd();
setInterval(ajaxd, 2000);
});
function ajaxd() {//works
var testId=document.getElementById("tId").value;
  $.ajax({
   type: "GET",
   url: "http://192.168.250.44:8080/cars/LiveTestUpdateServlet",
   data: "testId="+testId,
   success: function(response){
   document.getElementById("text").innerHTML = response;//todo json to table
   }
 });
}
</script>