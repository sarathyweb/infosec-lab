package main

import (
	"fmt"
)

func main() {
	numbers := []int{3, 4, 5}
	reminders := []int{2, 3, 1}

	fmt.Println("Givem: ")
	for i, v := range numbers {
		fmt.Println("x mod", v, "=", reminders[i])
	}

	fmt.Println("X is :", findMinX(numbers, reminders))
}

func findMinX(numbers, reminders []int) int {
	var result int
	product := 1

	// calculate the product of all elements of slice numbers[]
	for _, v := range numbers {
		product *= v
	}

	for i, v := range numbers {
		pp := product / v
		result += reminders[i] * findInverse(pp, v) * pp
	}

	return result % product
}

func findInverse(a, m int) int {
	m0 := m
	t, q, x0, x1 := 0, 0, 0, 1
	if m == 1 {
		return 0
	}

	for a > 1 {
		q = a / m
		t = m
		m = a % m
		a = t
		t = x0
		x0 = x1 - q*x0
		x1 = t
	}
	if x1 < 0 {
		x1 += m0
	}
	return x1
}
