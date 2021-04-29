package main

import (
	"fmt"
	"math"
)

func main() {
	// num1 and num2 are two prime numbers
	mum1, num2 := 3, 7
	//n is the first part of the public keey
	n := mum1 * num2

	// e is an exponent
	e := 2

	// Calculate phi (n)
	phi := (mum1 - 1) * (num2 - 1)

	for e < phi {
		if gcd(e, phi) == 1 {
			break
		} else {
			e++
		}
	}

	const k = 2
	// d is the Private key
	d := (1 + (k * phi)) / e
	msg := 2
	fmt.Println("Message Data: ", msg)
	//Encryption:

	//		c = (msg ^ e) % n
	c := math.Pow(float64(msg), float64(e))
	c = math.Mod(c, float64(n))

	fmt.Println("Encrypted Data: ", c)
	//Decrpton:
	//		m = (c ^ d) % n
	m := math.Pow(c, float64(d))
	m = math.Mod(m, float64(n))
	fmt.Println("Original Message: ", m)
}


//Function to calculate GCD
func gcd(a, b int) int {
	var temp int
	for {
		temp = a % b
		if temp == 0 {
			return b
		}
		a = b
		b = temp
	}
}
