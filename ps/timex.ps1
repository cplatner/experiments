function timex($cmd)
{ 
    $start = Get-Date
    $cmd
    $end = Get-Date
    $elapsed = $end - $start
    $elapsed
}
