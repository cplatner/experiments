param ([string] $name)

Function GpsDistanceKm([double] $lat1, [double] $lon1, [double] $lat2, [double] $lon2) 
{
    $R = 6371  # km
    $dLat = ($lat2 - $lat1).toRad();
    $dLon = ($lon2 - $lon1).toRad();
    $lat1 = lat1.toRad();
    $lat2 = lat2.toRad();

var a = Math.sin(dLat/2) * Math.sin(dLat/2) +
        Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2); 
var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
var d = R * c;


}


$cont = Get-Content $name

foreach ($line in $cont) { 
	$parts = $line -Split ","
        
    $newline = ""    
    foreach ($part in $parts) {
        if ($part -Match " ") {
            $newline += """$part"""
        }
        else {
            $newline += $part
        }
        $newline += ","
    }	
    $newline
}