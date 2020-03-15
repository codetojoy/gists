
# https://github.com/PoshCode/PowerShellPracticeAndStyle/blob/master/Style-Guide/Code-Layout-and-Formatting.md

function Get-Deck { 
    [CmdletBinding()]
    param(
        [int]$NumCards
    )
    process {
        1..$NumCards
    }
}

function Shuffle-Deck { 
    [CmdletBinding()]
    param(
        [int[]]$Deck
    )
    process {
        [int[]] ($Deck | Sort-Object {Get-Random}  )
    }
}

# https://stackoverflow.com/questions/13888253/powershell-break-a-long-array-into-a-array-of-array-with-length-of-n-in-one-line

<#
function Deal-Deck ($List, $HandSize) { 
    $counter = [pscustomobject] @{ Value = 0 }
    $Hands = $List | Group-Object -Property { [math]::Floor($counter.Value++ / $HandSize) }
}
#>

# ------- main 

$NumCards = 20 
$NumPlayers = 4
$HandSize = $NumCards / ($NumPlayers + 1) 

$Deck = Get-Deck($NumCards)  
$ShuffledDeck = Shuffle-Deck($Deck)
$ShuffledDeck 
# Deal-Deck($ShuffledDeck,$HandSize)
