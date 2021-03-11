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

func GetKey() int {
	var key int
	fmt.Print("Enter the key: ")
	fmt.Scanf("%d", &key)
	key = key % 26
	return key
}

func Shift(text string, key int) string {
	var numbers []rune
	numbers = TextToNumbers(text)
	for index, value := range numbers {
		if numbers[index] >= 65 && numbers[index] <= 90 {
			numbers[index] = rune((value+rune(key)-65)%26 + 65)
		} else if numbers[index] >= 97 && numbers[index] <= 122 {
			numbers[index] = rune((value+rune(key)-97)%26 + 97)
		} else {
			log.Fatal(errors.New("Only A-Z and a-z are allowed"))
		}
	}
	text = NumbersToText(numbers)
	return text
}

func Encrypt() {
	var text string
	var encrypted_text string
	var key int

	fmt.Print("Enter the text you want to encrypt: ")
	fmt.Scanf("%s", &text)
	key = GetKey()
	encrypted_text = Shift(text, key)
	fmt.Println("The encrypted text is :", encrypted_text)
}

func Decrypt() {
	var decrypted_text string
	var encrypted_text string
	var key int

	fmt.Print("Enter the text you want to decrypt: ")
	fmt.Scanf("%s", &encrypted_text)
	key = 26 - GetKey()
	decrypted_text = Shift(encrypted_text, key)
	fmt.Println("The decrypted text is :", decrypted_text)
}

func main() {
	Encrypt()
	Decrypt()
}
