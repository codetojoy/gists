
$here = (Split-Path -Parent $MyInvocation.MyCommand.Path)
. $here\Game.ps1

# Pester tests
Describe 'Get-Deck' {
  It "Given N, result deck has N items" {
    $deck = Get-Deck(10)
    $deck.Count | Should -Be 10 
  }
  It "Given N, result deck should be ordered" {
    $deck = Get-Deck(10)
    $deck | Select-Object -first 1 | Should -Be 1
    $deck | Select-Object -last 1 | Should -Be 10
  }
}

Describe 'Shuffle-Deck' {
  It "Given N, result deck has N items" {
    $deck = Get-Deck(10)
    $newDeck = Shuffle-Deck($deck)
    $newDeck.Count | Should -Be 10 
  }
}

<#
Describe 'Deal-Deck' {
  It "Given N, result deck has N items" {
    $deck = Get-Deck(10)
    $newDeck = Shuffle-Deck($deck)
    $newDeck.Count | Should -Be 10 
  }
}}
#>
