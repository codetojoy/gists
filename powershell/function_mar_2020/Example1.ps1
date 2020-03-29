
function getNext ([int[]] $hand, [int] $prizeCard) {
    [int] $bid = $hand[0]
    [int[]] $newHand = $hand | Select-Object -Skip 1
    $result = [System.Tuple]::Create($bid, $newHand)
    $result
}

$hand = @(5,4,3,2,1)
$prizeCard = 0
$strategy = $function:getNext
$map = @{'strategy' = $strategy}
# $result = & $script:map['strategy'] -hand @(5,4,3,2,1) -prizeCard 0
$result = & $script:map['strategy'] -hand $hand -prizeCard $prizeCard
$bid = $result.Item1
$newHand = $result.Item2
Write-Host "TRACER Strategy 29-MAR-2020 11:23 bid: ${bid} newHand: ${newHand}"

<#
$strategy = $function:getNext
$map = @{'strategy' = $strategy}
$result = & $script:map['strategy'] -hand @(5,4,3,2,1) -prizeCard 0
$bid = $result.Item1
$newHand = $result.Item2
Write-Host "TRACER Strategy bid: ${bid} newHand: ${newHand}"
#>
