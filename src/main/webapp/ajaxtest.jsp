a
<form>
<input type="submit" value="test">
</form>
<script  src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js">
</script>
 <p id="text"></p>
<script>
$(document).ready(function() {
ajaxd();
    setInterval(ajaxd, 10000);
});

function ajaxd() {//works
  $.ajax({
   type: "GET",
   url: "http://192.168.250.44:8080/cars/LiveTestUpdateServlet",
   data: "test=test",
   success: function(response){
   document.getElementById("text").innerHTML = response;
   }
 });
}

</script>