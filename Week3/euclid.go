package main

import "fmt"

func main() {
	var a, b int
	fmt.Print("Enter the value of a: ")
	fmt.Scanln(&a)
	fmt.Print("\nEnter the value of b: ")
	fmt.Scanln(&b)
	fmt.Println("The GCD of", a, "and", b, "is", calculateGCD(a, b))
}

func calculateGCD(a, b int) int {
	if a == 0 {
		return b
	}
	return calculateGCD(b%a, a)
}
