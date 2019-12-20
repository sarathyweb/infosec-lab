/*************************************************
* Program to perform encryption and decryption of
* a given string using Caesar cipher
*************************************************/

package main

import (
	"errors"
	"fmt"
	"log"
)

func TextToNumbers(text string) []rune {
	var numbers []rune
	numbers = []rune(text)
	return numbers
}

func NumbersToText(numbers []rune) string {
	var s string
	s = string(numbers)
	return s
}

func GetShift() int {
	var shift int
	fmt.Print("Enter the number of shifts: ")
	fmt.Scanf("%d", &shift)
	shift = shift % 26
	return shift
}

func Encrypt() {
	var numbers []rune
	var text string
	var enctypted_text string
	var shift int

	fmt.Print("Enter the text you want to encrypt: ")
	fmt.Scanf("%s", &text)
	shift = GetShift()
	numbers = TextToNumbers(text)
	for index, value := range numbers {
		if numbers[index] >= 65 && numbers[index] <= 90 {
			numbers[index] = rune((value+rune(shift)-65)%26 + 65)
		} else if numbers[index] >= 97 && numbers[index] <= 122 {
			numbers[index] = rune((value+rune(shift)-97)%26 + 97)
		} else {
			log.Fatal(errors.New("Only A-Z and a-z are allowed"))
		}
	}
	fmt.Println(numbers)
	enctypted_text = NumbersToText(numbers)
	fmt.Println("The encrypted text is :", enctypted_text)
}

func main() {
	Encrypt()
}
