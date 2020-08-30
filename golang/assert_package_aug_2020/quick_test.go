
package main

import (
    "testing"
    // "github.com/stretchr/testify/assert"
)

func TestLife(t *testing.T) {
    cases := []struct {
        in, expected string
    }{
        {"abcdef","fedcba"},
        {"abba","abba"},
    }

    for _, c := range cases {
        result := Reverse(c.in)

        if result != c.expected {
            t.Errorf("Life(%v) == %v, want %v", c.in, result, c.expected)
        }
    }
}

