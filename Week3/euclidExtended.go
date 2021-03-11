package main

import "fmt"

func main() {
	var a, b, x, y int

	fmt.Print("Enter the value of a: ")
	fmt.Scanln(&a)
	fmt.Print("\nEnter the value of b: ")
	fmt.Scanln(&b)
	fmt.Println("The GCD of", a, "and", b, "is", calculateGCD(a, b, &x, &y))
}

func calculateGCD(a int, b int, x *int, y *int) int {
	var x1, y1, gcd int
	if a == 0 {
		*x = 0
		*y = 1
		return b
	}
	gcd = calculateGCD(b%a, a, &x1, &y1)

	*x = y1 - (b/a)*x1
	*y = x1

	return gcd
}
