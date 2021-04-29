package main

import (
	"fmt"
	"math"
)

const (
	P = 23
	G = 9
)

func main() {
	fmt.Printf("Value of P is: %v\n", P)
	fmt.Printf("Value of G is: %v\n", G)

	a := 4
	fmt.Printf("Private Key Selected for Person 1 is: %v\n", a)
	b := 3
	fmt.Printf("Private Key Selected for Person 2 is: %v\n", b)

	//Computes public key value for Person 1
	x := math.Pow(G, float64(a))
	x = math.Mod(x, P)

	//Computes public key value for Person 2
	y := math.Pow(G, float64(b))
	y = math.Mod(y, P)

	// Compute Secret key value for Person 1
	sk1 := math.Pow(y, float64(a))
	sk1 = math.Mod(sk1, P)

	// Compute Secret key value for Person 2
	sk2 := math.Pow(x, float64(b))
	sk2 = math.Mod(sk2, P)

	fmt.Printf("Secret key for Person 1 is : %v\n", sk1)
	fmt.Printf("Secret key for Person 2 is : %v\n", sk2)
}
