$loadStatus = [System.Reflection.Assembly]::LoadWithPartialName("System.Windows.Forms")

$status = [System.Windows.Forms.MessageBox]::Show("Hello World!")
