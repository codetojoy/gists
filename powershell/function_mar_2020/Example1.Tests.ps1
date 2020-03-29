
$here = (Split-Path -Parent $MyInvocation.MyCommand.Path)
. $here\Example1.ps1

# Pester tests
Describe 'getNext' {
  It "getNext example of storing a function in a dictionary" {
    $hand = @(1,2,3,4)
    $prizeCard = 0
    $strategy = $function:getNext
    $map = @{'strategy' = $strategy}

    # test
    $result = & $script:map['strategy'] -hand $hand -prizeCard $prizeCard

    $result.Item1 | Should -Be 1
    $result.Item2.Count | Should -Be 3
  }
}
