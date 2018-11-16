param(	[string] $car ="Nissan",
	[string] $Model = "Dina",
	[string] $Color = "Light-blue",
	[int] $wheels = "4",
	[int] $windowSize = "24",
	[string] $file = "c:\program files\test" )


$text = @'
-car:"Nissan" -Model:"Dina" -Color:"Light-blue" -wheels:"4" -windowSize.Front:"24" -file="c:\program files\test"
'@

# assume parameter values do not contain ", otherwise this pattern should be changed
$pattern = '-([\.\w]+)[:=]"([^"]+)"'

foreach($match in [System.Text.RegularExpressions.Regex]::Matches($text, $pattern)) {
 $param = $match.Groups[1].Value
 $value = $match.Groups[2].Value
 "$param is $value"
}