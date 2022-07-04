var geocoder;
var map;
function initialize() {
	
	
	
geocoder = new google.maps.Geocoder();
var latlng = new google.maps.LatLng(37.2664398,126.9994077);
 var opts = {
 zoom: 15,
 center: latlng,
 mapTypeId: google.maps.MapTypeId.ROADMAP
 }
 map = new google.maps.Map
  (document.getElementById("map_canvas"), opts);
}

function codeAddress() {
 var address = document.getElementById("address").value;
 if (geocoder) {
 geocoder.geocode( { 'address': address,'region': 'kr'},
    function(results, status) {
  if (status == google.maps.GeocoderStatus.OK) {
    map.setCenter(results[0].geometry.location);

   var bounds = new google.maps.LatLngBounds();
   for (var r in results) {
    if (results[r].geometry) {
     var latlng = results[r].geometry.location;
     bounds.extend(latlng);
    new google.maps.Marker({
    position: latlng,map: map
    });

    document.getElementById('id_ido').innerHTML = latlng.lat();
    document.getElementById('id_keido').innerHTML = latlng.lng();




	//하버사인 공식
	const R = 6371e3; // 지구의 반지름
	
	var p1 = 37.2664398 * Math.PI / 180;
	var p2 = latlng.lat() * Math.PI / 180;
	var dp = (latlng.lat() - 37.2664398) * Math.PI / 180;
	var dr = (latlng.lng() - 126.9994077) * Math.PI / 180;

	var a = Math.sin(dp / 2) * Math.sin(dp / 2) +Math.cos(p1)*Math.cos(p2)*Math.sin(dr / 2) * Math.sin(dr / 2);

	var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

	var d = (R * c) / 1000; // meter
    var distance;
    if(d>1)
    {
        distance = d.toFixed(1)+"Km";
    }
    else{
        distance = d.toFixed(1)*1000+"m";
    }






	document.getElementById('id_distance').innerHTML = distance;
    }
   }
   //map.fitBounds(bounds);
   }else{
    alert("Geocode 불러오지 못했습니다 reason: "
         + status);
   }
  });
 }
}